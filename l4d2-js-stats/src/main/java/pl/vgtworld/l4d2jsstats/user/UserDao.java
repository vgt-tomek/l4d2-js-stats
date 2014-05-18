package pl.vgtworld.l4d2jsstats.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(User user) {
		em.persist(user);
	}
	
	@SuppressWarnings("unchecked")
	public User findByLogin(String login) {
		Query query = em.createNamedQuery(User.QUERY_FIND_BY_LOGIN);
		query.setParameter("login", login);
		List<User> results = query.getResultList();
		if (results.size() == 0) {
			return null;
		}
		return results.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public User[] findActive() {
		Query query = em.createNamedQuery(User.QUERY_FIND_ACTIVE);
		List<User> results = query.getResultList();
		return results.toArray(new User[results.size()]);
	}
}
