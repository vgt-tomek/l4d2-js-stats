package pl.vgtworld.l4d2jsstats.addmatch.versus;

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
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.MatchServiceException;
import pl.vgtworld.l4d2jsstats.match.dto.VersusMatchDto;
import pl.vgtworld.l4d2jsstats.player.PlayerService;
import pl.vgtworld.l4d2jsstats.player.PlayerServiceException;
import pl.vgtworld.l4d2jsstats.player.dto.PlayerVersusDto;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class VersusMatchController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VersusMatchController.class);
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";
	
	private static final String FORM_REQUEST_PARAM_KEY = "form";
	
	private static final String MATCH_REQUEST_PARAM_KEY = "match";
	
	private static final String ACTIVE_PLAYERS_REQUEST_PARAM_KEY = "activePlayers";

	private static final String ADDED_PLAYERS_REQUEST_PARAM_KEY = "addedPlayers";
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private MatchService matchService;
	
	@Inject
	private PlayerService playerService;
	
	@Inject
	private UserService userService;
	
	@GET
	@Path("/add/versus")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchForm() {
		setPageTitle("Add match");
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
		
		AddVersusMatchFormDto form = new AddVersusMatchFormDto();
		form.setDate(new SimpleDateFormat(App.DATE_FORMAT).format(new Date()));
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return render("add-match-versus");
	}
	
	@POST
	@Path("/add/versus")
	@Produces(MediaType.TEXT_HTML)
	public Response submitMatch(@Form AddVersusMatchFormDto form) {
		GameMapDto[] maps = mapService.findAll();
		UserDto user = getLoggedUser();
		
		AddVersusMatchValidator validator = new AddVersusMatchValidator();
		boolean validationResult = validator.validate(form, maps);
		
		if (!validationResult) {
			request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
			request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
			request.setAttribute("errors", validator.getErrors());
			return Response.ok(render("add-match-versus")).build();
		}
		
		try {
			int matchId = matchService.createVersusMatch(user.getId(), form);
			String location = String.format("/match/versus/%d/player/add", matchId);
			return seeOther(location);
		} catch (MatchServiceException e) {
			LOGGER.warn("Exception while trying to create match ({}).", e.getMessage());
			request.setAttribute("message", e.getMessage());
			return Response.ok(render("errors/unexpected-exception")).build();
		}
	}
	
	@GET
	@Path("/versus/{matchId}/player/add")
	@Produces(MediaType.TEXT_HTML)
	public Response getPlayerForm(@PathParam("matchId") int matchId) {
		setPageTitle("Manage players");
		VersusMatchDto match = matchService.findVersusById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		UserDto[] activePlayers = userService.findActiveUsers();
		PlayerVersusDto[] addedPlayers = playerService.findPlayersFromVersusMatch(matchId);
		activePlayers = filterNotAddedPlayers(activePlayers, addedPlayers);
		AddVersusPlayerFormDto form = new AddVersusPlayerFormDto();
		
		request.setAttribute(MATCH_REQUEST_PARAM_KEY, match);
		request.setAttribute(ACTIVE_PLAYERS_REQUEST_PARAM_KEY, activePlayers);
		request.setAttribute(ADDED_PLAYERS_REQUEST_PARAM_KEY, addedPlayers);
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return Response.ok(render("add-player-versus")).build();
	}
	
	@POST
	@Path("/versus/{matchId}/player/add")
	@Produces(MediaType.TEXT_HTML)
	public Response submitPlayer(@Form AddVersusPlayerFormDto form, @PathParam("matchId") int matchId) {
		setPageTitle("Manage players");
		VersusMatchDto match = matchService.findVersusById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		UserDto[] activePlayers = userService.findActiveUsers();
		PlayerVersusDto[] addedPlayers = playerService.findPlayersFromVersusMatch(matchId);
		
		AddVersusPlayerValidator validator = new AddVersusPlayerValidator();
		boolean validationResult = validator.validate(form, activePlayers, addedPlayers);
		
		try {
			if (validationResult) {
				playerService.addUserToVersusMatch(match.getId(), form.getUser(), form.isWinner());
				addedPlayers = playerService.findPlayersFromVersusMatch(matchId);
				request.setAttribute(FORM_REQUEST_PARAM_KEY, new AddVersusPlayerFormDto());
			} else {
				request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
				request.setAttribute("errors", validator.getErrors());
			}
			request.setAttribute(MATCH_REQUEST_PARAM_KEY, match);
			activePlayers = filterNotAddedPlayers(activePlayers, addedPlayers);
			request.setAttribute(ACTIVE_PLAYERS_REQUEST_PARAM_KEY, activePlayers);
			request.setAttribute(ADDED_PLAYERS_REQUEST_PARAM_KEY, addedPlayers);
			
			return Response.ok(render("add-player-versus")).build();
		} catch (PlayerServiceException e) {
			LOGGER.warn("Exception while trying to add player to match ({}).", e.getMessage());
			request.setAttribute("message", e.getMessage());
			return Response.ok(render("errors/unexpected-exception")).build();
		}
	}
	
	//TODO Almost exact copy of the same method in CampaignMatchController. Refactor into utility method.
	private UserDto[] filterNotAddedPlayers(UserDto[] userList, PlayerVersusDto[] addedPlayers) {
		List<UserDto> filteredList = new ArrayList<>();
		List<Integer> addedPlayersId = new ArrayList<>();
		for (PlayerVersusDto player : addedPlayers) {
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
