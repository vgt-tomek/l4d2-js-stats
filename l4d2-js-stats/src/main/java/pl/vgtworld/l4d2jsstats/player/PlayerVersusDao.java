package pl.vgtworld.l4d2jsstats.player;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PlayerVersusDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(PlayerVersus player) {
		em.persist(player);
	}
	
	@SuppressWarnings("unchecked")
	public PlayerVersus[] findByMatch(int matchId) {
		Query query = em.createNamedQuery(PlayerVersus.QUERY_FIND_FROM_MATCH);
		query.setParameter("matchId", matchId);
		List<PlayerVersus> result = query.getResultList();
		return result.toArray(new PlayerVersus[result.size()]);
	}
	
	public long getTotalPlayersCountForMatch(int matchId) {
		Query query = em.createNamedQuery(PlayerVersus.QUERY_COUNT_TOTAL_PLAYERS_FOR_MATCH);
		query.setParameter("matchId", matchId);
		return (long) query.getSingleResult();
	}
	
}
