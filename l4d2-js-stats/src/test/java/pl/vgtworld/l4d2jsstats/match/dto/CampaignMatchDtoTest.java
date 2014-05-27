package pl.vgtworld.l4d2jsstats.match.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CampaignMatchDtoTest {
	
	@Test
	public void shouldProperlyConvertTotalTimeToReadableForm() {
		CampaignMatchDto dto = new CampaignMatchDto();
		
		dto.setTotalTime(90);
		
		assertThat(dto.getTotalTimeHumanReadable()).isEqualTo("1:30");
	}
	
	@Test
	public void shouldProperlyConvertOneMinuteTotalTime() {
		CampaignMatchDto dto = new CampaignMatchDto();
		
		dto.setTotalTime(1);
		
		assertThat(dto.getTotalTimeHumanReadable()).isEqualTo("0:01");
	}
	
	@Test
	public void shouldProperlyConvertOneHourTotalTime() {
		CampaignMatchDto dto = new CampaignMatchDto();
		
		dto.setTotalTime(60);
		
		assertThat(dto.getTotalTimeHumanReadable()).isEqualTo("1:00");
	}
	
}
