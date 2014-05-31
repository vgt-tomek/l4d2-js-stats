package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchValidator;
import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

public class AddCampaignMatchValidator extends AddMatchValidator {
	
	public enum ErrorMessages {
		MATCH_TYPE_REQUIRED("Match type is required."),
		DIFFICULTY_REQUIRED("Difficulty level is required."),
		TOTAL_TIME_REQUIRED("Total time is required."),
		TOTAL_TIME_INVALID("Invalid format in total time."),
		TOTAL_TIME_PARSE_ERROR("Unexpeted error while trying to parse total time."),
		RESTARTS_INVALID("Restarts should be equal or greater than 0.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private ErrorMessages(String message) {
			this.message = message;
		}
	}
	
	private static final int MINUTES_IN_HOUR = 60;
	
	public boolean validate(AddCampaignMatchFormDto form, GameMapDto[] maps, DifficultyLevelDto[] difficultyLevels) {
		validateMap(form, maps);
		validateDate(form);
		validateDifficulty(form, difficultyLevels);
		validateTotalTime(form);
		validateRestarts(form);
		return getErrors().length == 0;
	}
	
	private void validateMap(AddCampaignMatchFormDto form, GameMapDto[] maps) {
		int mapId = form.getMapId();
		validateMap(mapId, maps);
	}
	
	private void validateDate(AddCampaignMatchFormDto form) {
		String date = form.getDate();
		Date parsedDate = validateDate(date);
		form.setDateParsed(parsedDate);
	}
	
	private void validateDifficulty(AddCampaignMatchFormDto form, DifficultyLevelDto[] difficultyLevels) {
		int difficultyId = form.getDifficultyId();
		for (DifficultyLevelDto difficultyLevel : difficultyLevels) {
			if (difficultyLevel.getId() == difficultyId) {
				return;
			}
		}
		addError(ErrorMessages.DIFFICULTY_REQUIRED.getMessage());
	}
	
	private void validateTotalTime(AddCampaignMatchFormDto form) {
		String totalTime = form.getTotalTime();
		if (totalTime == null || totalTime.equals("")) {
			addError(ErrorMessages.TOTAL_TIME_REQUIRED.getMessage());
			return;
		}
		Pattern pattern = Pattern.compile("^([0-9]+):([0-9]{2})$");
		Matcher matcher = pattern.matcher(totalTime);
		if (!matcher.find()) {
			addError(ErrorMessages.TOTAL_TIME_INVALID.getMessage());
			return;
		}
		try {
			String hours = matcher.group(1);
			String minutes = matcher.group(2);
			int calculatedValue = Integer.parseInt(hours) * MINUTES_IN_HOUR + Integer.parseInt(minutes);
			form.setTotalTimeParsed(calculatedValue);
		} catch (NumberFormatException e) {
			addError(ErrorMessages.TOTAL_TIME_PARSE_ERROR.getMessage());
			return;
		}
	}
	
	private void validateRestarts(AddCampaignMatchFormDto form) {
		int restarts = form.getRestarts();
		if (restarts < 0) {
			addError(ErrorMessages.RESTARTS_INVALID.getMessage());
			return;
		}
	}
}
