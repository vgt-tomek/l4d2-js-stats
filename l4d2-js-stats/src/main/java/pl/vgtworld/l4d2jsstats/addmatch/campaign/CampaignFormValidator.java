package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

class CampaignFormValidator {
	
	public enum ErrorMessages {
		
		MAP_REQUIRED("Map is required."),
		TOTAL_TIME_REQUIRED("Total time is required."),
		TOTAL_TIME_WRONG_FORMAT("Wrong value in total time field. Expected format is h:mm."),
		DIFFICULTY_REQUIRED("Difficulty level is required.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private ErrorMessages(String message) {
			this.message = message;
		}
	}
	
	private List<String> errors = new ArrayList<>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	public boolean validate(CampaignFormDto form, GameMapDto[] maps, DifficultyLevelDto[] difficultyLevels) {
		validateMap(form, maps);
		validateTotalTime(form);
		validateDifficulty(form, difficultyLevels);
		return errors.size() == 0;
	}
	
	private void validateMap(CampaignFormDto form, GameMapDto[] maps) {
		int mapId = form.getMapId();
		for (GameMapDto map : maps) {
			if (map.getId() == mapId) {
				return;
			}
		}
		errors.add(ErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	private void validateTotalTime(CampaignFormDto form) {
		String totalTime = form.getTotalTime();
		if (totalTime.equals("")) {
			errors.add(ErrorMessages.TOTAL_TIME_REQUIRED.getMessage());
			return;
		}
		Pattern pattern = Pattern.compile("^[0-9]{1,2}:[0-9]{2}$");
		Matcher matcher = pattern.matcher(totalTime);
		if (!matcher.find()) {
			errors.add(ErrorMessages.TOTAL_TIME_WRONG_FORMAT.getMessage());
			return;
		}
	}
	
	private void validateDifficulty(CampaignFormDto form, DifficultyLevelDto[] difficultyLevels) {
		int difficultyId = form.getDifficultyId();
		for (DifficultyLevelDto difficulty : difficultyLevels) {
			if (difficulty.getId() == difficultyId) {
				return;
			}
		}
		errors.add(ErrorMessages.DIFFICULTY_REQUIRED.getMessage());
	}
}
