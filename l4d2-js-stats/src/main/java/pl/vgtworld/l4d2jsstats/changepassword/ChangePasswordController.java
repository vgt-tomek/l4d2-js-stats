package pl.vgtworld.l4d2jsstats.changepassword;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/change-password")
public class ChangePasswordController extends BaseController {
	
	@GET
	public Response getChangePasswordForm() {
		UserDto user = getLoggedUser();
		if (user == null) {
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}
		
		return Response.ok(render("change-password")).build();
	}
	
}
