package pl.vgtworld.l4d2jsstats.addmatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vgtworld.l4d2jsstats.App;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

public abstract class AddMatchValidator {
	
	public enum BaseErrorMessages {
		MAP_REQUIRED("Map is required."),
		DATE_REQURED("Date is required."),
		DATE_FORMAT("Invalid date format."),
		DATE_INVALID("Invalid date.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private BaseErrorMessages(String message) {
			this.message = message;
		}
		
	}
	
	private List<String> errors = new ArrayList<>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	protected void addError(String message) {
		errors.add(message);
	}
	
	protected void validateMap(int mapId, GameMapDto[] maps) {
		for (GameMapDto map : maps) {
			if (map.getId() == mapId) {
				return;
			}
		}
		errors.add(BaseErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	protected Date validateDate(String date) {
		if (date == null || date.equals("")) {
			addError(BaseErrorMessages.DATE_REQURED.getMessage());
			return null;
		}
		Pattern pattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		Matcher matcher = pattern.matcher(date);
		if (!matcher.find()) {
			addError(BaseErrorMessages.DATE_FORMAT.getMessage());
			return null;
		}
		try {
			Date parsedDate = new SimpleDateFormat(App.DATE_FORMAT).parse(date);
			return parsedDate;
		} catch (ParseException e) {
			addError(BaseErrorMessages.DATE_INVALID.getMessage());
			return null;
		}
	}
}
