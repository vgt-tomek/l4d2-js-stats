package pl.vgtworld.l4d2jsstats.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(User user) {
		em.persist(user);
	}
	
	public User findById(int userId) {
		return em.find(User.class, userId);
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
	
	public boolean changeUserPassword(int userId, String salt, String passwordHash) {
		User user = findById(userId);
		if (user == null) {
			LOGGER.warn("No user found. Unable to change password. userId:{}", userId);
			return false;
		}
		user.setSalt(salt);
		user.setPassword(passwordHash);
		return true;
	}
	
}
