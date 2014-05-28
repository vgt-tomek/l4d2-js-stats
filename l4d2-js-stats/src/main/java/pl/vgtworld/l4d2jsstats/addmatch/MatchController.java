package pl.vgtworld.l4d2jsstats.addmatch;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.l4d2jsstats.App;
import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevelService;
import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.MatchServiceException;
import pl.vgtworld.l4d2jsstats.match.MatchTypeService;
import pl.vgtworld.l4d2jsstats.match.dto.CampaignMatchDto;
import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class MatchController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";
	
	private static final String MATCH_TYPES_REQUEST_PARAM_KEY = "matchTypes";
	
	private static final String FORM_REQUEST_PARAM_KEY = "form";
	
	private static final String DIFFICULTY_LEVELS_REQUEST_PARAM_KEY = "difficultyLevels";
	
	private static final int CAMPAIGN_MATCH_TYPE_ID = 1;
	
	@Inject
	private MatchTypeService matchTypeService;
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private MatchService matchService;
	
	@Inject
	private DifficultyLevelService difficultyService;
	
	@Inject
	private UserService userService;
	
	@GET
	@Path("/add/picker")
	@Produces(MediaType.TEXT_HTML)
	public String displayMatchTypePicker() {
		setPageTitle("Pick match type");
		return render("add-match-picker");
	}
	
	@GET
	@Path("/add/campaign")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchForm() {
		setPageTitle("Add match");
		MatchTypeDto[] matchTypes = matchTypeService.findAll();
		request.setAttribute(MATCH_TYPES_REQUEST_PARAM_KEY, matchTypes);
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		request.setAttribute(DIFFICULTY_LEVELS_REQUEST_PARAM_KEY, difficultyLevels);
		
		AddMatchFormDto form = new AddMatchFormDto();
		form.setDate(new SimpleDateFormat(App.DATE_FORMAT).format(new Date()));
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return render("add-match-campaign");
	}
	
	@POST
	@Path("/add/campaign")
	@Produces(MediaType.TEXT_HTML)
	public Response submitMatch(@Form AddMatchFormDto form) {
		GameMapDto[] maps = mapService.findAll();
		UserDto user = getLoggedUser();
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		
		AddMatchValidator validator = new AddMatchValidator();
		boolean validationResult = validator.validate(form, maps, difficultyLevels);
		
		if (!validationResult) {
			request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
			request.setAttribute("errors", validator.getErrors());
			request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
			request.setAttribute(DIFFICULTY_LEVELS_REQUEST_PARAM_KEY, difficultyLevels);
			render("add-match-campaign");
		}
		
		try {
			int matchId = matchService.createMatch(user.getId(), CAMPAIGN_MATCH_TYPE_ID, form);
			String location = String.format("/match/campaign/%d/player/add", matchId);
			return seeOther(location);
		} catch (MatchServiceException e) {
			LOGGER.warn("Exception while trying to create match ({}).", e.getMessage());
			request.setAttribute("message", e.getMessage());
			return Response.ok(render("errors/unexpected-exception")).build();
		}
		
	}
	
	@GET
	@Path("/campaign/{matchId}/player/add")
	@Produces(MediaType.TEXT_HTML)
	public Response getPlayerForm(@PathParam("matchId") int matchId) {
		setPageTitle("Manage players");
		CampaignMatchDto match = matchService.findCampaignById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		request.setAttribute("match", match);
		UserDto[] activePlayers = userService.findActiveUsers();
		request.setAttribute("activePlayers", activePlayers);
		
		AddPlayerFormDto form = new AddPlayerFormDto();
		request.setAttribute("form", form);
		
		return Response.ok(render("add-player-campaign")).build();
	}
	
}
