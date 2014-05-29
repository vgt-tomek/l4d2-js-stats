package pl.vgtworld.l4d2jsstats.addmatch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.vgtworld.l4d2jsstats.addmatch.AddPlayerValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

public class AddPlayerValidatorTest {
	
	private static final int USER_ID = 1;
	
	private static final boolean USER_SURVIVED = true;
	
	private static final int USER_DEATHS = 2;
	
	@Test
	public void shouldAcceptValidForm() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		AddPlayerFormDto form = createValidForm();
		
		boolean result = validator.validate(form, users);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldAcceptZeroDeathCount() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		AddPlayerFormDto form = createValidForm();
		form.setDeaths(0);
		
		boolean result = validator.validate(form, users);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldNotAcceptWrongUser() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		AddPlayerFormDto form = createValidForm();
		form.setUser(4);
		
		boolean result = validator.validate(form, users);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.USER_REUIQRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNegativeDeathCount() {
		AddPlayerValidator validator = new AddPlayerValidator();
		UserDto[] users = createActiveUsersList();
		AddPlayerFormDto form = createValidForm();
		form.setDeaths(-1);
		
		boolean result = validator.validate(form, users);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.DEATH_WRONG_VALUE.getMessage());
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
	
	private UserDto createActiveUser(int userId) {
		UserDto dto = new UserDto();
		dto.setId(userId);
		return dto;
	}
}
