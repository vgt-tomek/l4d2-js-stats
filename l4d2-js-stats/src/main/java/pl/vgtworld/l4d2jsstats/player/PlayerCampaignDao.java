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
	
	public long getTotalPlayersCountOnCampaignMap(int mapId) {
		Query query = em.createNamedQuery(PlayerCampaign.QUERY_COUNT_TOTAL_PLAYERS_FOR_MAP);
		query.setParameter("mapId", mapId);
		return (long) (query.getSingleResult());
	}
	
	public long getSurvivedPlayersCountOnCampaignMap(int mapId) {
		Query query = em.createNamedQuery(PlayerCampaign.QUERY_COUNT_SURVIVED_PLAYERS_FOR_MAP);
		query.setParameter("mapId", mapId);
		return (long) (query.getSingleResult());
	}
	
	public long getTotalDeathCountOnCampaignMap(int mapId) {
		Query query = em.createNamedQuery(PlayerCampaign.QUERY_COUNT_PLAYER_DEATHS_FOR_MAP);
		query.setParameter("mapId", mapId);
		Object result = query.getSingleResult();
		if (result == null) {
			return 0;
		}
		return (long)result;
	}
	
}
