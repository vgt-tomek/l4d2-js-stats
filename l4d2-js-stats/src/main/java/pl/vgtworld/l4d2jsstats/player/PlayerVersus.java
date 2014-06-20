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
			query = "SELECT v FROM PlayerVersus v JOIN v.player p WHERE p.match.id = :matchId"),
		@NamedQuery(name = PlayerVersus.QUERY_COUNT_TOTAL_PLAYERS_FOR_MATCH,
			query = "SELECT COUNT(v) FROM PlayerVersus v JOIN v.player p JOIN p.match m "
				+ "WHERE m.active = TRUE AND m.matchType.id = 2 AND m.id = :matchId"),
		@NamedQuery(name = PlayerVersus.QUERY_MOST_POPULAR_TEAMMATES,
			query = "SELECT new pl.vgtworld.l4d2jsstats.player.dto.TeammateDto(p.user.login, COUNT(p)) "
				+ "FROM Player p JOIN p.match m "
				+ "WHERE m.id IN (SELECT m.id FROM Player p JOIN p.match m WHERE p.user.id = :userId AND m.matchType.id = 2) "
				+ "AND m.active = TRUE AND p.user.id <> :userId GROUP BY p.user.id ORDER BY COUNT(p) DESC")
})
public class PlayerVersus implements Serializable {
	
	public static final String QUERY_FIND_FROM_MATCH = "PlayerVersus.findFromMatch";
	
	public static final String QUERY_COUNT_TOTAL_PLAYERS_FOR_MATCH = "PlayerVersus.countTotalPlayersForMatch";
	
	public static final String QUERY_MOST_POPULAR_TEAMMATES = "PlayerVersus.mostPopularTeammates";
	
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
