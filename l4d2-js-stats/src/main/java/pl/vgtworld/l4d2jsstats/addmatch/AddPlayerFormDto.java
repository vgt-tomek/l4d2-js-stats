package pl.vgtworld.l4d2jsstats.addmatch;

import javax.ws.rs.FormParam;

public class AddPlayerFormDto {
	
	@FormParam("user")
	private int user;
	
	@FormParam("survived")
	private boolean survived;
	
	@FormParam("deaths")
	private int deaths;
	
	public int getUser() {
		return user;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public boolean isSurvived() {
		return survived;
	}
	
	public void setSurvived(boolean survived) {
		this.survived = survived;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
}
