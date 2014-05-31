package pl.vgtworld.l4d2jsstats.addmatch;

import javax.ws.rs.FormParam;

public class DeletePlayerFormDto {
	
	@FormParam("user")
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
