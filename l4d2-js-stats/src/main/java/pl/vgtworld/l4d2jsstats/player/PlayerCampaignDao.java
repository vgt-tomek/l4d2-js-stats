package pl.vgtworld.l4d2jsstats.player;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PlayerCampaignDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(PlayerCampaign player) {
		em.persist(player);
	}
	
	@SuppressWarnings("unchecked")
	public PlayerCampaign[] findByMatch(int matchId) {
		Query query = em.createNamedQuery(PlayerCampaign.QUERY_FIND_FROM_MATCH);
		query.setParameter("matchId", matchId);
		List<PlayerCampaign> result = query.getResultList();
		return result.toArray(new PlayerCampaign[result.size()]);
	}
	
}
