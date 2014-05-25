package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MatchCampaignDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(MatchCampaign match) {
		em.persist(match);
	}
	
}
