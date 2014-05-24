package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Form;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelService;
import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class CampaignController extends BaseController {
	
	private static final String FORM_VIEW_NAME = "add-match-campaign";

	private static final String DIFFICULTY_LEVELS_REQUEST_ATTRIBUTE = "difficultyLevels";

	private static final String MAPS_REQUEST_ATTRIBUTE = "maps";

	private static final String ACTIVE_USERS_REQUEST_ATTRIBUTE = "activeUsers";

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
		request.setAttribute(ACTIVE_USERS_REQUEST_ATTRIBUTE, activeUsers);
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute(MAPS_REQUEST_ATTRIBUTE, maps);
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		request.setAttribute(DIFFICULTY_LEVELS_REQUEST_ATTRIBUTE, difficultyLevels);
		return render(FORM_VIEW_NAME);
	}
	
	@POST
	@Path("add-campaign")
	@Produces(MediaType.TEXT_HTML)
	public String submitMatch(@Form CampaignFormDto form) {
		GameMapDto[] maps = mapService.findAll();
		UserDto[] activeUsers = userService.findActiveUsers();
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		CampaignFormValidator validator = new CampaignFormValidator();
		boolean validationResult = validator.validate(form, maps, difficultyLevels, activeUsers);
		if (!validationResult) {
			request.setAttribute(ACTIVE_USERS_REQUEST_ATTRIBUTE, activeUsers);
			request.setAttribute(MAPS_REQUEST_ATTRIBUTE, maps);
			request.setAttribute(DIFFICULTY_LEVELS_REQUEST_ATTRIBUTE, difficultyLevels);
			request.setAttribute("errors", validator.getErrors());
			return render(FORM_VIEW_NAME);
		}
		return "ok";
	}
}
