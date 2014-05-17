package pl.vgtworld.l4d2jsstats.home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/")
public class HomeController extends BaseController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHome() {
		setPageTitle("Home");
		return render("home");
	}
}
