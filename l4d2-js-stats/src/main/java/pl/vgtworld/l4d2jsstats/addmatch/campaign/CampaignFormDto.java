package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import javax.ws.rs.FormParam;

public class CampaignFormDto {
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("total-time")
	private String totalTime;
	
	@FormParam("difficulty")
	private int difficultyId;
	
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
	
}
