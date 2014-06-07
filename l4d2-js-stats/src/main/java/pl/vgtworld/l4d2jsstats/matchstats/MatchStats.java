package pl.vgtworld.l4d2jsstats.matchstats;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.CampaignMatchDto;
import pl.vgtworld.l4d2jsstats.match.dto.VersusMatchDto;
import pl.vgtworld.l4d2jsstats.player.PlayerService;
import pl.vgtworld.l4d2jsstats.player.dto.PlayerCampaignDto;

@Path("/match/{matchId}")
public class MatchStats extends BaseController {
	
	private static final String MATCH_REQUEST_KEY = "match";
	
	private static final String PLAYERS_REQUEST_KEY = "players";
	
	@PathParam("matchId")
	private int matchId;
	
	@Inject
	private MatchService matchService;
	
	@Inject
	private PlayerService playerService;
	
	@GET
	@Path("/campaign")
	public Response getCampaignMatchStatistics() {
		CampaignMatchDto match = matchService.findCampaignById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		PlayerCampaignDto[] players = playerService.findPlayersFromCampaignMatch(matchId);
		
		request.setAttribute(MATCH_REQUEST_KEY, match);
		request.setAttribute(PLAYERS_REQUEST_KEY, players);
		return Response.ok(render("match-campaign")).build();
	}
	
	@GET
	@Path("/versus")
	public Response getVersusMatchStatistics() {
		VersusMatchDto match = matchService.findVersusById(matchId);
		if (match == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		request.setAttribute(MATCH_REQUEST_KEY, match);
		return Response.ok(render("match-versus")).build();
	}
	
}
