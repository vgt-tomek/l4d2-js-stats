package pl.vgtworld.l4d2jsstats.addmatch;

import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

public class AddPlayerValidator {
	
	public enum ErrorMessages {
		
		USER_REUIQRED("User is required."),
		DEATH_WRONG_VALUE("Death count should be greater or equal to 0.");
		
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
	
	public boolean validate(AddPlayerFormDto form, UserDto[] activeUsers) {
		validateUser(form, activeUsers);
		validateDeaths(form);
		return errors.size() == 0;
	}
	
	private void validateUser(AddPlayerFormDto form, UserDto[] activeUsers) {
		int userId = form.getUser();
		for (UserDto user : activeUsers) {
			if (user.getId() == userId) {
				return;
			}
		}
		errors.add(ErrorMessages.USER_REUIQRED.getMessage());
	}
	
	private void validateDeaths(AddPlayerFormDto form) {
		if (form.getDeaths() < 0) {
			errors.add(ErrorMessages.DEATH_WRONG_VALUE.getMessage());
		}
	}
}
