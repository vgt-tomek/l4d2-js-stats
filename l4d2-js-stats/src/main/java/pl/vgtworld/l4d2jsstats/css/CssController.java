package pl.vgtworld.l4d2jsstats.css;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/css/{filename}")
public class CssController {
	
	@PathParam("filename")
	private String filename;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCssFile() {
		if (validateFilename(filename)) {
			InputStream stream = CssController.class.getResourceAsStream("/css/" + filename);
			if (stream == null) {
				return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
			}
			return Response.ok(stream).build();
		}
		return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
	}
	
	private boolean validateFilename(String filename) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\-\\.]+\\.css$");
		Matcher matcher = pattern.matcher(filename);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
