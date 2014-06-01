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

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.l4d2jsstats.App;
import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.MatchServiceException;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class VersusMatchController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VersusMatchController.class);
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";
	
	private static final String FORM_REQUEST_PARAM_KEY = "form";
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private MatchService matchService;
	
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
	public String submitMatch(@Form AddVersusMatchFormDto form) {
		GameMapDto[] maps = mapService.findAll();
		UserDto user = getLoggedUser();
		
		AddVersusMatchValidator validator = new AddVersusMatchValidator();
		boolean validationResult = validator.validate(form, maps);
		
		if (!validationResult) {
			request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
			request.setAttribute(FORM_REQUEST_PARAM_KEY, form);
			request.setAttribute("errors", validator.getErrors());
			return render("add-match-versus");
		}
		
		try {
			matchService.createVersusMatch(user.getId(), form);
			// TODO Redirect to player management page.
			return null;
		} catch (MatchServiceException e) {
			LOGGER.warn("Exception while trying to create match ({}).", e.getMessage());
			request.setAttribute("message", e.getMessage());
			return render("errors/unexpected-exception");
		}
	}
}
