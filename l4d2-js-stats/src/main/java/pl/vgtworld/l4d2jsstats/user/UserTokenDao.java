package pl.vgtworld.l4d2jsstats.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserTokenDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(UserToken token) {
		em.persist(token);
	}
	
	@SuppressWarnings("unchecked")
	public String findLastToken(int userId) {
		Query query = em.createNamedQuery(UserToken.QUERY_USER_LAST_TOKEN);
		query.setParameter("userId", userId);
		query.setMaxResults(1);
		List<UserToken> result = query.getResultList();
		if (result.size() == 1) {
			return result.get(0).getToken();
		}
		return null;
	}
}
