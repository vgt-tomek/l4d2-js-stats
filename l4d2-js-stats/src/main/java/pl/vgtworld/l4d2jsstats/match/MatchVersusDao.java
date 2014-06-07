package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MatchVersusDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(MatchVersus match) {
		em.persist(match);
	}
	
	public MatchVersus findById(int matchId) {
		return em.find(MatchVersus.class, matchId);
	}
	
	public int getTopWinnerPointsOnMap(int mapId) {
		Query query = em.createNamedQuery(MatchVersus.QUERY_TOP_POINTS_ON_MAP);
		query.setParameter("mapId", mapId);
		Object topScore = query.getSingleResult();
		if (topScore == null) {
			return 0;
		}
		return (int)topScore;
	}
	
}
