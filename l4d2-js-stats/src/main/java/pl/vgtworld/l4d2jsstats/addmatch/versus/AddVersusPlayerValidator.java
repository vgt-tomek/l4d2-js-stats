package pl.vgtworld.l4d2jsstats.addmatch.versus;

import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.player.dto.PlayerVersusDto;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

//TODO A lot of code copied from AddCampaignPlayerValidator. Refactor both validators to extend base class with common code.
public class AddVersusPlayerValidator {
	
	public enum ErrorMessages {
		
		MAX_PLAYERS("Maximum number of players reached."),
		USER_REUIQRED("User is required."),
		USER_ALREADY_ADDED("This user is already added."),
		WINNER_STATUS_REQUIRED("Team assignment is required.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private ErrorMessages(String message) {
			this.message = message;
		}
	}
	
	private static final int MAX_PLAYERS_COUNT = 8;
	
	private List<String> errors = new ArrayList<>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	public boolean validate(AddVersusPlayerFormDto form, UserDto[] activeUsers, PlayerVersusDto[] addedPlayers) {
		if (addedPlayers.length == MAX_PLAYERS_COUNT) {
			errors.add(ErrorMessages.MAX_PLAYERS.getMessage());
			return false;
		}
		validateUser(form, activeUsers);
		validateUserDuplication(form, addedPlayers);
		validateWinnerStatus(form);
		return errors.size() == 0;
	}
	
	private void validateUser(AddVersusPlayerFormDto form, UserDto[] activeUsers) {
		int userId = form.getUser();
		for (UserDto user : activeUsers) {
			if (user.getId() == userId) {
				return;
			}
		}
		errors.add(ErrorMessages.USER_REUIQRED.getMessage());
	}
	
	private void validateUserDuplication(AddVersusPlayerFormDto form, PlayerVersusDto[] addedPlayers) {
		int userId = form.getUser();
		for (PlayerVersusDto player : addedPlayers) {
			if (player.getUserId() == userId) {
				errors.add(ErrorMessages.USER_ALREADY_ADDED.getMessage());
			}
		}
	}
	
	private void validateWinnerStatus(AddVersusPlayerFormDto form) {
		Boolean winnerStatus = form.isWinner();
		if (winnerStatus == null) {
			errors.add(ErrorMessages.WINNER_STATUS_REQUIRED.getMessage());
			return;
		}
	}
	
}
