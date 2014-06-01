package pl.vgtworld.l4d2jsstats.addmatch.versus;

import javax.ws.rs.FormParam;

public class AddVersusPlayerFormDto {
	
	@FormParam("user")
	private int user;
	
	@FormParam("win")
	private Boolean winner;
	
	public int getUser() {
		return user;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public Boolean isWinner() {
		return winner;
	}
	
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
}
