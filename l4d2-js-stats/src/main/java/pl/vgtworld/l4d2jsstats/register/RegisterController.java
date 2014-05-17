package pl.vgtworld.l4d2jsstats.register;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/register")
public class RegisterController extends BaseController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRegisterForm() {
		setPageTitle("Register");
		return render("register");
	}
}
