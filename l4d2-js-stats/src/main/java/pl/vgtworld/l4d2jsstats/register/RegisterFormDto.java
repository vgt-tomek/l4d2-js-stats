package pl.vgtworld.l4d2jsstats.register;

import javax.ws.rs.FormParam;

public class RegisterFormDto {
	
	@FormParam("login")
	private String login;
	
	@FormParam("password")
	private String password;
	
	@FormParam("password-repeat")
	private String repeatPassword;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepeatPassword() {
		return repeatPassword;
	}
	
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
}
