package pl.vgtworld.l4d2jsstats.userstats;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.user.User;
import pl.vgtworld.l4d2jsstats.user.UserService;

@Path("/user/{userId}")
public class UserStats extends BaseController {
	
	@PathParam("userId")
	private int userId;
	
	@Inject
	private UserService userService;
	
	@GET
	public Response getUserStats() {
		User user = userService.findById(userId);
		if (user == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		return Response.ok(render("user")).build();
	}
}
