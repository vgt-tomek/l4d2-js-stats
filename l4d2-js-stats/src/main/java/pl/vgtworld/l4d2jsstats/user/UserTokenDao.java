package pl.vgtworld.l4d2jsstats.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserTokenDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(UserToken token) {
		em.persist(token);
	}
}
