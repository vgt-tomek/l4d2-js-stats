package pl.vgtworld.l4d2jsstats.changepassword;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.UserServiceException;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/change-password")
public class ChangePasswordController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordController.class);
	
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
		
		if (formValid) {
			try {
				userService.changeUserPassword(user.getId(), form.getNewPassword());
				return Response.ok(render("change-password-success")).build();
			} catch (UserServiceException e) {
				LOGGER.warn("Exception while trying to change user's password ({}).", e.getMessage());
				request.setAttribute("message", e.getMessage());
				return Response.ok(render("errors/unexpected-exception")).build();
			}
		}
		
		request.setAttribute("errors", validator.getErrors());
		return Response.ok(render("change-password")).build();
	}
}
