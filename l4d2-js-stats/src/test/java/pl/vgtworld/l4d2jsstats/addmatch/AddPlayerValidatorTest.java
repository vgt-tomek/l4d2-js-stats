package pl.vgtworld.l4d2jsstats.addmatch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.vgtworld.l4d2jsstats.addmatch.AddPlayerValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.player.dto.PlayerCampaignDto;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

public class AddPlayerValidatorTest {
	
	private static final int USER_ID = 1;
	
	private static final boolean USER_SURVIVED = true;
	
	private static final int USER_DEATHS = 2;
	
	@Test
	public void shouldAcceptValidForm() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		PlayerCampaignDto[] addedPlayers = createEmptyAddedPlayersList();
		AddPlayerFormDto form = createValidForm();
		
		boolean result = validator.validate(form, users, addedPlayers);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldAcceptZeroDeathCount() {
		AddPlayerValidator validator = new AddPlayerValidator();
		PlayerCampaignDto[] addedPlayers = createEmptyAddedPlayersList();
		UserDto[] users = createActiveUsersList();
		AddPlayerFormDto form = createValidForm();
		form.setDeaths(0);
		
		boolean result = validator.validate(form, users, addedPlayers);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldNotAcceptWrongUser() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		PlayerCampaignDto[] addedPlayers = createEmptyAddedPlayersList();
		AddPlayerFormDto form = createValidForm();
		form.setUser(4);
		
		boolean result = validator.validate(form, users, addedPlayers);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.USER_REUIQRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNegativeDeathCount() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		PlayerCampaignDto[] addedPlayers = createEmptyAddedPlayersList();
		AddPlayerFormDto form = createValidForm();
		form.setDeaths(-1);
		
		boolean result = validator.validate(form, users, addedPlayers);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.DEATH_WRONG_VALUE.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWhenAddedlayersListIsAlreadyFull() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		PlayerCampaignDto[] addedPlayers = createFullAddedPlayersList();
		AddPlayerFormDto form = createValidForm();
		
		boolean result = validator.validate(form, users, addedPlayers);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.MAX_PLAYERS.getMessage());
	}
	
	private AddPlayerFormDto createValidForm() {
		AddPlayerFormDto form = new AddPlayerFormDto();
		form.setUser(USER_ID);
		form.setSurvived(USER_SURVIVED);
		form.setDeaths(USER_DEATHS);
		return form;
	}
	
	private UserDto[] createActiveUsersList() {
		UserDto[] users = new UserDto[3];
		users[0] = createActiveUser(1);
		users[1] = createActiveUser(2);
		users[2] = createActiveUser(3);
		return users;
	}
	
	private PlayerCampaignDto[] createEmptyAddedPlayersList() {
		return new PlayerCampaignDto[0];
	}
	
	private PlayerCampaignDto[] createFullAddedPlayersList() {
		PlayerCampaignDto[] dtoList = new PlayerCampaignDto[4];
		dtoList[0] = createAddedPlayer(1);
		dtoList[1] = createAddedPlayer(2);
		dtoList[2] = createAddedPlayer(3);
		dtoList[3] = createAddedPlayer(4);
		return dtoList;
	}
	
	private UserDto createActiveUser(int userId) {
		UserDto dto = new UserDto();
		dto.setId(userId);
		return dto;
	}
	
	private PlayerCampaignDto createAddedPlayer(int userId) {
		PlayerCampaignDto dto = new PlayerCampaignDto();
		dto.setUserId(userId);
		return dto;
	}
}
