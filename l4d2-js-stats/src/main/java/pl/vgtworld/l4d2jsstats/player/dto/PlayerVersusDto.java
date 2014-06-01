package pl.vgtworld.l4d2jsstats.player.dto;

public class PlayerVersusDto extends PlayerBaseDto {
	
	private boolean winner;
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
}
