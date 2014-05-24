package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import javax.ws.rs.FormParam;

public class CampaignFormDto {
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("total-time")
	private String totalTime;
	
	@FormParam("difficulty")
	private int difficultyId;
	
	@FormParam("restarts")
	private String restarts;
	
	@FormParam("players")
	private int[] players;
	
	@FormParam("survived")
	private String[] survived;
	
	@FormParam("deaths")
	private String[] deaths;
	
	public int getMapId() {
		return mapId;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	
	public String getTotalTime() {
		return totalTime;
	}
	
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	
	public int getDifficultyId() {
		return difficultyId;
	}
	
	public void setDifficultyId(int difficultyId) {
		this.difficultyId = difficultyId;
	}
	
	public String getRestarts() {
		return restarts;
	}
	
	public int getRestartsParsed() throws NumberFormatException {
		return Integer.parseInt(restarts);
	}
	
	public void setRestarts(String restarts) {
		this.restarts = restarts;
	}
	
	public int[] getPlayers() {
		return players;
	}
	
	public void setPlayers(int[] players) {
		this.players = players;
	}
	
	public String[] getSurvived() {
		return survived;
	}
	
	public void setSurvived(String[] survived) {
		this.survived = survived;
	}
	
	public String[] getDeaths() {
		return deaths;
	}
	
	public void setDeaths(String[] deaths) {
		this.deaths = deaths;
	}
	
}
