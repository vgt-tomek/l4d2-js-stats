package pl.vgtworld.l4d2jsstats.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pl.vgtworld.l4d2jsstats.match.Match;
import pl.vgtworld.l4d2jsstats.user.User;

@Entity
@Table(name = "players")
@NamedQueries({
		@NamedQuery(name = Player.QUERY_DELETE_USER_FROM_MATCH,
			query = "DELETE FROM Player p WHERE p.user.id = :userId AND p.match.id = :matchId"),
		@NamedQuery(name = Player.QUERY_MOST_ACTIVE,
			query = "SELECT new pl.vgtworld.l4d2jsstats.player.dto.MostActivePlayerDto(p.user.id, p.user.login, COUNT(p)) "
				+ "FROM Player p JOIN p.match WHERE p.match.active = TRUE GROUP BY p.user.id ORDER BY COUNT(p) DESC")
})
public class Player {
	
	public static final String QUERY_DELETE_USER_FROM_MATCH = "Player.deleteUserFromMatch";
	
	public static final String QUERY_MOST_ACTIVE = "Player.mostActive";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Match match;
	
	@ManyToOne
	private User user;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Match getMatch() {
		return match;
	}
	
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
