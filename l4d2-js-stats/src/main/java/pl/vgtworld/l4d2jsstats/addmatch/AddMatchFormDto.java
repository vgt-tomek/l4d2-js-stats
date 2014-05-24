package pl.vgtworld.l4d2jsstats.addmatch;

import javax.ws.rs.FormParam;

public class AddMatchFormDto {
	
	@FormParam("match-type")
	private int matchType;
	
	@FormParam("map")
	private int mapId;
	
	public int getMatchType() {
		return matchType;
	}
	
	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}
	
	public int getMapId() {
		return mapId;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	
}
