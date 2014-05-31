package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchFormDto;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevel;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelDao;
import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.map.GameMapDao;
import pl.vgtworld.l4d2jsstats.match.dto.CampaignMatchDto;
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
	
	public CampaignMatchDto findCampaignById(int matchId) {
		MatchCampaign match = matchCampaignDao.findById(matchId);
		if (match == null) {
			return null;
		}
		return mapFrom(match);
	}
	
	public void activateMatch(int matchId) throws MatchServiceException {
		Match match = matchDao.findById(matchId);
		if (match == null) {
			throw new MatchServiceException("Match doesn't exist.");
		}
		match.setActive(true);
	}
	
	public int createMatch(int ownerId, int matchTypeId, AddMatchFormDto form) throws MatchServiceException {
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
		campaign.setTime(form.getTotalTimeParsed());
		campaign.setRestarts(form.getRestarts());
		campaign.setDifficulty(difficulty);
		matchCampaignDao.add(campaign);
		
		return match.getId();
	}

	private CampaignMatchDto mapFrom(MatchCampaign match) {
		CampaignMatchDto dto = new CampaignMatchDto();
		dto.setId(match.getMatch().getId());
		dto.setOwnerId(match.getMatch().getOwner().getId());
		dto.setMapId(match.getMatch().getMap().getId());
		dto.setMapName(match.getMatch().getMap().getName());
		dto.setPlayedAt(match.getMatch().getPlayedAt());
		dto.setTotalTime(match.getTime());
		dto.setDifficultyName(match.getDifficulty().getName());
		dto.setRestarts(match.getRestarts());
		return dto;
	}

}
