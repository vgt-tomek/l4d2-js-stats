package pl.vgtworld.l4d2jsstats.logout;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.UserService;

@Path("/logout")
public class LogoutController extends BaseController {
	
	@Inject
	private UserService userService;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getLogoutPage() {
		setPageTitle("Logout");
		userService.logout(response);
		return render("logout");
	}
}
