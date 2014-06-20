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
				+ "WHERE m.active = TRUE AND m.matchType.id = 2 AND m.id = :matchId")
})
public class PlayerVersus implements Serializable {
	
	public static final String QUERY_FIND_FROM_MATCH = "PlayerVersus.findFromMatch";
	
	public static final String QUERY_COUNT_TOTAL_PLAYERS_FOR_MATCH = "PlayerVersus.countTotalPlayersForMatch";
	
	public static final String QUERY_NATIVE_MOST_POPULAR_TEAMMATES = "SELECT "
		+ "tmu.login, COUNT(*) as games_played "
		+ "FROM players_versus pv "
		+ "INNER JOIN players p ON pv.player_id = p.id "
		+ "INNER JOIN matches m ON p.match_id = m.id "
		+ "INNER JOIN players tm ON m.id = tm.match_id "
		+ "INNER JOIN players_versus tmv ON tm.id = tmv.player_id "
		+ "INNER JOIN users tmu ON tm.user_id = tmu.id "
		+ "WHERE p.user_id = :userId AND m.match_type_id = 2 AND m.active = TRUE AND tm.user_id <> :userId "
		+ "AND pv.winner = tmv.winner "
		+ "GROUP BY tm.user_id "
		+ "ORDER BY games_played DESC";
	
	public static final String QUERY_NATIVE_MOST_POPULAR_WINNING_TEAMMATES = "SELECT "
		+ "tmu.login, COUNT(*) as games_played "
		+ "FROM players_versus pv "
		+ "INNER JOIN players p ON pv.player_id = p.id "
		+ "INNER JOIN matches m ON p.match_id = m.id "
		+ "INNER JOIN players tm ON m.id = tm.match_id "
		+ "INNER JOIN players_versus tmv ON tm.id = tmv.player_id "
		+ "INNER JOIN users tmu ON tm.user_id = tmu.id "
		+ "WHERE p.user_id = :userId AND m.match_type_id = 2 AND m.active = TRUE AND tm.user_id <> :userId "
		+ "AND pv.winner = tmv.winner "
		+ "AND pv.winner = TRUE "
		+ "GROUP BY tm.user_id "
		+ "ORDER BY games_played DESC";
	
	public static final String QUERY_NATIVE_MOST_POPULAR_LOSING_TEAMMATES = "SELECT "
		+ "tmu.login, COUNT(*) as games_played "
		+ "FROM players_versus pv "
		+ "INNER JOIN players p ON pv.player_id = p.id "
		+ "INNER JOIN matches m ON p.match_id = m.id "
		+ "INNER JOIN players tm ON m.id = tm.match_id "
		+ "INNER JOIN players_versus tmv ON tm.id = tmv.player_id "
		+ "INNER JOIN users tmu ON tm.user_id = tmu.id "
		+ "WHERE p.user_id = :userId AND m.match_type_id = 2 AND m.active = TRUE AND tm.user_id <> :userId "
		+ "AND pv.winner = tmv.winner "
		+ "AND pv.winner = FALSE "
		+ "GROUP BY tm.user_id "
		+ "ORDER BY games_played DESC";
	
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
