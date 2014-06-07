package pl.vgtworld.l4d2jsstats.match;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matches_versus")
@NamedQueries({
		@NamedQuery(name = MatchVersus.QUERY_TOP_POINTS_ON_MAP,
			query = "SELECT MAX(m.winnerPoints) FROM MatchVersus m WHERE m.match.map.id = :mapId AND m.match.active = TRUE")
})
public class MatchVersus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String QUERY_TOP_POINTS_ON_MAP = "MatchVersus.topPointsOnMap";
	
	@Id
	@OneToOne
	private Match match;
	
	@Column(name = "winner_points")
	private int winnerPoints;
	
	@Column(name = "loser_points")
	private int loserPoints;
	
	public Match getMatch() {
		return match;
	}
	
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public int getWinnerPoints() {
		return winnerPoints;
	}
	
	public void setWinnerPoints(int winnerPoints) {
		this.winnerPoints = winnerPoints;
	}
	
	public int getLoserPoints() {
		return loserPoints;
	}
	
	public void setLoserPoints(int loserPoints) {
		this.loserPoints = loserPoints;
	}
	
}
