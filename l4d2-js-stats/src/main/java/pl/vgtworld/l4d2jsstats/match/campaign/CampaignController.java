package pl.vgtworld.l4d2jsstats.match.campaign;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelService;
import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class CampaignController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private DifficultyLevelService difficultyService;

	@GET
	@Path("/add-campaign")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchForm() {
		UserDto[] activeUsers = userService.findActiveUsers();
		request.setAttribute("activeUsers", activeUsers);
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute("maps", maps);
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		request.setAttribute("difficultyLevels", difficultyLevels);
		return render("add-match-campaign");
	}
}
