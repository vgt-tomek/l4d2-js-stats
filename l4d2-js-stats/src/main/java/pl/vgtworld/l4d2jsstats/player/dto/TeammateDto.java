package pl.vgtworld.l4d2jsstats.player.dto;

public class TeammateDto {
	
	private String userName;
	
	private long gamesCount;
	
	public TeammateDto(String userName, long gamesCount) {
		this.userName = userName;
		this.gamesCount = gamesCount;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getGamesCount() {
		return gamesCount;
	}
	
	public void setGamesCount(long gamesCount) {
		this.gamesCount = gamesCount;
	}
	
}
