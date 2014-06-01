package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import pl.vgtworld.l4d2jsstats.player.PlayerService;
import pl.vgtworld.l4d2jsstats.player.PlayerServiceException;
import pl.vgtworld.l4d2jsstats.player.dto.PlayerCampaignDto;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class CampaignMatchController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CampaignMatchController.class);
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";
	
	private static final String MATCH_TYPES_REQUEST_PARAM_KEY = "matchTypes";
	
	private static final String FORM_REQUEST_PARAM_KEY = "form";
	
	private static final String DIFFICULTY_LEVELS_REQUEST_PARAM_KEY = "difficultyLevels";
	
	private static final String MATCH_REQUEST_PARAM_KEY = "match";
	
	private static final String ACTIVE_PLAYERS_REQUEST_PARAM_KEY = "activePlayers";
	
	private static final String ADDED_PLAYERS_REQUEST_PARAM_KEY = "addedPlayers";
	
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
	
	@Inject
	private PlayerService playerService;
	
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
		
		AddCampaignMatchFormDto form = new AddCampaignMatchFormDto();
		form.setDate(new SimpleDateFormat(App.DATE_FORMAT).format(new Date()));
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return render("add-match-campaign");
	}
	
	@POST
	@Path("/add/campaign")
	@Produces(MediaType.TEXT_HTML)
	public Response submitMatch(@Form AddCampaignMatchFormDto form) {
		GameMapDto[] maps = mapService.findAll();
		UserDto user = getLoggedUser();
		DifficultyLevelDto[] difficultyLevels = difficultyService.findAll();
		
		AddCampaignMatchValidator validator = new AddCampaignMatchValidator();
		boolean validationResult = validator.validate(form, maps, difficultyLevels);
		
		if (!validationResult) {
			request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
			request.setAttribute("errors", validator.getErrors());
			request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
			request.setAttribute(DIFFICULTY_LEVELS_REQUEST_PARAM_KEY, difficultyLevels);
			return Response.ok(render("add-match-campaign")).build();
		}
		
		try {
			int matchId = matchService.createCampaignMatch(user.getId(), form);
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
		UserDto[] activePlayers = userService.findActiveUsers();
		PlayerCampaignDto[] addedPlayers = playerService.findPlayersFromCampaignMatch(match.getId());
		activePlayers = filterNotAddedPlayers(activePlayers, addedPlayers);
		AddCampaignPlayerFormDto form = new AddCampaignPlayerFormDto();
		
		request.setAttribute(MATCH_REQUEST_PARAM_KEY, match);
		request.setAttribute(ACTIVE_PLAYERS_REQUEST_PARAM_KEY, activePlayers);
		request.setAttribute(ADDED_PLAYERS_REQUEST_PARAM_KEY, addedPlayers);
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return Response.ok(render("add-player-campaign")).build();
	}
	
	@POST
	@Path("/campaign/{matchId}/player/add")
	@Produces(MediaType.TEXT_HTML)
	public Response submitPlayer(@Form AddCampaignPlayerFormDto form, @PathParam("matchId") int matchId) {
		setPageTitle("Manage players");
		CampaignMatchDto match = matchService.findCampaignById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		UserDto[] activePlayers = userService.findActiveUsers();
		PlayerCampaignDto[] addedPlayers = playerService.findPlayersFromCampaignMatch(match.getId());
		
		AddCampaignPlayerValidator validator = new AddCampaignPlayerValidator();
		boolean validationResult = validator.validate(form, activePlayers, addedPlayers);
		
		try {
			if (validationResult) {
				playerService.addUserToCampaignMatch(
					match.getId(), form.getUser(), form.isSurvived(), form.getDeaths()
					);
				addedPlayers = playerService.findPlayersFromCampaignMatch(match.getId());
				request.setAttribute(FORM_REQUEST_PARAM_KEY, new AddCampaignPlayerFormDto());
			} else {
				request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
				request.setAttribute("errors", validator.getErrors());
			}
			request.setAttribute(MATCH_REQUEST_PARAM_KEY, match);
			activePlayers = filterNotAddedPlayers(activePlayers, addedPlayers);
			request.setAttribute(ACTIVE_PLAYERS_REQUEST_PARAM_KEY, activePlayers);
			request.setAttribute(ADDED_PLAYERS_REQUEST_PARAM_KEY, addedPlayers);
			
			return Response.ok(render("add-player-campaign")).build();
		} catch (PlayerServiceException e) {
			LOGGER.warn("Exception while trying to add player to match ({}).", e.getMessage());
			request.setAttribute("message", e.getMessage());
			return Response.ok(render("errors/unexpected-exception")).build();
		}
	}
	
	private UserDto[] filterNotAddedPlayers(UserDto[] userList, PlayerCampaignDto[] addedPlayers) {
		List<UserDto> filteredList = new ArrayList<>();
		List<Integer> addedPlayersId = new ArrayList<>();
		for (PlayerCampaignDto player : addedPlayers) {
			addedPlayersId.add(player.getUserId());
		}
		for (UserDto user : userList) {
			if (!addedPlayersId.contains(user.getId())) {
				filteredList.add(user);
			}
		}
		return filteredList.toArray(new UserDto[filteredList.size()]);
	}
}
