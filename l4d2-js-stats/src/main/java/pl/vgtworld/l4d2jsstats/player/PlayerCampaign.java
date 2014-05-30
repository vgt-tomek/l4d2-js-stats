package pl.vgtworld.l4d2jsstats.player;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "players_campaign")
@NamedQueries({
		@NamedQuery(name = PlayerCampaign.QUERY_FIND_FROM_MATCH,
			query = "SELECT c FROM PlayerCampaign c JOIN c.player p WHERE p.match.id = :matchId")
})
public class PlayerCampaign implements Serializable {
	
	public static final String QUERY_FIND_FROM_MATCH = "PlayerCampaign.findFromMatch";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	private Player player;
	
	private boolean survived;
	
	private int deaths;
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isSurvived() {
		return survived;
	}
	
	public void setSurvived(boolean survived) {
		this.survived = survived;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
}
