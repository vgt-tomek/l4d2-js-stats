package pl.vgtworld.l4d2jsstats.userstats;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/user/{userId}")
public class UserStats extends BaseController {
	
	@PathParam("userId")
	private int userId;
	
	@GET
	public String getUserStats() {
		return render("user");
	}
}
