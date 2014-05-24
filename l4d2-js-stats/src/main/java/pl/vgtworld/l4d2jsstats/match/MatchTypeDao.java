package pl.vgtworld.l4d2jsstats.match;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MatchTypeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public MatchType[] findAll() {
		Query query = em.createNamedQuery(MatchType.QUERY_FIND_ALL);
		List<MatchType> result = query.getResultList();
		return result.toArray(new MatchType[result.size()]);
	}
}
