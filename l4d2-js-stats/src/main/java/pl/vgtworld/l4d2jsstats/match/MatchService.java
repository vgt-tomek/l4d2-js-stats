package pl.vgtworld.l4d2jsstats.match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.Days;

import pl.vgtworld.l4d2jsstats.addmatch.campaign.AddCampaignMatchFormDto;
import pl.vgtworld.l4d2jsstats.addmatch.versus.AddVersusMatchFormDto;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevel;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelDao;
import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.map.GameMapDao;
import pl.vgtworld.l4d2jsstats.match.dto.CampaignMatchDto;
import pl.vgtworld.l4d2jsstats.match.dto.MapBreakDto;
import pl.vgtworld.l4d2jsstats.match.dto.MatchDto;
import pl.vgtworld.l4d2jsstats.match.dto.RecentMatchDto;
import pl.vgtworld.l4d2jsstats.match.dto.UserActivityDto;
import pl.vgtworld.l4d2jsstats.match.dto.VersusMatchDto;
import pl.vgtworld.l4d2jsstats.player.PlayerCampaignDao;
import pl.vgtworld.l4d2jsstats.player.PlayerVersusDao;
import pl.vgtworld.l4d2jsstats.storage.Storage;
import pl.vgtworld.l4d2jsstats.user.User;
import pl.vgtworld.l4d2jsstats.user.UserDao;

@Stateless
public class MatchService {
	
	private static final String IMAGE_ATTACHMENT_TEMPLATE = "%1$tY-%1$tm-%1$td_%1$tH.%1$tM.%1$tS_%2$d_%3$d.jpg";
	
	@Inject
	private MatchDao matchDao;
	
	@Inject
	private MatchCampaignDao matchCampaignDao;
	
	@Inject
	private MatchVersusDao matchVersusDao;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private GameMapDao mapDao;
	
	@Inject
	private MatchTypeDao matchTypeDao;
	
	@Inject
	private PlayerCampaignDao playerCampaignDao;
	
	@Inject
	private PlayerVersusDao playerVersusDao;
	
	@Inject
	private Storage storage;
	
	@Inject
	private DifficultyLevelDao difficultyDao;
	
	public MatchDto findMatchById(int matchId) {
		Match match = matchDao.findById(matchId);
		if (match == null) {
			return null;
		}
		return mapMatchFrom(match);
	}
	
	public CampaignMatchDto findCampaignById(int matchId) {
		MatchCampaign match = matchCampaignDao.findById(matchId);
		if (match == null) {
			return null;
		}
		return mapFrom(match);
	}
	
	public VersusMatchDto findVersusById(int matchId) {
		MatchVersus match = matchVersusDao.findById(matchId);
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
	
	public int createCampaignMatch(int ownerId, AddCampaignMatchFormDto form)
		throws MatchServiceException {
		Match match = new Match();
		User user = userDao.findById(ownerId);
		GameMap map = mapDao.findById(form.getMapId());
		DifficultyLevel difficulty = difficultyDao.findById(form.getDifficultyId());
		MatchType matchType = matchTypeDao.getCampaignMatchType();
		if (user == null) {
			throw new MatchServiceException("Unknown user.");
		}
		if (map == null) {
			throw new MatchServiceException("Unknown map.");
		}
		
		String imageAttachmentFilename = null;
		if (form.getImage().length > 0) {
			imageAttachmentFilename = String.format(
				IMAGE_ATTACHMENT_TEMPLATE,
				new Date(),
				user.getId(),
				form.getMapId()
				);
			saveImageAttachment(form.getImage(), imageAttachmentFilename);
		}
		
		match.setMatchType(matchType);
		match.setOwner(user);
		match.setMap(map);
		match.setActive(false);
		match.setPlayedAt(form.getDateParsed());
		match.setImageName(imageAttachmentFilename);
		matchDao.add(match);
		
		MatchCampaign campaign = new MatchCampaign();
		campaign.setMatch(match);
		campaign.setTime(form.getTotalTimeParsed());
		campaign.setRestarts(form.getRestarts());
		campaign.setDifficulty(difficulty);
		matchCampaignDao.add(campaign);
		
		return match.getId();
	}
	
	public int createVersusMatch(int ownerId, AddVersusMatchFormDto form) throws MatchServiceException {
		Match match = new Match();
		User user = userDao.findById(ownerId);
		GameMap map = mapDao.findById(form.getMapId());
		MatchType matchType = matchTypeDao.getVersusMatchType();
		
		if (user == null) {
			throw new MatchServiceException("Unknown user.");
		}
		if (map == null) {
			throw new MatchServiceException("Unknown map.");
		}
		
		String imageAttachmentFilename = null;
		if (form.getImage().length > 0) {
			imageAttachmentFilename = String.format(
				IMAGE_ATTACHMENT_TEMPLATE,
				new Date(),
				user.getId(),
				form.getMapId()
				);
			saveImageAttachment(form.getImage(), imageAttachmentFilename);
		}
		
		match.setMatchType(matchType);
		match.setOwner(user);
		match.setMap(map);
		match.setActive(false);
		match.setPlayedAt(form.getDateParsed());
		match.setImageName(imageAttachmentFilename);
		matchDao.add(match);
		
		MatchVersus versus = new MatchVersus();
		versus.setMatch(match);
		versus.setWinnerPoints(form.getWinnerPoints());
		versus.setLoserPoints(form.getLoserPoints());
		matchVersusDao.add(versus);
		
		return match.getId();
	}
	
	public RecentMatchDto[] findRecentMatches(int count, int offset) {
		Match[] recentMatches = matchDao.findRecentMatches(count, offset);
		return mapFrom(recentMatches);
	}
	
	public RecentMatchDto[] findRecentMatchesFromMap(int mapId, int count) {
		Match[] recentMatches = matchDao.findRecentMatchesFromMap(mapId, count);
		return mapFrom(recentMatches);
	}
	
	public RecentMatchDto[] findRecentMatchesForUser(int userId, int count) {
		Match[] recentMatches = matchDao.findRecentMatchesForUser(userId, count);
		return mapFrom(recentMatches);
	}
	
	public MapBreakDto[] getMapsByLongestBreak() {
		GameMap[] maps = mapDao.findAll();
		List<MapBreakDto> dtoList = new ArrayList<>();
		for (GameMap map : maps) {
			Match[] recentMatches = matchDao.findRecentMatchesFromMap(map.getId(), 1);
			
			MapBreakDto dto = new MapBreakDto();
			dto.setMapId(map.getId());
			dto.setMapName(map.getName());
			if (recentMatches.length == 1) {
				Date matchDate = recentMatches[0].getPlayedAt();
				int dayDifference = Days.daysBetween(new DateTime(matchDate), new DateTime()).getDays();
				dto.setBreakDayCount(dayDifference);
			}
			dtoList.add(dto);
		}
		
		Collections.sort(dtoList);
		return dtoList.toArray(new MapBreakDto[dtoList.size()]);
	}
	
	public UserActivityDto[] getUsersActivity() {
		User[] users = userDao.findActive();
		List<UserActivityDto> dtoList = new ArrayList<>();
		for (User user : users) {
			Match[] recentMatches = matchDao.findRecentMatchesForUser(user.getId(), 1);
			
			UserActivityDto dto = new UserActivityDto();
			dto.setUserId(user.getId());
			dto.setUserName(user.getLogin());
			if (recentMatches.length == 1) {
				Date matchDate = recentMatches[0].getPlayedAt();
				int dayDifference = Days.daysBetween(new DateTime(matchDate), new DateTime()).getDays();
				dto.setDaysInactive(dayDifference);
			}
			dtoList.add(dto);
		}
		
		Collections.sort(dtoList);
		return dtoList.toArray(new UserActivityDto[dtoList.size()]);
	}
	
	private CampaignMatchDto mapFrom(MatchCampaign match) {
		int matchId = match.getMatch().getId();
		
		CampaignMatchDto dto = new CampaignMatchDto();
		dto.setId(matchId);
		dto.setOwnerId(match.getMatch().getOwner().getId());
		dto.setMapId(match.getMatch().getMap().getId());
		dto.setMapName(match.getMatch().getMap().getName());
		dto.setPlayedAt(match.getMatch().getPlayedAt());
		dto.setImage(match.getMatch().getImageName());
		dto.setTotalTime(match.getTime());
		dto.setDifficultyName(match.getDifficulty().getName());
		dto.setRestarts(match.getRestarts());
		
		long survivedPlayerCount = playerCampaignDao.getSurvivedPlayersCountForCampaignMatch(matchId);
		long totalPlayerCount = playerCampaignDao.getTotalPlayersCountForCampaignMatch(matchId);
		dto.setPlayerCount(totalPlayerCount);
		dto.setSurvivedPlayerCount(survivedPlayerCount);
		return dto;
	}
	
	private VersusMatchDto mapFrom(MatchVersus match) {
		int matchId = match.getMatch().getId();
		
		VersusMatchDto dto = new VersusMatchDto();
		dto.setId(matchId);
		dto.setOwnerId(match.getMatch().getOwner().getId());
		dto.setMapId(match.getMatch().getMap().getId());
		dto.setMapName(match.getMatch().getMap().getName());
		dto.setPlayedAt(match.getMatch().getPlayedAt());
		dto.setImage(match.getMatch().getImageName());
		dto.setWinnerPoints(match.getWinnerPoints());
		dto.setLoserPoints(match.getLoserPoints());
		
		long totalPlayerCount = playerVersusDao.getTotalPlayersCountForMatch(matchId);
		dto.setPlayerCount(totalPlayerCount);
		return dto;
	}
	
	private RecentMatchDto mapFrom(Match match) {
		RecentMatchDto dto = new RecentMatchDto();
		dto.setId(match.getId());
		dto.setType(match.getMatchType().getName());
		dto.setTypeIdentifier(match.getMatchType().getIdentifier());
		dto.setMapId(match.getMap().getId());
		dto.setMapName(match.getMap().getName());
		dto.setPlayedAt(match.getPlayedAt());
		return dto;
	}
	
	private MatchDto mapMatchFrom(Match match) {
		MatchDto dto = new MatchDto();
		dto.setId(match.getId());
		dto.setMatchType(match.getMatchType().getName());
		dto.setMapId(match.getMap().getId());
		dto.setMapName(match.getMap().getName());
		dto.setPlayedAt(match.getPlayedAt());
		dto.setImageName(match.getImageName());
		dto.setActive(match.isActive());
		return dto;
	}
	
	private RecentMatchDto[] mapFrom(Match[] recentMatches) {
		RecentMatchDto[] dtoList = new RecentMatchDto[recentMatches.length];
		for (int i = 0; i < recentMatches.length; ++i) {
			dtoList[i] = mapFrom(recentMatches[i]);
		}
		return dtoList;
	}
	
	private void saveImageAttachment(byte[] bytes, String imageAttachmentFilename) throws MatchServiceException {
		try {
			storage.saveMatchScreenshot(bytes, imageAttachmentFilename);
		} catch (IOException e) {
			throw new MatchServiceException("Unexpected error while trying to save image attachment.", e);
		}
	}
	
}
