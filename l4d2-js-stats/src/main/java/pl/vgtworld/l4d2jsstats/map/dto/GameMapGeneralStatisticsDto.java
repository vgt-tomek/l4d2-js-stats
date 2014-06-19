package pl.vgtworld.l4d2jsstats.map.dto;

public class GameMapGeneralStatisticsDto {
	
	private long totalCampaignMatchesPlayed;
	
	private long totalVersusMatchesPlayed;
	
	private long totalCampaignPlayerCount;
	
	private long survivedCampaignPlayerCount;
	
	private long totalCampaignDeathCount;
	
	private float averageVersusPoints;
	
	private int topVersusPoints;
	
	public long getTotalMatchesPlayed() {
		return totalCampaignMatchesPlayed + totalVersusMatchesPlayed;
	}
	
	public long getTotalCampaignMatchesPlayed() {
		return totalCampaignMatchesPlayed;
	}
	
	public void setTotalCampaignMatchesPlayed(long totalCampaignMatchesPlayed) {
		this.totalCampaignMatchesPlayed = totalCampaignMatchesPlayed;
	}
	
	public long getTotalVersusMatchesPlayed() {
		return totalVersusMatchesPlayed;
	}
	
	public void setTotalVersusMatchesPlayed(long totalVersusMatchesPlayed) {
		this.totalVersusMatchesPlayed = totalVersusMatchesPlayed;
	}
	
	public long getTotalCampaignPlayerCount() {
		return totalCampaignPlayerCount;
	}
	
	public void setTotalCampaignPlayerCount(long totalCampaignPlayerCount) {
		this.totalCampaignPlayerCount = totalCampaignPlayerCount;
	}
	
	public long getSurvivedCampaignPlayerCount() {
		return survivedCampaignPlayerCount;
	}
	
	public void setSurvivedCampaignPlayerCount(long survivedCampaignPlayerCount) {
		this.survivedCampaignPlayerCount = survivedCampaignPlayerCount;
	}
	
	public long getTotalCampaignDeathCount() {
		return totalCampaignDeathCount;
	}
	
	public void setTotalCampaignDeathCount(long totalCampaignDeathCount) {
		this.totalCampaignDeathCount = totalCampaignDeathCount;
	}
	
	public float getAverageVersusPoints() {
		return averageVersusPoints;
	}
	
	public void setAverageVersusPoints(float averageVersusPoints) {
		this.averageVersusPoints = averageVersusPoints;
	}
	
	public int getTopVersusPoints() {
		return topVersusPoints;
	}
	
	public void setTopVersusPoints(int topVersusPoints) {
		this.topVersusPoints = topVersusPoints;
	}
	
}
