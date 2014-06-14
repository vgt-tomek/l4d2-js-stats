package pl.vgtworld.l4d2jsstats.map;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.l4d2jsstats.map.dto.MostPlayedMapDto;

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
	
	public GameMap findById(int mapId) {
		return em.find(GameMap.class, mapId);
	}
	
	@SuppressWarnings("unchecked")
	public MostPlayedMapDto[] getMostPlayedMaps() {
		Query query = em.createNamedQuery(GameMap.QUERY_MOST_PLAYED);
		List<MostPlayedMapDto> result = query.getResultList();
		return result.toArray(new MostPlayedMapDto[result.size()]);
	}
	
}
