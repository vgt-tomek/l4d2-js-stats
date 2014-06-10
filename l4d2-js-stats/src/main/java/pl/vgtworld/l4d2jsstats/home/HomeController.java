package pl.vgtworld.l4d2jsstats.home;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.MostPlayedMapDto;

@Path("/")
public class HomeController extends BaseController {
	
	@Inject
	private GameMapService mapService;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHome() {
		setPageTitle("Home");
		
		MostPlayedMapDto[] mostPlayedMaps = mapService.getMostPlayedMaps();
		request.setAttribute("mostPlayedMaps", mostPlayedMaps);
		
		return render("home");
	}
}
