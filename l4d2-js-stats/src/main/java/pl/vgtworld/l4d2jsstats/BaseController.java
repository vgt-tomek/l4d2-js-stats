package pl.vgtworld.l4d2jsstats;

import java.net.URI;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

public abstract class BaseController {
	
	@Context
	protected HttpServletRequest request;
	
	@Context
	protected HttpServletResponse response;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private TemplateEngine engine;
	
	protected UserDto getLoggedUser() {
		return (UserDto) (request.getAttribute("user"));
	}
	
	protected void setPageTitle(String title) {
		request.setAttribute("pageTitle", title);
	}
	
	protected String render(String view) {
		WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
		return engine.process(view, context);
	}
	
	protected String getRemoteAddr() {
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		if (xForwardedFor != null) {
			return xForwardedFor;
		}
		return request.getRemoteAddr();
	}
	
	protected Response seeOther(String location) {
		URI uri = uriInfo.getBaseUriBuilder().replacePath(location).build();
		return Response.seeOther(uri).build();
	}
	
}
