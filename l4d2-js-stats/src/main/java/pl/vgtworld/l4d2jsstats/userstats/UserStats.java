package pl.vgtworld.l4d2jsstats.userstats;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.RecentMatchDto;
import pl.vgtworld.l4d2jsstats.player.PlayerService;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;
import pl.vgtworld.l4d2jsstats.userstats.dto.UserGeneralStatisticsDto;

@Path("/user/{userId}")
public class UserStats extends BaseController {
	
	private static final int RECENT_MATCHES_COUNT = 10;

	@PathParam("userId")
	private int userId;
	
	@Inject
	private UserService userService;
	
	@Inject
	private PlayerService playerService;
	
	@Inject
	private MatchService matchService;
	
	@GET
	public Response getUserStats() {
		UserDto user = userService.findById(userId);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		request.setAttribute("userProfile", user);
		setPageTitle(user.getLogin() + " profile");
		
		UserGeneralStatisticsDto playerStatistics = playerService.getPlayerStatistics(userId);
		request.setAttribute("playerStatistics", playerStatistics);
		
		RecentMatchDto[] recentMatches = matchService.findRecentMatchesForUser(userId, RECENT_MATCHES_COUNT);
		request.setAttribute("recentMatches", recentMatches);
		
		return Response.ok(render("user")).build();
	}
}
