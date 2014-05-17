package pl.vgtworld.l4d2jsstats.login;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/login")
public class LoginController extends BaseController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getLoginForm() {
		setPageTitle("Login");
		return render("login");
	}
	
}
