package pl.vgtworld.l4d2jsstats.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/")
public class HomeController extends BaseController {
	
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHome() {
		request.setAttribute("pageTitle", "Home");
		return process("home", request, response);
	}
}
