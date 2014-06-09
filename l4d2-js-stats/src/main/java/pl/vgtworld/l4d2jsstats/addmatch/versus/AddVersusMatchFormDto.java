package pl.vgtworld.l4d2jsstats.addmatch.versus;

import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class AddVersusMatchFormDto {
	
	@FormParam("map")
	private int mapId;
	
	@FormParam("date")
	private String date;
	
	private Date dateParsed;
	
	@FormParam("winner-points")
	private int winnerPoints;
	
	@FormParam("loser-points")
	private int loserPoints;
	
	@FormParam("image")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	private byte[] image;
	
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
	
	public int getWinnerPoints() {
		return winnerPoints;
	}
	
	public void setWinnerPoints(int winnerPoints) {
		this.winnerPoints = winnerPoints;
	}
	
	public int getLoserPoints() {
		return loserPoints;
	}
	
	public void setLoserPoints(int loserPoints) {
		this.loserPoints = loserPoints;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
