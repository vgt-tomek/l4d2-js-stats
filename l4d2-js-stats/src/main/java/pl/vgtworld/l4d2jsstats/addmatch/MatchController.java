package pl.vgtworld.l4d2jsstats.addmatch;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchTypeService;
import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;

@Path("/match")
public class MatchController extends BaseController {
	
	@Inject
	private MatchTypeService matchTypeService;
	
	@Inject
	private GameMapService mapService;
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchPicker() {
		setPageTitle("Add match");
		MatchTypeDto[] matchTypes = matchTypeService.findAll();
		request.setAttribute("matchTypes", matchTypes);
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute("maps", maps);
		return render("add-match");
	}
}
