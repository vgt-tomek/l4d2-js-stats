package pl.vgtworld.l4d2jsstats.match;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;

@Stateless
public class MatchTypeService {
	
	@Inject
	private MatchTypeDao dao;
	
	public MatchTypeDto[] findAll() {
		MatchType[] matchTypes = dao.findAll();
		MatchTypeDto[] dtoList = new MatchTypeDto[matchTypes.length];
		for (int i = 0; i < matchTypes.length; ++i) {
			dtoList[i] = mapFrom(matchTypes[i]);
		}
		return dtoList;
	}
	
	private MatchTypeDto mapFrom(MatchType matchType) {
		MatchTypeDto dto = new MatchTypeDto();
		dto.setId(matchType.getId());
		dto.setIdentifier(matchType.getIdentifier());
		dto.setName(matchType.getName());
		return dto;
	}
	
}
