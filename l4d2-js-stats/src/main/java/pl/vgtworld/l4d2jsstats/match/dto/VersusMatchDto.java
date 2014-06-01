package pl.vgtworld.l4d2jsstats.match.dto;

public class VersusMatchDto extends MatchBaseDto {
	
	private int winnerPoints;
	
	private int loserPoints;
	
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
	
}
