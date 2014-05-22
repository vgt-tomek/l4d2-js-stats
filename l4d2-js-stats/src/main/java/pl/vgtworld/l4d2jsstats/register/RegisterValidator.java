package pl.vgtworld.l4d2jsstats.register;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vgtworld.l4d2jsstats.user.UserService;

class RegisterValidator {
	
	private static final int LOGIN_MIN_LENGTH = 3;
	
	private static final int LOGIN_MAX_LENGTH = 100;
	
	private static final String LOGIN_PATTERN = "^[A-Za-z0-9]+$";
	
	public enum ErrorMessages {
		
		LOGIN_REQUIRED("Login is required"),
		LOGIN_TOO_SHORT(String.format("Login is too short. Minimum length is %d characters.", LOGIN_MIN_LENGTH)),
		LOGIN_TOO_LONG(String.format("Login is too long. Maximum length is %d characters.", LOGIN_MAX_LENGTH)),
		LOGIN_FORBIDDEN_CHARACTERS("Anly letters and digits can be used in login."),
		LOGIN_TAKEN("Login is already taken."),
		PASSWORD_REQUIRED("Password is required."),
		PASSWORD_MISMATCH("Passwords doesn't match.");
		
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
	
	public boolean validate(RegisterFormDto form, UserService userService) {
		validateLogin(form.getLogin(), userService);
		validatePassword(form.getPassword(), form.getRepeatPassword());
		return errors.size() == 0;
	}
	
	private void validateLogin(String login, UserService userService) {
		if (login == null || login.equals("")) {
			errors.add(ErrorMessages.LOGIN_REQUIRED.getMessage());
			return;
		}
		if (login.length() < LOGIN_MIN_LENGTH) {
			errors.add(ErrorMessages.LOGIN_TOO_SHORT.getMessage());
			return;
		}
		if (login.length() > LOGIN_MAX_LENGTH) {
			errors.add(ErrorMessages.LOGIN_TOO_LONG.getMessage());
			return;
		}
		Pattern pattern = Pattern.compile(LOGIN_PATTERN);
		Matcher matcher = pattern.matcher(login);
		if (!matcher.find()) {
			errors.add(ErrorMessages.LOGIN_FORBIDDEN_CHARACTERS.getMessage());
			return;
		}
		if (!userService.isLoginAvailable(login)) {
			errors.add(ErrorMessages.LOGIN_TAKEN.getMessage());
			return;
		}
	}
	
	private void validatePassword(String password, String repeatPassword) {
		if (password == null || password.length() == 0) {
			errors.add(ErrorMessages.PASSWORD_REQUIRED.getMessage());
			return;
		}
		if (!password.equals(repeatPassword)) {
			errors.add(ErrorMessages.PASSWORD_MISMATCH.getMessage());
			return;
		}
	}
}
