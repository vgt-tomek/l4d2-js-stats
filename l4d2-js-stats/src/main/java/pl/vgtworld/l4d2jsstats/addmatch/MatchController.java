package pl.vgtworld.l4d2jsstats.addmatch;

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

import pl.vgtworld.l4d2jsstats.BaseController;
import pl.vgtworld.l4d2jsstats.map.GameMapService;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;
import pl.vgtworld.l4d2jsstats.match.MatchService;
import pl.vgtworld.l4d2jsstats.match.MatchServiceException;
import pl.vgtworld.l4d2jsstats.match.MatchTypeService;
import pl.vgtworld.l4d2jsstats.match.dto.MatchTypeDto;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@Path("/match")
public class MatchController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);
	
	private static final String MAPS_REQUEST_PARAM_KEY = "maps";

	private static final String MATCH_TYPES_REQUEST_PARAM_KEY = "matchTypes";

	@Inject
	private MatchTypeService matchTypeService;
	
	@Inject
	private GameMapService mapService;
	
	@Inject
	private MatchService matchService;
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public String getMatchForm() {
		setPageTitle("Add match");
		MatchTypeDto[] matchTypes = matchTypeService.findAll();
		request.setAttribute(MATCH_TYPES_REQUEST_PARAM_KEY, matchTypes);
		GameMapDto[] maps = mapService.findAll();
		request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
		return render("add-match");
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public Response submitMatch(@Form AddMatchFormDto form) {
		MatchTypeDto[] matchTypes = matchTypeService.findAll();
		GameMapDto[] maps = mapService.findAll();
		UserDto user = getLoggedUser();
		
		AddMatchValidator validator = new AddMatchValidator();
		boolean validationResult = validator.validate(form, matchTypes, maps);
		
		if (!validationResult) {
			request.setAttribute(MATCH_TYPES_REQUEST_PARAM_KEY, matchTypes);
			request.setAttribute(MAPS_REQUEST_PARAM_KEY, maps);
			request.setAttribute("errors", validator.getErrors());
			return Response.ok(render("add-match")).build();
		}
		
		try {
			matchService.createMatch(user.getId(), form.getMapId());
			//TODO Redirect to step 2.
			return Response.ok(render("errors/not-implemented")).build();
		} catch (MatchServiceException e) {
			LOGGER.warn("Exception while trying to create match ({}).", e.getMessage());
			// TODO Display proper error page.
			return null;
		}
		
	}
	
}
