package pl.vgtworld.l4d2jsstats.register;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import pl.vgtworld.l4d2jsstats.register.RegisterValidator.ErrorMessages;

public class RegisterValidatorTest {
	
	private static final String LOGIN = "somelogin";
	
	private static final String PASSWORD = "somepassword";
	
	private static final String SHORT_LOGIN = "ab";
	
	private static final String LONG_LOGIN = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
		+ "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvw";
	
	private static final String LOGIN_WITH_WRONG_CHARACTERS = "lo gin-$";
	
	private RegisterValidator validator;
	
	private MultivaluedMap<String, String> form;
	
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
		form.putSingle("login", null);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyLogin() {
		form.putSingle("login", "");
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooShortLogin() {
		form.putSingle("login", SHORT_LOGIN);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_SHORT.getMessage());
	}
	
	@Test
	public void shouldNotAcceptTooLongLogin() {
		form.putSingle("login", LONG_LOGIN);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_TOO_LONG.getMessage());
	}
	
	@Test
	public void shouldNotAcceptLoginWithWrongCharacters() {
		form.putSingle("login", LOGIN_WITH_WRONG_CHARACTERS);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOGIN_FORBIDDEN_CHARACTERS.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullPassword() {
		form.putSingle("password", null);
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyPassword() {
		form.putSingle("password", "");
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptDifferentPasswords() {
		form.putSingle("password-repeat", "differentPassword");
		
		boolean result = validator.validate(form);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.PASSWORD_MISMATCH.getMessage());
	}
	
	private MultivaluedMap<String, String> createValidForm() {
		MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
		map.putSingle("login", LOGIN);
		map.putSingle("password", PASSWORD);
		map.putSingle("password-repeat", PASSWORD);
		return map;
	}
}
