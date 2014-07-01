package pl.vgtworld.l4d2jsstats.match.dto;

import java.util.Date;

public class RecentMatchDto {
	
	private int id;
	
	private String type;
	
	private String typeIdentifier;
	
	private int mapId;
	
	private String mapName;
	
	private Date playedAt;
	
	private RecentMatchPlayerDto[] players;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTypeIdentifier() {
		return typeIdentifier;
	}
	
	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
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
	
	public RecentMatchPlayerDto[] getPlayers() {
		return players;
	}
	
	public void setPlayers(RecentMatchPlayerDto[] players) {
		this.players = players;
	}
	
	public boolean isSuccessfulForUser(int userId) {
		if (players == null) {
			return false;
		}
		for (RecentMatchPlayerDto player : players) {
			if (player.isWinner() && player.getId() == userId) {
				return true;
			}
		}
		return false;
	}
	
}
