package pl.vgtworld.l4d2jsstats.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserTokenService {
	
	@Inject
	private UserTokenDao dao;
	
	public String createNewToken(User user, String remoteAddress) throws UserTokenServiceException {
		try {
			String generatedToken = UserUtils.generateToken(user.getLogin() + System.currentTimeMillis());
			UserToken token = new UserToken();
			token.setUser(user);
			token.setToken(generatedToken);
			token.setIp(remoteAddress);
			token.setCreatedAt(new Date());
			dao.add(token);
			return generatedToken;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UserTokenServiceException("Unexpected error while trying to create new token.", e);
		}
	}
	
	public String findTokenForUser(User user) {
		return dao.findLastToken(user.getId());
	}
	
}
