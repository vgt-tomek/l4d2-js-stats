package pl.vgtworld.l4d2jsstats.difficulty;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;

@Stateless
public class DifficultyLevelService {
	
	@Inject
	private DifficultyLevelDao dao;
	
	public DifficultyLevelDto[] findAll() {
		DifficultyLevel[] difficultyLevels = dao.findAll();
		DifficultyLevelDto[] dtoList = new DifficultyLevelDto[difficultyLevels.length];
		for (int i = 0; i < difficultyLevels.length; ++i) {
			dtoList[i] = mapFrom(difficultyLevels[i]);
		}
		return dtoList;
	}
	
	private DifficultyLevelDto mapFrom(DifficultyLevel difficulty) {
		DifficultyLevelDto dto = new DifficultyLevelDto();
		dto.setId(difficulty.getId());
		dto.setName(difficulty.getName());
		return dto;
	}
}
