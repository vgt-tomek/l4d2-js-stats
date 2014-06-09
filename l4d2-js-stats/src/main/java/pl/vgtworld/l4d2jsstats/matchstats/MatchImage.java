package pl.vgtworld.l4d2jsstats.matchstats;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.dto.MatchDto;
import pl.vgtworld.l4d2jsstats.storage.Storage;

@Path("/match-image/{matchId}")
public class MatchImage {
	
	@PathParam("matchId")
	private int matchId;
	
	@Inject
	private MatchService matchService;
	
	@Inject
	private Storage storage;
	
	@GET
	public Response getImage() {
		try {
			MatchDto match = matchService.findMatchById(matchId);
			if (match == null || match.getImageName() == null) {
				return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
			}
			String filename = match.getImageName();
			InputStream stream = storage.getMatchScreenshot(filename);
			return Response.ok(stream).type("image/jpeg").build();
		} catch (FileNotFoundException e) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
	}
	
}
