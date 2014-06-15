package pl.vgtworld.l4d2jsstats.userstats.dto;

public class UserGeneralStatisticsDto {
	
	private long totalCampaignMatchesPlayed;
	
	private long totalVersusMatchesPlayed;
	
	private long campaignSurviveCount;
	
	private long versusWinCount;
	
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
	
	public long getCampaignSurviveCount() {
		return campaignSurviveCount;
	}
	
	public void setCampaignSurviveCount(long campaignSurviveCount) {
		this.campaignSurviveCount = campaignSurviveCount;
	}
	
	public long getVersusWinCount() {
		return versusWinCount;
	}
	
	public void setVersusWinCount(long versusWinCount) {
		this.versusWinCount = versusWinCount;
	}
	
}
