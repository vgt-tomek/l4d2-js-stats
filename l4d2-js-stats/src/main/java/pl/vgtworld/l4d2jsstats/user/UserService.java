package pl.vgtworld.l4d2jsstats.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
	
	@Inject
	private UserDao dao;
	
	public void createNewUser(String login, String password) throws UserServiceException {
		try {
			String salt = UserUtils.generateSalt();
			String passwordHash = UserUtils.generatePasswordHash(password, salt);
			User user = new User();
			user.setLogin(login);
			user.setPassword(passwordHash);
			user.setSalt(salt);
			user.setActive(false);
			user.setCreatedAt(new Date());
			dao.add(user);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UserServiceException("Enexpected error while trying to create user.", e);
		}
	}
	
	public boolean isLoginAvailable(String login) {
		return dao.findByLogin(login) == null;
	}
	
}
