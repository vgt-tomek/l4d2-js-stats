package pl.vgtworld.l4d2jsstats.addmatch.campaign;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import pl.vgtworld.l4d2jsstats.addmatch.AddMatchValidator.BaseErrorMessages;
import pl.vgtworld.l4d2jsstats.addmatch.campaign.AddCampaignMatchValidator.ErrorMessages;
import pl.vgtworld.l4d2jsstats.difficulty.dto.DifficultyLevelDto;
import pl.vgtworld.l4d2jsstats.map.dto.GameMapDto;

public class AddCampaignMatchValidatorTest {
	
	private static final String FORM_TOTAL_TIME = "1:20";
	
	private static final int FORM_RESTART_COUNT = 3;
	
	private static final int FORM_MAP_ID = 1;
	
	private static final int FORM_DIFFICULTY_ID = 2;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private AddCampaignMatchFormDto form;
	
	private AddCampaignMatchValidator validator;
	
	private GameMapDto[] maps;
	
	private DifficultyLevelDto[] difficultyLevels;
	
	@Before
	public void init() {
		form = createValidForm();
		validator = new AddCampaignMatchValidator();
		maps = createMapList();
		difficultyLevels = createDifficultyLevelList();
	}

	@Test
	public void shouldAcceptValidForm() {
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).hasSize(0);
	}
	
	@Test
	public void shouldSetParsedDateInAcceptedForm() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
		String date = "2014-05-31";
		form.setDate(date);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		Date parsedDate = form.getDateParsed();
		
		assertThat(result).isTrue();
		assertThat(date).isEqualTo(df.format(parsedDate));
	}
	
	@Test
	public void shouldSetParserdTotalTimeInAcceptedForm() {
		String totalTime = "1:21";
		form.setTotalTime(totalTime);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		int totalTimeParsed = form.getTotalTimeParsed();
		
		assertThat(result).isTrue();
		assertThat(totalTimeParsed).isEqualTo(81);
	}
	
	@Test
	public void shouldNotAcceptMissingMap() {
		form.setMapId(0);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongMapId() {
		form.setMapId(21);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.MAP_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullDate() {
		form.setDate(null);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_REQURED.getMessage());
	}

	@Test
	public void shouldNotAcceptEmptyDate() {
		form.setDate("");
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_REQURED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptWrongFormatDate() {
		form.setDate("random-string");
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(BaseErrorMessages.DATE_FORMAT.getMessage());
	}
	
	@Test
	public void shouldNotAcceptMissingDifficulty() {
		form.setDifficultyId(0);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.DIFFICULTY_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullTotalTime() {
		form.setTotalTime(null);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.TOTAL_TIME_REQUIRED.getMessage());
	}

	@Test
	public void shouldNotAcceptEmptyTotalTime() {
		form.setTotalTime("");
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.TOTAL_TIME_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptInvalidTotalTime() {
		form.setTotalTime("random-string");
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.TOTAL_TIME_INVALID.getMessage());
	}
	
	@Test
	public void shouldNotAcceptInvalidRestars() {
		form.setRestarts(-1);
		
		boolean result = validator.validate(form, maps, difficultyLevels);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(ErrorMessages.RESTARTS_INVALID.getMessage());
	}

	private DifficultyLevelDto createDifficultyLevel(int id, String name) {
		DifficultyLevelDto dto = new DifficultyLevelDto();
		dto.setId(id);
		dto.setName(name);
		return dto;
	}
	
	private DifficultyLevelDto[] createDifficultyLevelList() {
		DifficultyLevelDto[] dtoList = new DifficultyLevelDto[3];
		dtoList[0] = createDifficultyLevel(1, "easy");
		dtoList[1] = createDifficultyLevel(2, "normal");
		dtoList[2] = createDifficultyLevel(3, "hard");
		return dtoList;
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

	private AddCampaignMatchFormDto createValidForm() {
		AddCampaignMatchFormDto form = new AddCampaignMatchFormDto();
		form.setDate(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		form.setDifficultyId(FORM_DIFFICULTY_ID);
		form.setMapId(FORM_MAP_ID);
		form.setRestarts(FORM_RESTART_COUNT);
		form.setTotalTime(FORM_TOTAL_TIME);
		return form;
	}
}
