package pl.vgtworld.l4d2jsstats.map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

@Stateless
public class GameMapService {
	
	@Inject
	private GameMapDao dao;
	
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
	
	private GameMapDto mapFrom(GameMap map) {
		GameMapDto dto = new GameMapDto();
		dto.setId(map.getId());
		dto.setName(map.getName());
		dto.setImage(map.getImage());
		return dto;
	}
}
