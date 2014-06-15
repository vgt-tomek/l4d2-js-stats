package pl.vgtworld.l4d2jsstats.staticdata;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/static")
public class StaticDataController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaticDataController.class);
	
	@GET
	@Path("/css/{filename}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCssFile(@PathParam("filename") String filename) {
		return getResource(filename, "css");
	}
	
	@GET
	@Path("/js/{filename}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getJsFile(@PathParam("filename") String filename) {
		return getResource(filename, "js");
	}
	
	@GET
	@Path("/fonts/{filename}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFontFile(@PathParam("filename") String filename) {
		return getResource(filename, "font");		
	}
	
	private Response getResource(String filename, String folder) {
		if (validateFilename(filename)) {
			String resourcePath = String.format("/%s/%s", folder, filename);
			InputStream stream = StaticDataController.class.getResourceAsStream(resourcePath);
			if (stream == null) {
				return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
			}
			return Response.ok(stream).build();
		}
		LOGGER.debug("Filename {} is not valid. Aborting.", filename);
		return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
	}
	
	private boolean validateFilename(String filename) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\-\\.]+\\.[a-z]+$");
		Matcher matcher = pattern.matcher(filename);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
