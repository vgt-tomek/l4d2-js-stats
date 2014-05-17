package pl.vgtworld.l4d2jsstats;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;

public abstract class BaseController {
	
	public String process(String view, HttpServletRequest request, HttpServletResponse response) {
		WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale());
		return AppTemplateEngine.getTemplateEngine().process(view, context);
	}
	
}
