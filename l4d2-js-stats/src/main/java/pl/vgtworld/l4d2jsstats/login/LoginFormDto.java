package pl.vgtworld.l4d2jsstats.login;

class LoginFormDto {
	
	private String login;
	
	private String password;
	
	public LoginFormDto() {
	}
	
	public LoginFormDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
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
	
}
