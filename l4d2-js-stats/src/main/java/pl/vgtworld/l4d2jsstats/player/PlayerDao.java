package pl.vgtworld.l4d2jsstats.player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlayerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Player player) {
		em.persist(player);
	}
	
}
