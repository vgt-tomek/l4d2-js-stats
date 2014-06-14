package pl.vgtworld.l4d2jsstats.player.dto;

public class MostActivePlayerDto {
	
	private int playerId;
	
	private String playerName;
	
	private long matchCount;
	
	public MostActivePlayerDto(int playerId, String playerName, long matchCount) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.matchCount = matchCount;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public long getMatchCount() {
		return matchCount;
	}
	
	public void setMatchCount(long matchCount) {
		this.matchCount = matchCount;
	}
	
}
