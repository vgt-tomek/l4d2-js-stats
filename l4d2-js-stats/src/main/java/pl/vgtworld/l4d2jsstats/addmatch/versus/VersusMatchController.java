package pl.vgtworld.l4d2jsstats.addmatch.versus;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.vgtworld.l4d2jsstats.App;
import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

@Path("/match")
public class VersusMatchController extends BaseController {
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";
	
	private static final String FORM_REQUEST_PARAM_KEY = "form";
	
	@Inject
	private GameMapService mapService;
	
	@GET
	@Path("/add/versus")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchForm() {
		setPageTitle("Add match");
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
		
		AddVersusMatchFormDto form = new AddVersusMatchFormDto();
		form.setDate(new SimpleDateFormat(App.DATE_FORMAT).format(new Date()));
		request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
		
		return render("add-match-versus");
	}
	
	@POST
	@Path("/add/versus")
	@Produces(MediaType.TEXT_HTML)
	public Response submitMatch() {
		
		return null;
	}
}
