package pl.vgtworld.l4d2jsstats.addmatch;

import java.util.Date;

import javax.ws.rs.FormParam;

public class AddMatchFormDto {
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("date")
	private String date;
	
	private Date dateParsed;
	
	@FormParam("difficulty")
	private int difficultyId;
	
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
	
}
