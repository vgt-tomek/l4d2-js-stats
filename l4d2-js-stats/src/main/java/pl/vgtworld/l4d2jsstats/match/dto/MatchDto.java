package pl.vgtworld.l4d2jsstats.match.dto;

import java.util.Date;

public class MatchDto {
	
	private int id;
	
	private String matchType;
	
	private int mapId;
	
	private String mapName;
	
	private Date playedAt;
	
	private String imageName;
	
	private boolean active;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMatchType() {
		return matchType;
	}
	
	public void setMatchType(String matchType) {
		this.matchType = matchType;
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
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
