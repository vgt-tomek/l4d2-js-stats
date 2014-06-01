package pl.vgtworld.l4d2jsstats.player.dto;

public class PlayerCampaignDto extends PlayerBaseDto {
	
	private boolean survived;
	
	private int deaths;
	
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
