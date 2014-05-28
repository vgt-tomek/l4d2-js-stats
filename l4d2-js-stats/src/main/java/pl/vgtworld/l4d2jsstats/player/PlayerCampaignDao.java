package pl.vgtworld.l4d2jsstats.player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlayerCampaignDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(PlayerCampaign player) {
		em.persist(player);
	}
	
}
