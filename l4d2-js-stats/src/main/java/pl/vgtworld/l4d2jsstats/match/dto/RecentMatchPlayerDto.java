package pl.vgtworld.l4d2jsstats.match.dto;

public class RecentMatchPlayerDto implements Comparable<RecentMatchPlayerDto> {
	
	private int id;
	
	private String name;
	
	private boolean winner;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	@Override
	public int compareTo(RecentMatchPlayerDto o) {
		if (name == null) {
			return -1;
		}
		if (o.name == null) {
			return 1;
		}
		return name.compareToIgnoreCase(o.name);
	}
	
}
