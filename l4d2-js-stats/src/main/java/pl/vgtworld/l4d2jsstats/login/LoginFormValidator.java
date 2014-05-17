package pl.vgtworld.l4d2jsstats.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

class LoginFormValidator {
	
	public enum ErrorMessages {
		LOGIN_ERROR("Wrong login or password."),
		USER_INACTIVE("User's account is not activated."),
		SYSTEM_ERROR("Unexpected system error. Please try again.");
		
		private String message;
		
		private ErrorMessages(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	private List<String> errors = new ArrayList<>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	public boolean validate(LoginFormDto form, UserService userService) {
		try {
			String login = form.getLogin();
			if (login == null) {
				errors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			UserDto user = userService.findByLogin(login);
			if (user == null) {
				errors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			if (!userService.isCorrectLoginCredentials(form.getLogin(), form.getPassword())) {
				errors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			if (!user.isActive()) {
				errors.add(ErrorMessages.USER_INACTIVE.getMessage());
				return false;
			}
			return true;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			errors.add(ErrorMessages.SYSTEM_ERROR.getMessage());
			return false;
		}
	}
}
