package pl.vgtworld.l4d2jsstats;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Singleton
public class AppTemplateEngine {
	
	private static final long CACHE_TIME = 3600000L;
	
	private TemplateEngine templateEngine = null;
	
	@Produces
	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}
	
	@PostConstruct
	private void initializeTemplateEngine() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/pages/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(CACHE_TIME);
		
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}
}
