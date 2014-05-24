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
import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;

public class AddMatchValidator {
	
	public enum ErrorMessages {
		MATCH_TYPE_REQUIRED("Match type is required."),
		MAP_REQUIRED("Map is required."),
		DATE_REQURED("Date is required."),
		DATE_FORMAT("Invalid date format."),
		DATE_INVALID("Invalid date.");
		
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
	
	public boolean validate(AddMatchFormDto form, MatchTypeDto[] matchTypes, GameMapDto[] maps) {
		validateMatchType(form, matchTypes);
		validateMap(form, maps);
		validateDate(form);
		return errors.size() == 0;
	}
	
	private void validateMatchType(AddMatchFormDto form, MatchTypeDto[] matchTypes) {
		int matchType = form.getMatchTypeId();
		for (MatchTypeDto type : matchTypes) {
			if (type.getId() == matchType) {
				return;
			}
		}
		errors.add(ErrorMessages.MATCH_TYPE_REQUIRED.getMessage());
	}
	
	private void validateMap(AddMatchFormDto form, GameMapDto[] maps) {
		int mapId = form.getMapId();
		for (GameMapDto map : maps) {
			if (map.getId() == mapId) {
				return;
			}
		}
		errors.add(ErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	private void validateDate(AddMatchFormDto form) {
		String date = form.getDate();
		if (date == null || date.equals("")) {
			errors.add(ErrorMessages.DATE_REQURED.getMessage());
			return;
		}
		Pattern pattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		Matcher matcher = pattern.matcher(date);
		if (!matcher.find()) {
			errors.add(ErrorMessages.DATE_FORMAT.getMessage());
			return;
		}
		try {
			Date parsedDate = new SimpleDateFormat(App.DATE_FORMAT).parse(date);
			form.setDateParsed(parsedDate);
		} catch (ParseException e) {
			errors.add(ErrorMessages.DATE_INVALID.getMessage());
			return;
		}
	}
}
