package pl.vgtworld.l4d2jsstats.register;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;

@Path("/register")
public class RegisterController extends BaseController {
	
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getRegisterForm() {
		request.setAttribute("pageTitle", "Register");
		return process("register", request, response);
	}
}
