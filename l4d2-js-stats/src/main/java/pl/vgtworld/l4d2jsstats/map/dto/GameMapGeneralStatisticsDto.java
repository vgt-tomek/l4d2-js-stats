package pl.vgtworld.l4d2jsstats.map.dto;

public class GameMapGeneralStatisticsDto {
	
	private long totalMatchesPlayed;
	
	private float campaignSurvivalPercentage;
	
	private float averageCampaignDeathCount;
	
	private float averageVersusPoints;
	
	private float topVersusPoints;
	
	public long getTotalMatchesPlayed() {
		return totalMatchesPlayed;
	}
	
	public void setTotalMatchesPlayed(long totalMatchesPlayed) {
		this.totalMatchesPlayed = totalMatchesPlayed;
	}
	
	public float getCampaignSurvivalPercentage() {
		return campaignSurvivalPercentage;
	}
	
	public void setCampaignSurvivalPercentage(float campaignSurvivalPercentage) {
		this.campaignSurvivalPercentage = campaignSurvivalPercentage;
	}
	
	public float getAverageCampaignDeathCount() {
		return averageCampaignDeathCount;
	}
	
	public void setAverageCampaignDeathCount(float averageCampaignDeathCount) {
		this.averageCampaignDeathCount = averageCampaignDeathCount;
	}
	
	public float getAverageVersusPoints() {
		return averageVersusPoints;
	}
	
	public void setAverageVersusPoints(float averageVersusPoints) {
		this.averageVersusPoints = averageVersusPoints;
	}
	
	public float getTopVersusPoints() {
		return topVersusPoints;
	}
	
	public void setTopVersusPoints(float topVersusPoints) {
		this.topVersusPoints = topVersusPoints;
	}
	
}
