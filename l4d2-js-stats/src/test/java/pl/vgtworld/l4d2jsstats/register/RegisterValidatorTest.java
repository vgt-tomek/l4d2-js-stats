package pl.vgtworld.l4d2jsstats.register;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.vgtworld.l4d2jsstats.register.RegisterValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class RegisterValidatorTest {
	
	private static final String EMPTY_STRING = "";

	private static final String LOGIN = "somelogin";
	
	private static final String PASSWORD = "somepassword";
	
	private static final String SHORT_LOGIN = "ab";
	
	private static final String LONG_LOGIN = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
		+ "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvw";
	
	private static final String LOGIN_WITH_WRONG_CHARACTERS = "lo gin-$";
	
	private RegisterValidator validator;
	
	private RegisterFormDto form;
	
	@Mock
	private UserService userService;
	
	@Before
	public void initTest() {
		validator = new RegisterValidator();
		form = createValidForm();
	}
	
	@Test
	public void shouldAcceptValidForm() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).hasSize(0);
	}
	
	@Test
	public void shouldNotAcceptNullLogin() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setLogin(null);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyLogin() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setLogin(EMPTY_STRING);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooShortLogin() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setLogin(SHORT_LOGIN);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_SHORT.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooLongLogin() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setLogin(LONG_LOGIN);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_LONG.getMessage());
	}
	
	@Test
	public void shouldNotAcceptLoginWithWrongCharacters() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setLogin(LOGIN_WITH_WRONG_CHARACTERS);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_FORBIDDEN_CHARACTERS.getMessage());
	}
	
	@Test
	public void shouldNotAcceptAlreadyTakenLogin() {
		when(userService.isLoginAvailable(anyString())).thenReturn(false);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TAKEN.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullPassword() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setPassword(null);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyPassword() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setPassword(EMPTY_STRING);
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptDifferentPasswords() {
		when(userService.isLoginAvailable(anyString())).thenReturn(true);
		form.setRepeatPassword("differentPassword");
		
		boolean result = validator.validate(form, userService);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_MISMATCH.getMessage());
	}
	
	private RegisterFormDto createValidForm() {
		RegisterFormDto dto = new RegisterFormDto();
		dto.setLogin(LOGIN);
		dto.setPassword(PASSWORD);
		dto.setRepeatPassword(PASSWORD);
		return dto;
	}
}
