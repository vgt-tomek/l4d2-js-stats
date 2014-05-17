package pl.vgtworld.l4d2jsstats.register;

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
import pl.vgtworld.l4d2jsstats.user.UserServiceException;

@Path("/register")
public class RegisterController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	private static final String PAGE_TITLE = "Register";
	
	@Inject
	private UserService userService;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRegisterForm() {
		setPageTitle(PAGE_TITLE);
		return render("register");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String submitRegisterForm(@FormParam("login") String login, @FormParam("password") String password,
		@FormParam("password-repeat") String repeatPassword) {
		setPageTitle(PAGE_TITLE);
		RegisterFormDto form = new RegisterFormDto(login, password, repeatPassword);
		RegisterValidator validator = new RegisterValidator();
		boolean formValid = validator.validate(form);
		if (formValid) {
			try {
				LOGGER.info("New account created (login: {}).", form.getLogin());
				userService.createNewUser(login, password);
				return render("register-success");
			} catch (UserServiceException e) {
				LOGGER.warn("Exception while trying to create user ({}).", e.getMessage());
				// TODO Display proper error page.
				return null;
			}
		}
		request.setAttribute("form", form);
		request.setAttribute("errors", validator.getErrors());
		return render("register");
	}
}
