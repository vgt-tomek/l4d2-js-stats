package pl.vgtworld.l4d2jsstats.changepassword;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/change-password")
public class ChangePasswordController extends BaseController {
	
	@Inject
	private UserService userService;

	@GET
	public Response getChangePasswordForm() {
		UserDto user = getLoggedUser();
		if (user == null) {
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}
		
		return Response.ok(render("change-password")).build();
	}
	
	@POST
	public Response submitChangePasswordForm(@Form ChangePasswordFormDto form) {
		UserDto user = getLoggedUser();
		if (user == null) {
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}
		
		ChangePasswordValidator validator = new ChangePasswordValidator();
		boolean formValid = validator.validate(form, user.getLogin(), userService);
		
		request.setAttribute("errors", validator.getErrors());
		return Response.ok(render("change-password")).build();
	}
}
