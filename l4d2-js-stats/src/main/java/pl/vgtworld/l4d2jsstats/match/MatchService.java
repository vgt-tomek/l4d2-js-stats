package pl.vgtworld.l4d2jsstats.match;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.map.GameMapDao;
import pl.vgtworld.l4d2jsstats.user.User;
import pl.vgtworld.l4d2jsstats.user.UserDao;

@Stateless
public class MatchService {
	
	@Inject
	private MatchDao dao;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private GameMapDao mapDao;
	
	@Inject
	private MatchTypeDao matchTypeDao;
	
	public void createMatch(int ownerId, int mapId, int matchTypeId) throws MatchServiceException {
		Match match = new Match();
		User user = userDao.findById(ownerId);
		GameMap map = mapDao.findById(mapId);
		MatchType matchType = matchTypeDao.findById(matchTypeId);
		if (user == null) {
			throw new MatchServiceException("Unknown user.");
		}
		if (map == null) {
			throw new MatchServiceException("Unknown map.");
		}
		match.setMatchType(matchType);
		match.setOwner(user);
		match.setMap(map);
		//TODO Use time from the form.
		match.setPlayedAt(new Date());
		dao.add(match);
	}
}
