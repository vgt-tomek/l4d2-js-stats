package pl.vgtworld.l4d2jsstats.changepassword;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.user.UserService;

class ChangePasswordValidator {
	
	public enum ErrorMessages {
		
		INTERNAL_ERROR("Unexpected error."),
		CURRENT_PASSWORD_REQUIRED("Current password is required."),
		CURRENT_PASSWORD_DOESNT_MATCH("Wrong current password."),
		NEW_PASSWORD_REQUIRED("New password is required."),
		NEW_PASSWORD_DOESNT_MATCH("New password doesn't match.");
		
		private String message;
		
		private ErrorMessages(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	private List<String> errors = new ArrayList<String>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	public boolean validate(ChangePasswordFormDto form, String userLogin, UserService userService) {
		validateCurrentPassword(userLogin, form.getCurrentPassword(), userService);
		validateNewPassword(form.getNewPassword(), form.getRepeatPassword());
		return errors.size() == 0;
	}
	
	private void validateCurrentPassword(String login, String password, UserService userService) {
		try {
			if (password == null || password.length() == 0) {
				errors.add(ErrorMessages.CURRENT_PASSWORD_REQUIRED.getMessage());
				return;
			}
			if (!userService.isCorrectLoginCredentials(login, password)) {
				errors.add(ErrorMessages.CURRENT_PASSWORD_DOESNT_MATCH.getMessage());
				return;
			}
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			errors.add(ErrorMessages.INTERNAL_ERROR.getMessage());
		}
	}
	
	private void validateNewPassword(String password, String repeatPassword) {
		if (password == null || repeatPassword == null || password.length() == 0) {
			errors.add(ErrorMessages.NEW_PASSWORD_REQUIRED.getMessage());
			return;
		}
		if (!password.equals(repeatPassword)) {
			errors.add(ErrorMessages.NEW_PASSWORD_DOESNT_MATCH.getMessage());
			return;
		}
	}
}
