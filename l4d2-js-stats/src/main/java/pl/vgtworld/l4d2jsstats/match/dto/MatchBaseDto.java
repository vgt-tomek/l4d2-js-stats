package pl.vgtworld.l4d2jsstats.match.dto;

import java.util.Date;

public abstract class MatchBaseDto {
	
	private int id;
	
	private int ownerId;
	
	private int mapId;
	
	private String mapName;
	
	private Date playedAt;
	
	private String image;
	
	private long playerCount;
	
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
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public long getPlayerCount() {
		return playerCount;
	}
	
	public void setPlayerCount(long playerCount) {
		this.playerCount = playerCount;
	}
	
}
