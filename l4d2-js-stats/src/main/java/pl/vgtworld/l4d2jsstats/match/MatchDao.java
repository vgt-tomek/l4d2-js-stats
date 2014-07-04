package pl.vgtworld.l4d2jsstats.match;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.l4d2jsstats.match.dto.MatchCountMonthlyDto;

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
	
	@SuppressWarnings("unchecked")
	public Match[] findRecentMatches(int count, int offset) {
		Query query = em.createNamedQuery(Match.QUERY_FIND_RECENT);
		query.setMaxResults(count);
		query.setFirstResult(offset);
		List<Match> result = query.getResultList();
		return result.toArray(new Match[result.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public Match[] findRecentMatchesFromMap(int mapId, int count) {
		Query query = em.createNamedQuery(Match.QUERY_FIND_RECENT_FROM_MAP);
		query.setParameter("mapId", mapId);
		query.setMaxResults(count);
		List<Match> result = query.getResultList();
		return result.toArray(new Match[result.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public Match[] findRecentMatchesForUser(int userId, int count) {
		Query query = em.createNamedQuery(Match.QUERY_FIND_RECENT_FOR_USER);
		query.setParameter("userId", userId);
		query.setMaxResults(count);
		List<Match> result = query.getResultList();
		return result.toArray(new Match[result.size()]);
	}
	
	public long getTotalMatchesPlayedOnMap(int mapId, int matchTypeId) {
		Query query = em.createNamedQuery(Match.QUERY_TOTAL_MATCHES_PLAYED_ON_MAP);
		query.setParameter("mapId", mapId);
		query.setParameter("matchTypeId", matchTypeId);
		return (long) (query.getSingleResult());
	}
	
	public long getTotalMatchesPlayed() {
		Query query = em.createNamedQuery(Match.QUERY_TOTAL_MATCHES_PLAYED);
		Object result = query.getSingleResult();
		if (result == null) {
			return 0;
		}
		return (long) result;
	}
	
	@SuppressWarnings("unchecked")
	public MatchCountMonthlyDto[] getMatchCountMonthly() {
		Query query = em.createNativeQuery(Match.QUERY_NATIVE_MATCH_COUNT_MONTHLY);
		List<Object[]> result = query.getResultList();
		List<MatchCountMonthlyDto> convertedResult = new ArrayList<>();
		for (Object[] row : result) {
			BigInteger matches = (BigInteger) row[0];
			int year = (int) row[1];
			int month = (int) row[2];
			MatchCountMonthlyDto dto = new MatchCountMonthlyDto();
			dto.setMatchCount(matches.longValue());
			dto.setYear(year);
			dto.setMonth(month);
			convertedResult.add(dto);
		}
		return convertedResult.toArray(new MatchCountMonthlyDto[convertedResult.size()]);
	}
	
}
