package pl.vgtworld.l4d2jsstats.match;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.user.User;

@Entity
@Table(name = "matches")
@NamedQueries({
		@NamedQuery(name = Match.QUERY_FIND_RECENT,
			query = "SELECT m FROM Match m WHERE m.active = TRUE ORDER BY m.id")
})
public class Match {
	
	public static final String QUERY_FIND_RECENT = "Match.findRecent";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "match_type_id")
	private MatchType matchType;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private GameMap map;
	
	@Column(name = "played_at")
	private Date playedAt;
	
	private boolean active;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public MatchType getMatchType() {
		return matchType;
	}
	
	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public Date getPlayedAt() {
		return playedAt;
	}
	
	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
