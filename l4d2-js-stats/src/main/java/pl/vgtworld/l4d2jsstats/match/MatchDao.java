package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MatchDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Match match) {
		em.persist(match);
	}
	
	public Match findById(int matchId) {
		return em.find(Match.class, matchId);
	}
	
}
