package pl.vgtworld.l4d2jsstats.addmatch;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.addmatch.campaign.DeletePlayerFormDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.MatchServiceException;
import pl.vgtworld.l4d2jsstats.player.PlayerService;

@Path("/match")
public class MatchController extends BaseController {
	
	@Inject
	private MatchService matchService;
	
	@Inject
	private PlayerService playerService;
	
	@GET
	@Path("/add/picker")
	@Produces(MediaType.TEXT_HTML)
	public String displayMatchTypePicker() {
		setPageTitle("Pick match type");
		return render("add-match-picker");
	}
	
	@POST
	@Path("/{matchType}/{matchId}/player/delete")
	@Produces(MediaType.TEXT_HTML)
	public Response deletePlayerFromMatch(
		@Form DeletePlayerFormDto form, @PathParam("matchType") String matchType, @PathParam("matchId") int matchId) {
		int userId = form.getUserId();
		playerService.deleteUserFromMatch(userId, matchId);
		return seeOther(String.format("/match/%s/%d/player/add", matchType, matchId));
	}
	
	@POST
	@Path("/{matchId}/activate")
	@Produces(MediaType.TEXT_HTML)
	public String activateMatch(@PathParam("matchId") int matchId) {
		try {
			matchService.activateMatch(matchId);
			return render("match-activated");
		} catch (MatchServiceException e) {
			request.setAttribute("message", e.getMessage());
			return render("errors/unexpected-exception");
		}
	}
	
}
