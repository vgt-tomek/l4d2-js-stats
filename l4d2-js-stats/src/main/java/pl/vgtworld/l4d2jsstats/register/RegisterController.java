package pl.vgtworld.l4d2jsstats.register;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/register")
public class RegisterController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	private static final String PAGE_TITLE = "Register";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRegisterForm() {
		setPageTitle(PAGE_TITLE);
		return render("register");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String submitRegisterForm(MultivaluedMap<String, String> form) {
		setPageTitle(PAGE_TITLE);
		RegisterValidator validator = new RegisterValidator();
		boolean formValid = validator.validate(form);
		if (formValid) {
			LOGGER.info("New account created (login: {}).", form.getFirst("login"));
			//TODO Persist registered user in database.
			return render("register-success");
		}
		request.setAttribute("form", form);
		request.setAttribute("errors", validator.getErrors());
		return render("register");
	}
}
