package pl.vgtworld.l4d2jsstats.player;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "players_versus")
@NamedQueries({
		@NamedQuery(name = PlayerVersus.QUERY_FIND_FROM_MATCH,
			query = "SELECT v FROM PlayerVersus v JOIN v.player p WHERE p.match.id = :matchId")
})
public class PlayerVersus implements Serializable {
	
	public static final String QUERY_FIND_FROM_MATCH = "PlayerVersus.findFromMatch";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Player player;
	
	private boolean winner;
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
}
