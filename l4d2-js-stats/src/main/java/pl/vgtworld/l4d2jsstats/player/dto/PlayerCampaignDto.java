package pl.vgtworld.l4d2jsstats.player.dto;

public class PlayerCampaignDto {
	
	private int userId;
	
	private String name;
	
	private boolean survived;
	
	private int deaths;
	
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
