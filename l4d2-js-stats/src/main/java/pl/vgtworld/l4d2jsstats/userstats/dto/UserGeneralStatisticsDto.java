package pl.vgtworld.l4d2jsstats.userstats.dto;

public class UserGeneralStatisticsDto {
	
	private long totalCampaignMatchesPlayed;
	
	private long totalVersusMatchesPlayed;
	
	private float campaignSurviveCount;
	
	private float versusWinCount;
	
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
	
	public float getCampaignSurviveCount() {
		return campaignSurviveCount;
	}
	
	public void setCampaignSurviveCount(float campaignSurviveCount) {
		this.campaignSurviveCount = campaignSurviveCount;
	}
	
	public float getVersusWinCount() {
		return versusWinCount;
	}
	
	public void setVersusWinCount(float versusWinCount) {
		this.versusWinCount = versusWinCount;
	}
	
}
