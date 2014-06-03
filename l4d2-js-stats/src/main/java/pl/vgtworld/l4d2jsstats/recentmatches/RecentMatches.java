package pl.vgtworld.l4d2jsstats.recentmatches;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.RecentMatchDto;

@Path("recent-matches")
public class RecentMatches extends BaseController {
	
	private static final int RECENT_MATCHES_COUNT = 30;
	@Inject
	private MatchService matchService;
	
	@GET
	public String getRecentMatches() {
		setPageTitle(String.format("%d recent matches", RECENT_MATCHES_COUNT));
		RecentMatchDto[] recentMatches = matchService.findRecentMatches(RECENT_MATCHES_COUNT);
		request.setAttribute("recentMatches", recentMatches);
		return render("recent-matches");
	}
	
}
