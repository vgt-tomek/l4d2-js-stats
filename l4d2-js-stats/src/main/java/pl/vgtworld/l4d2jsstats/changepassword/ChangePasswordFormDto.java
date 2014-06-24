package pl.vgtworld.l4d2jsstats.changepassword;

import javax.ws.rs.FormParam;

public class ChangePasswordFormDto {
	
	@FormParam("current-password")
	private String currentPassword;
	
	@FormParam("new-password")
	private String newPassword;
	
	@FormParam("repeat-password")
	private String repeatPassword;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
}
