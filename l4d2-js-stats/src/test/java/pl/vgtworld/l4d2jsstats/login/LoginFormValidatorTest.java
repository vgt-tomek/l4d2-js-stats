package pl.vgtworld.l4d2jsstats.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.vgtworld.l4d2jsstats.login.LoginFormValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@RunWith(MockitoJUnitRunner.class)
public class LoginFormValidatorTest {
	
	private static final int ID = 21;

	private static final String LOGIN = "login";
	
	private static final String PASSWORD = "password";
	
	private LoginFormValidator validator;
	
	private LoginFormDto form;
	
	@Mock
	private UserService userService;
	
	@Before
	public void initTest() {
		validator = new LoginFormValidator();
		form = createValidDto();
	}
	
	@Test
	public void shouldAcceptValidLoginData() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		when(userService.findByLogin(anyString())).thenReturn(createValidUserDto());
		when(userService.isCorrectLoginCredentials(anyString(), anyString())).thenReturn(true);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).hasSize(0);
	}

	@Test
	public void shouldNotAcceptWrongLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		when(userService.findByLogin(anyString())).thenReturn(null);
		when(userService.isCorrectLoginCredentials(anyString(), anyString())).thenReturn(true);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_ERROR.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		when(userService.findByLogin(anyString())).thenReturn(createValidUserDto());
		when(userService.isCorrectLoginCredentials(anyString(), anyString())).thenReturn(false);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_ERROR.getMessage());
	}
	
	@Test
	public void shouldNotAcceptInactiveUser() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		when(userService.findByLogin(anyString())).thenReturn(createInactiveUserDto());
		when(userService.isCorrectLoginCredentials(anyString(), anyString())).thenReturn(true);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.USER_INACTIVE.getMessage());
	}
	
	private UserDto createInactiveUserDto() {
		UserDto dto = new UserDto();
		dto.setId(ID);
		dto.setLogin(LOGIN);
		dto.setActive(false);
		dto.setCreatedAt(new Date());
		return dto;
	}
	
	private UserDto createValidUserDto() {
		UserDto dto = new UserDto();
		dto.setId(ID);
		dto.setLogin(LOGIN);
		dto.setActive(true);
		dto.setCreatedAt(new Date());
		return dto;
	}
	
	private LoginFormDto createValidDto() {
		LoginFormDto dto = new LoginFormDto();
		dto.setLogin(LOGIN);
		dto.setPassword(PASSWORD);
		return dto;
	}
}
