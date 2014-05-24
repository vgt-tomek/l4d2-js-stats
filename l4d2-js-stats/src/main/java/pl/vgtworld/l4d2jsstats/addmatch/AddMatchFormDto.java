package pl.vgtworld.l4d2jsstats.addmatch;

import javax.ws.rs.FormParam;

public class AddMatchFormDto {
	
	@FormParam("match-type")
	private int matchTypeId;
	
	@FormParam("map")
	private int mapId;
	
	public int getMatchTypeId() {
		return matchTypeId;
	}
	
	public void setMatchTypeId(int matchTypeId) {
		this.matchTypeId = matchTypeId;
	}
	
	public int getMapId() {
		return mapId;
	}
	
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	
}
