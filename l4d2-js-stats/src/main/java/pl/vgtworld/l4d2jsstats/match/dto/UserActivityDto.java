package pl.vgtworld.l4d2jsstats.match.dto;

public class UserActivityDto implements Comparable<UserActivityDto> {
	
	private int userId;
	
	private String userName;
	
	private Integer daysInactive;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getDaysInactive() {
		return daysInactive;
	}
	
	public void setDaysInactive(Integer daysInactive) {
		this.daysInactive = daysInactive;
	}
	
	@Override
	public int compareTo(UserActivityDto other) {
		if (daysInactive == null && other.daysInactive == null) {
			return 0;
		}
		if (daysInactive == null) {
			return -1;
		}
		if (other.daysInactive == null) {
			return 1;
		}
		return other.daysInactive - daysInactive;
	}
	
}
