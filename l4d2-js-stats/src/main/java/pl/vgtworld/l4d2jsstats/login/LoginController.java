package pl.vgtworld.l4d2jsstats.login;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.UserService;

@Path("/login")
public class LoginController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private UserService userService;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getLoginForm() {
		setPageTitle("Login");
		return render("login");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String submitLoginForm(@FormParam("login") String login, @FormParam("password") String password) {
		setPageTitle("Login");
		LoginFormDto form = new LoginFormDto(login, password);
		LoginFormValidator validator = new LoginFormValidator();
		boolean formValid = validator.validate(form, userService);
		if (formValid) {
			//TODO Create cookies.
			return render("login-success");
		}
		LOGGER.info("Failed login attempt for user {} from ip {}.", login, getRemoteAddr());
		request.setAttribute("form", form);
		request.setAttribute("errors", validator.getErrors());
		return render("login");
	}
}
