package pl.vgtworld.l4d2jsstats.player;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.l4d2jsstats.player.dto.TeammateDto;

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
	
	public TeammateDto[] getMostPopularTeammates(int userId) {
		return getUserTeammates(userId, PlayerVersus.QUERY_NATIVE_MOST_POPULAR_TEAMMATES);
	}
	
	public TeammateDto[] getMostPopularWinningTeammates(int userId) {
		return getUserTeammates(userId, PlayerVersus.QUERY_NATIVE_MOST_POPULAR_WINNING_TEAMMATES);
	}

	@SuppressWarnings("unchecked")
	private TeammateDto[] getUserTeammates(int userId, String queryyyy) {
		Query query = em.createNativeQuery(queryyyy);
		query.setParameter("userId", userId);
		List<Object[]> result = query.getResultList();
		List<TeammateDto> convertedResult = new ArrayList<TeammateDto>();
		for (Object[] row : result) {
			String userName = (String)row[0];
			BigInteger gamesPlayed = (BigInteger)row[1];
			TeammateDto dto = new TeammateDto(userName, gamesPlayed.longValue());
			convertedResult.add(dto);
		}
		return convertedResult.toArray(new TeammateDto[result.size()]);
	}
	
}
