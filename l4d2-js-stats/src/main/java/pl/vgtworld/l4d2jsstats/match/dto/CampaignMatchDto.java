package pl.vgtworld.l4d2jsstats.match.dto;

import java.util.Date;

public class CampaignMatchDto {
	
	private static final int MINUTES_IN_HOUR = 60;
	
	private int id;
	
	private int ownerId;
	
	private int mapId;
	
	private String mapName;
	
	private Date playedAt;
	
	private int totalTime;
	
	private String difficultyName;
	
	private int restarts;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public int getMapId() {
		return mapId;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	
	public String getMapName() {
		return mapName;
	}
	
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public Date getPlayedAt() {
		return playedAt;
	}
	
	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
	
	public String getTotalTimeHumanReadable() {
		return String.format("%d:%02d", totalTime / MINUTES_IN_HOUR, totalTime % MINUTES_IN_HOUR);
	}
	
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public String getDifficultyName() {
		return difficultyName;
	}
	
	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}
	
	public int getRestarts() {
		return restarts;
	}
	
	public void setRestarts(int restarts) {
		this.restarts = restarts;
	}
	
}
