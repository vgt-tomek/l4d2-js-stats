package pl.vgtworld.l4d2jsstats.match.dto;

public class MatchCountMonthlyDto {
	
	private long matchCount;
	
	private int year;
	
	private int month;
	
	public long getMatchCount() {
		return matchCount;
	}
	
	public void setMatchCount(long matchCount) {
		this.matchCount = matchCount;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
}
