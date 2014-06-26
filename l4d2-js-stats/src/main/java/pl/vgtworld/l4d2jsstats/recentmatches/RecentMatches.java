package pl.vgtworld.l4d2jsstats.recentmatches;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.RecentMatchDto;

@Path("recent-matches")
public class RecentMatches extends BaseController {
	
	private static final int RESULTS_PER_PAGE = 10;
	
	@Inject
	private MatchService matchService;
	
	@GET
	public Response getRecentMatches() {
		return getRecentMatches(1);
	}
	
	@GET
	@Path("{pageNumber}")
	public Response getRecentMatches(@PathParam("pageNumber") int page) {
		if (page <= 0) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		setPageTitle("Recent matches");
		
		long pageCount = (long) Math.ceil(matchService.getTotalMatchesPlayed() / (double) RESULTS_PER_PAGE);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		int offset = (page - 1) * RESULTS_PER_PAGE;
		RecentMatchDto[] recentMatches = matchService.findRecentMatches(RESULTS_PER_PAGE, offset);
		request.setAttribute("recentMatches", recentMatches);
		return Response.ok(render("recent-matches")).build();
	}
}
