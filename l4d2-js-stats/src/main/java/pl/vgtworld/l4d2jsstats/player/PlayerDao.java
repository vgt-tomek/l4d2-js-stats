package pl.vgtworld.l4d2jsstats.player;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.l4d2jsstats.player.dto.MostActivePlayerDto;

@Stateless
public class PlayerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Player player) {
		em.persist(player);
	}
	
	public void delete(int userId, int matchId) {
		Query query = em.createNamedQuery(Player.QUERY_DELETE_USER_FROM_MATCH);
		query.setParameter("userId", userId);
		query.setParameter("matchId", matchId);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public MostActivePlayerDto[] getMostActivePlayers() {
		Query query = em.createNamedQuery(Player.QUERY_MOST_ACTIVE);
		List<MostActivePlayerDto> result = query.getResultList();
		return result.toArray(new MostActivePlayerDto[result.size()]);
	}
	
	public long getMatchCountForPlayer(int userId, int matchTypeId) {
		Query query = em.createNamedQuery(Player.QUERY_MATCH_COUNT);
		query.setParameter("userId", userId);
		query.setParameter("matchTypeId", matchTypeId);
		Object result = query.getSingleResult();
		if (result == null) {
			return 0;
		}
		return (long) result;
	}
	
	public long getSurvivedCampaignCount(int userId) {
		Query query = em.createNamedQuery(Player.QUERY_SURVIVED_CAMPAIGN_COUNT);
		query.setParameter("userId", userId);
		Object result = query.getSingleResult();
		if (result == null) {
			return 0;
		}
		return (long) result;
	}
	
	public long getWonVersusCount(int userId) {
		Query query = em.createNamedQuery(Player.QUERY_WON_VERSUS_COUNT);
		query.setParameter("userId", userId);
		Object result = query.getSingleResult();
		if (result == null) {
			return 0;
		}
		return (long) result;
	}
	
}
