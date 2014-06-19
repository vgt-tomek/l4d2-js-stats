package pl.vgtworld.l4d2jsstats.map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapGeneralStatisticsDto;
import pl.vgtworld.l4d2jsstats.map.dto.MostPlayedMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchDao;
import pl.vgtworld.l4d2jsstats.match.MatchVersusDao;
import pl.vgtworld.l4d2jsstats.player.PlayerCampaignDao;

@Stateless
public class GameMapService {
	
	@Inject
	private GameMapDao dao;
	
	@Inject
	private MatchDao matchDao;
	
	@Inject
	private MatchVersusDao versusDao;
	
	@Inject
	private PlayerCampaignDao playerCampaignDao;
	
	public GameMapDto findById(int mapId) {
		GameMap map = dao.findById(mapId);
		if (map == null) {
			return null;
		}
		return mapFrom(map);
	}
	
	public GameMapDto[] findAll() {
		GameMap[] maps = dao.findAll();
		GameMapDto[] dtoList = new GameMapDto[maps.length];
		for (int i = 0; i < maps.length; ++i) {
			dtoList[i] = mapFrom(maps[i]);
		}
		return dtoList;
	}
	
	public GameMapGeneralStatisticsDto getMapStatistics(int mapId) {
		GameMapGeneralStatisticsDto dto = new GameMapGeneralStatisticsDto();
		if (dao.findById(mapId) == null) {
			return dto;
		}
		long totalMatchesPlayed = matchDao.getTotalMatchesPlayedOnMap(mapId);
		dto.setTotalMatchesPlayed(totalMatchesPlayed);
		int topWinnerPoints = versusDao.getTopWinnerPointsOnMap(mapId);
		dto.setTopVersusPoints(topWinnerPoints);
		long totalPlayerCount = playerCampaignDao.getTotalPlayersCountOnCampaignMap(mapId);
		dto.setTotalCampaignPlayerCount(totalPlayerCount);
		long survivedPlayerCount = playerCampaignDao.getSurvivedPlayersCountOnCampaignMap(mapId);
		dto.setSurvivedCampaignPlayerCount(survivedPlayerCount);
		float averageDeathPerMatch = calculateAverageDeathCount(mapId, totalMatchesPlayed);
		dto.setAverageCampaignDeathCount(averageDeathPerMatch);
		return dto;
	}
	
	public MostPlayedMapDto[] getMostPlayedMaps() {
		return dao.getMostPlayedMaps();
	}
	
	private GameMapDto mapFrom(GameMap map) {
		GameMapDto dto = new GameMapDto();
		dto.setId(map.getId());
		dto.setName(map.getName());
		dto.setImage(map.getImage());
		return dto;
	}
	
	private float calculateAverageDeathCount(int mapId, long totalMatchesPlayed) {
		long totalDeaths = playerCampaignDao.getTotalDeathCountOnCampaignMap(mapId);
		float averageDeathPerMatch = totalDeaths / (float) totalMatchesPlayed;
		return averageDeathPerMatch;
	}
	
}
