package pl.vgtworld.l4d2jsstats.match;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MatchTypeDao {
	
	public static final int CAMPAIGN_MATCH_TYPE_ID = 1;
	
	public static final int VERSUS_MATCH_TYPE_ID = 2;
	
	@PersistenceContext
	private EntityManager em;
	
	public MatchType findById(int matchTypeId) {
		return em.find(MatchType.class, matchTypeId);
	}
	
	@SuppressWarnings("unchecked")
	public MatchType[] findAll() {
		Query query = em.createNamedQuery(MatchType.QUERY_FIND_ALL);
		List<MatchType> result = query.getResultList();
		return result.toArray(new MatchType[result.size()]);
	}
	
	public MatchType getCampaignMatchType() {
		return findById(CAMPAIGN_MATCH_TYPE_ID);
	}
	
	public MatchType getVersusMatchType() {
		return findById(VERSUS_MATCH_TYPE_ID);
	}
	
}
