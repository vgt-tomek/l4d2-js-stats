package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import java.util.Date;

import javax.ws.rs.FormParam;

public class AddCampaignMatchFormDto {
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("date")
	private String date;
	
	private Date dateParsed;
	
	@FormParam("difficulty")
	private int difficultyId;
	
	@FormParam("total-time")
	private String totalTime;
	
	private int totalTimeParsed;
	
	@FormParam("restarts")
	private int restarts;
	
	public int getMapId() {
		return mapId;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Date getDateParsed() {
		return dateParsed;
	}
	
	public void setDateParsed(Date dateParsed) {
		this.dateParsed = dateParsed;
	}
	
	public int getDifficultyId() {
		return difficultyId;
	}
	
	public void setDifficultyId(int difficultyId) {
		this.difficultyId = difficultyId;
	}
	
	public String getTotalTime() {
		return totalTime;
	}
	
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	
	public int getTotalTimeParsed() {
		return totalTimeParsed;
	}
	
	public void setTotalTimeParsed(int totalTimeParsed) {
		this.totalTimeParsed = totalTimeParsed;
	}
	
	public int getRestarts() {
		return restarts;
	}
	
	public void setRestarts(int restarts) {
		this.restarts = restarts;
	}
	
}
