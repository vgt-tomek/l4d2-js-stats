package pl.vgtworld.l4d2jsstats.favicon;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/images/js.png")
public class FaviconController {
	
	@GET
	public Response getFavicon() {
		InputStream stream = FaviconController.class.getResourceAsStream("/images/js.png");
		return Response.ok(stream, "image/png").build();
	}
}
