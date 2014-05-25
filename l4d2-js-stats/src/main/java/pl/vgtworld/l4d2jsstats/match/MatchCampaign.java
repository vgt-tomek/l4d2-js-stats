package pl.vgtworld.l4d2jsstats.match;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.vgtworld.l4d2jsstats.difficulty.DifficultyLevel;

@Entity
@Table(name="matches_campaign")
public class MatchCampaign implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Match match;
	
	private int time;
	
	@ManyToOne
	private DifficultyLevel difficulty;
	
	private int restarts;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public DifficultyLevel getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	public int getRestarts() {
		return restarts;
	}

	public void setRestarts(int restarts) {
		this.restarts = restarts;
	}
	
}
