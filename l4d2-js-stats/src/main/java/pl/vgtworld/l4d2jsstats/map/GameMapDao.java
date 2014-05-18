package pl.vgtworld.l4d2jsstats.map;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GameMapDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public GameMap[] findAll() {
		Query query = em.createNamedQuery(GameMap.QUERY_FIND_ALL);
		List<GameMap> results = query.getResultList();
		return results.toArray(new GameMap[results.size()]);
	}
}
