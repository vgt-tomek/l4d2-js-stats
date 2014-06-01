package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import javax.ws.rs.FormParam;

public class AddVersusPlayerFormDto {
	
	@FormParam("user")
	private int user;
	
	@FormParam("win")
	private boolean winner;
	
	public int getUser() {
		return user;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
}
