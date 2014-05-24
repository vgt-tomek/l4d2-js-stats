package pl.vgtworld.l4d2jsstats.addmatch;

import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;

public class AddMatchValidator {
	
	public enum ErrorMessages {
		MATCH_TYPE_REQUIRED("Match type is required."),
		MAP_REQUIRED("Map is required.");
		
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
	
}
