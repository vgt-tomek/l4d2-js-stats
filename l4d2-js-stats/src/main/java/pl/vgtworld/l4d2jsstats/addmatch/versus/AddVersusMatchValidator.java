package pl.vgtworld.l4d2jsstats.addmatch.versus;

import java.util.Date;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchValidator;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

public class AddVersusMatchValidator extends AddMatchValidator {
	
	public enum ErrorMessages {
		WINNER_POINTS_WRONG_VALUE("Winner points should be greater or equal to 0."),
		LOSER_POINTS_WRONG_VALUE("Loser points should be greater or equal to 0.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private ErrorMessages(String message) {
			this.message = message;
		}
	}
	
	public boolean validate(AddVersusMatchFormDto form, GameMapDto[] maps) {
		validateMap(form, maps);
		validateDate(form);
		validateWinnerPoints(form);
		validateLooserPoints(form);
		return getErrors().length == 0;
	}
	
	private void validateMap(AddVersusMatchFormDto form, GameMapDto[] maps) {
		int mapId = form.getMapId();
		validateMap(mapId, maps);
	}
	
	private void validateDate(AddVersusMatchFormDto form) {
		String date = form.getDate();
		Date validatedDate = validateDate(date);
		form.setDateParsed(validatedDate);
	}
	
	private void validateWinnerPoints(AddVersusMatchFormDto form) {
		int points = form.getWinnerPoints();
		if (points < 0) {
			addError(ErrorMessages.WINNER_POINTS_WRONG_VALUE.getMessage());
			return;
		}
	}
	
	private void validateLooserPoints(AddVersusMatchFormDto form) {
		int points = form.getLoserPoints();
		if (points < 0) {
			addError(ErrorMessages.LOSER_POINTS_WRONG_VALUE.getMessage());
			return;
		}
	}
	
}
