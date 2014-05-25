package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchFormDto;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevel;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelDao;
import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.map.GameMapDao;
import pl.vgtworld.l4d2jsstats.user.User;
import pl.vgtworld.l4d2jsstats.user.UserDao;

@Stateless
public class MatchService {
	
	@Inject
	private MatchDao matchDao;
	
	@Inject
	private MatchCampaignDao matchCampaignDao;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private GameMapDao mapDao;
	
	@Inject
	private MatchTypeDao matchTypeDao;
	
	@Inject
	private DifficultyLevelDao difficultyDao;
	
	public void createMatch(int ownerId, int matchTypeId, AddMatchFormDto form) throws MatchServiceException {
		Match match = new Match();
		User user = userDao.findById(ownerId);
		GameMap map = mapDao.findById(form.getMapId());
		DifficultyLevel difficulty = difficultyDao.findById(form.getDifficultyId());
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
		match.setActive(false);
		match.setPlayedAt(form.getDateParsed());
		matchDao.add(match);
		
		MatchCampaign campaign = new MatchCampaign();
		campaign.setMatch(match);
		//TODO Load time and restarts from form.
		campaign.setTime(21);
		campaign.setRestarts(8);
		campaign.setDifficulty(difficulty);
		matchCampaignDao.add(campaign);
	}

}
