package pl.vgtworld.l4d2jsstats;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.thymeleaf.context.WebContext;

public abstract class BaseController {
	
	@Context
	protected HttpServletRequest request;
	
	@Context
	protected HttpServletResponse response;
	
	protected void setPageTitle(String title) {
		request.setAttribute("pageTitle", title);
	}
	
	public String render(String view) {
		WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
		return AppTemplateEngine.getTemplateEngine().process(view, context);
	}
	
}
