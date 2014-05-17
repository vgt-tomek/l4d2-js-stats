package pl.vgtworld.l4d2jsstats;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public abstract class AppTemplateEngine {
	
	private static TemplateEngine templateEngine = null;
	
	static {
		initializeTemplateEngine();
	}
	
	public static TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
	
	private static void initializeTemplateEngine() {
		
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/pages/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);
		
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		
	}
}
