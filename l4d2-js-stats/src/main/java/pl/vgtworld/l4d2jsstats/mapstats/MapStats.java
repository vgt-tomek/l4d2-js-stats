package pl.vgtworld.l4d2jsstats.mapstats;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

@Path("/map/{mapId}")
public class MapStats extends BaseController {
	
	@PathParam("mapId")
	private int mapId;
	
	@Inject
	private GameMapService mapService;
	
	@GET
	public Response getMapStatistics() {
		GameMapDto map = mapService.findById(mapId);
		if (map == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		setPageTitle(String.format("%s statistics", map.getName()));
		request.setAttribute("map", map);
		return Response.ok(render("map")).build();
	}
}
