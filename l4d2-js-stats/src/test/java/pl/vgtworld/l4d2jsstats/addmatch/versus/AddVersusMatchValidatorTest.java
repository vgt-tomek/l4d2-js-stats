package pl.vgtworld.l4d2jsstats.addmatch.versus;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchValidator.BaseErrorMessages;
import pl.vgtworld.l4d2jsstats.addmatch.versus.AddVersusMatchValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

public class AddVersusMatchValidatorTest {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final int FORM_MAP_ID = 1;
	
	private static final int FORM_WINNER_POINTS = 8;
	
	private static final int FORM_LOSER_POINTS = 21;
	
	private AddVersusMatchFormDto form;
	
	private AddVersusMatchValidator validator;
	
	private GameMapDto[] maps;
	
	@Before
	public void init() {
		form = createValidForm();
		validator = new AddVersusMatchValidator();
		maps = createMapList();
	}
	
	@Test
	public void shouldAcceptValidForm() {
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).hasSize(0);
	}
	
	@Test
	public void shouldSetParsedDateInAcceptedForm() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		String date = "2014-06-01";
		form.setDate(date);
		
		boolean result = validator.validate(form, maps);
		Date parsedDate = form.getDateParsed();
		
		assertThat(result).isTrue();
		assertThat(date).isEqualTo(df.format(parsedDate));
	}
	
	@Test
	public void shouldNotAcceptMissingMap() {
		form.setMapId(0);
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongMap() {
		form.setMapId(31);
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullDate() {
		form.setDate(null);
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_REQURED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyDate() {
		form.setDate("");
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_REQURED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongDateFormat() {
		form.setDate("random string");
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_FORMAT.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongWinnerPointsValue() {
		form.setWinnerPoints(-21);
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.WINNER_POINTS_WRONG_VALUE.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongLoserPointsValue() {
		form.setLoserPoints(-31);
		
		boolean result = validator.validate(form, maps);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.LOSER_POINTS_WRONG_VALUE.getMessage());
	}
	
	private GameMapDto createMap(int id, String name) {
		GameMapDto dto = new GameMapDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}
	
	private GameMapDto[] createMapList() {
		GameMapDto[] dtoList = new GameMapDto[3];
		dtoList[0] = createMap(1, "first map");
		dtoList[1] = createMap(2, "second map");
		dtoList[2] = createMap(3, "third map");
		return dtoList;
	}
	
	private AddVersusMatchFormDto createValidForm() {
		AddVersusMatchFormDto form = new AddVersusMatchFormDto();
		form.setDate(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		form.setMapId(FORM_MAP_ID);
		form.setWinnerPoints(FORM_WINNER_POINTS);
		form.setLoserPoints(FORM_LOSER_POINTS);
		return form;
	}
	
}
