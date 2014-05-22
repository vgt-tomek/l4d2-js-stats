package pl.vgtworld.l4d2jsstats.match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/match")
public class MatchController extends BaseController {
	
	@GET
	@Path("/pick-type")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchPicker() {
		setPageTitle("Pick match type");
		return render("add-match-picker");
	}
}
