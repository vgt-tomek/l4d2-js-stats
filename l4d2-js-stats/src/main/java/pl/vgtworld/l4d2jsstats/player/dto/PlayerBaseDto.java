package pl.vgtworld.l4d2jsstats.player.dto;

public abstract class PlayerBaseDto {
	
	private int userId;
	
	private String name;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
