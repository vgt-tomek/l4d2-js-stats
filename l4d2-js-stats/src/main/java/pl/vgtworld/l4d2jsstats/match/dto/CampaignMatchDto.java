package pl.vgtworld.l4d2jsstats.match.dto;

public class CampaignMatchDto extends MatchBaseDto {
	
	private static final int MINUTES_IN_HOUR = 60;
	
	private int totalTime;
	
	private String difficultyName;
	
	private int restarts;
	
	private long survivedPlayerCount;
	
	public int getTotalTime() {
		return totalTime;
	}
	
	public String getTotalTimeHumanReadable() {
		return String.format("%d:%02d", totalTime / MINUTES_IN_HOUR, totalTime % MINUTES_IN_HOUR);
	}
	
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public String getDifficultyName() {
		return difficultyName;
	}
	
	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}
	
	public int getRestarts() {
		return restarts;
	}
	
	public void setRestarts(int restarts) {
		this.restarts = restarts;
	}
	
	public long getSurvivedPlayerCount() {
		return survivedPlayerCount;
	}
	
	public void setSurvivedPlayerCount(long survivedPlayerCount) {
		this.survivedPlayerCount = survivedPlayerCount;
	}
	
}
