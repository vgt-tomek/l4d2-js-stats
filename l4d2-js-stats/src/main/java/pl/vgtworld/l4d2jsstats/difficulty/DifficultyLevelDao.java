package pl.vgtworld.l4d2jsstats.difficulty;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DifficultyLevelDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DifficultyLevel[] findAll() {
		Query query = em.createNamedQuery(DifficultyLevel.QUERY_FIND_ALL);
		List<DifficultyLevel> results = query.getResultList();
		return results.toArray(new DifficultyLevel[results.size()]);
	}
	
}
