package pl.vgtworld.l4d2jsstats.home;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.MostPlayedMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.MapBreakDto;
import pl.vgtworld.l4d2jsstats.match.dto.MatchCountMonthlyDto;
import pl.vgtworld.l4d2jsstats.match.dto.UserActivityDto;
import pl.vgtworld.l4d2jsstats.player.PlayerService;
import pl.vgtworld.l4d2jsstats.player.dto.MostActivePlayerDto;

@Path("/")
public class HomeController extends BaseController {
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private PlayerService playerService;
	
	@Inject
	private MatchService matchService;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHome() {
		setPageTitle("Home");
		
		MostPlayedMapDto[] mostPlayedMaps = mapService.getMostPlayedMaps();
		request.setAttribute("mostPlayedMaps", mostPlayedMaps);
		
		MostActivePlayerDto[] mostActivePlayers = playerService.getMostActivePlayers();
		request.setAttribute("mostActivePlayers", mostActivePlayers);
		
		MapBreakDto[] mapsByBreak = matchService.getMapsByLongestBreak();
		request.setAttribute("mapsByBreak", mapsByBreak);
		
		UserActivityDto[] usersActivity = matchService.getUsersActivity();
		request.setAttribute("usersActivity", usersActivity);
		
		MatchCountMonthlyDto[] matchCountMonthly = matchService.getMatchCountMonthly();
		request.setAttribute("matchCountMonthly", matchCountMonthly);
		
		return render("home");
	}
}
