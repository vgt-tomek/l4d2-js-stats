package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
}
