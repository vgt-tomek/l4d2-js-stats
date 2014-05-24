package pl.vgtworld.l4d2jsstats.addmatch;

import java.util.Date;

import javax.ws.rs.FormParam;

public class AddMatchFormDto {
	
	@FormParam("match-type")
	private int matchTypeId;
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("date")
	private String date;
	
	private Date dateParsed;
	
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
	
}
