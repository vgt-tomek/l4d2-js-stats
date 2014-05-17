package pl.vgtworld.l4d2jsstats.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(UserEntity user) {
		em.persist(user);
	}
	
}
