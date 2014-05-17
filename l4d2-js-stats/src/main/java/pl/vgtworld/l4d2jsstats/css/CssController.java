package pl.vgtworld.l4d2jsstats.css;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/css/style.css")
public class CssController {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public InputStream getCssFile() {
		return CssController.class.getResourceAsStream("/css/style.css");
	}
}
