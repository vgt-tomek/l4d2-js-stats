package pl.vgtworld.l4d2jsstats.addmatch;

import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.l4d2jsstats.player.dto.PlayerCampaignDto;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

public class AddPlayerValidator {
	
	public enum ErrorMessages {
		
		MAX_PLAYERS("Maximum number of players reached."),
		USER_REUIQRED("User is required."),
		USER_ALREADY_ADDED("This user is already added."),
		DEATH_WRONG_VALUE("Death count should be greater or equal to 0.");
		
		private String message;
		
		public String getMessage() {
			return message;
		}
		
		private ErrorMessages(String message) {
			this.message = message;
		}
	}
	
	private static final int MAX_PLAYERS_COUNT = 4;
	
	private List<String> errors = new ArrayList<>();
	
	public String[] getErrors() {
		return errors.toArray(new String[errors.size()]);
	}
	
	public boolean validate(AddPlayerFormDto form, UserDto[] activeUsers, PlayerCampaignDto[] addedPlayers) {
		if (addedPlayers.length == MAX_PLAYERS_COUNT) {
			errors.add(ErrorMessages.MAX_PLAYERS.getMessage());
			return false;
		}
		validateUser(form, activeUsers);
		validateUserDuplication(form, addedPlayers);
		validateDeaths(form);
		return errors.size() == 0;
	}
	
	private void validateUser(AddPlayerFormDto form, UserDto[] activeUsers) {
		int userId = form.getUser();
		for (UserDto user : activeUsers) {
			if (user.getId() == userId) {
				return;
			}
		}
		errors.add(ErrorMessages.USER_REUIQRED.getMessage());
	}
	
	private void validateUserDuplication(AddPlayerFormDto form, PlayerCampaignDto[] addedPlayers) {
		int userId = form.getUser();
		for (PlayerCampaignDto player : addedPlayers) {
			if (player.getUserId() == userId) {
				errors.add(ErrorMessages.USER_ALREADY_ADDED.getMessage());
			}
		}
	}
	
	private void validateDeaths(AddPlayerFormDto form) {
		if (form.getDeaths() < 0) {
			errors.add(ErrorMessages.DEATH_WRONG_VALUE.getMessage());
		}
	}
}
