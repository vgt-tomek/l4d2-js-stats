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
			query = "SELECT m FROM Match m WHERE m.active = TRUE ORDER BY m.id DESC"),
		@NamedQuery(name = Match.QUERY_FIND_RECENT_FROM_MAP,
			query = "SELECT m FROM Match m WHERE m.active = TRUE AND m.map.id = :mapId ORDER BY m.id DESC"),
		@NamedQuery(name = Match.QUERY_FIND_RECENT_FOR_USER,
			query = "SELECT p.match FROM Player p JOIN p.match m "
				+ "WHERE m.active = TRUE and p.user.id = :userId ORDER by p.id DESC"),
		@NamedQuery(name = Match.QUERY_TOTAL_MATCHES_PLAYED_ON_MAP,
			query = "SELECT COUNT(m) FROM Match m WHERE "
				+ "m.map.id = :mapId AND m.matchType.id = :matchTypeId AND m.active = TRUE"),
		@NamedQuery(name = Match.QUERY_TOTAL_MATCHES_PLAYED,
			query = "SELECT COUNT(m) FROM Match m WHERE m.active = TRUE")
})
public class Match {
	
	public static final String QUERY_FIND_RECENT = "Match.findRecent";
	
	public static final String QUERY_FIND_RECENT_FROM_MAP = "Match.findRecentFromMap";
	
	public static final String QUERY_TOTAL_MATCHES_PLAYED_ON_MAP = "Match.totalMatchesPlayedOnMap";
	
	public static final String QUERY_FIND_RECENT_FOR_USER = "Match.findRecentForUser";
	
	public static final String QUERY_TOTAL_MATCHES_PLAYED = "Match.totalMatchesPlayed";
	
	public static final String QUERY_NATIVE_MATCH_COUNT_MONTHLY =
		"SELECT COUNT(*) AS match_count, YEAR(played_at) AS year, MONTH(played_at) AS MONTH "
			+ "FROM matches m "
			+ "WHERE m.active = TRUE "
			+ "GROUP BY YEAR(played_at), MONTH(played_at) "
			+ "ORDER BY year, month";
	
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
	
	@Column(name = "image_name")
	private String imageName;
	
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
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
