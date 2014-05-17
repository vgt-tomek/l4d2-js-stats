package pl.vgtworld.l4d2jsstats.register;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import pl.vgtworld.l4d2jsstats.register.RegisterValidator.ErrorMessages;

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
	
	@Before
	public void initTest() {
		validator = new RegisterValidator();
		form = createValidForm();
	}
	
	@Test
	public void shouldAcceptValidForm() {
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).hasSize(0);
	}
	
	@Test
	public void shouldNotAcceptNullLogin() {
		form.setLogin(null);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyLogin() {
		form.setLogin(EMPTY_STRING);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooShortLogin() {
		form.setLogin(SHORT_LOGIN);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_SHORT.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooLongLogin() {
		form.setLogin(LONG_LOGIN);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_LONG.getMessage());
	}
	
	@Test
	public void shouldNotAcceptLoginWithWrongCharacters() {
		form.setLogin(LOGIN_WITH_WRONG_CHARACTERS);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_FORBIDDEN_CHARACTERS.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullPassword() {
		form.setPassword(null);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyPassword() {
		form.setPassword(EMPTY_STRING);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptDifferentPasswords() {
		form.setRepeatPassword("differentPassword");
		
		boolean result = validator.validate(form);
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
