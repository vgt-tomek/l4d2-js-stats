package pl.vgtworld.l4d2jsstats.match;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.vgtworld.l4d2jsstats.map.GameMap;
import pl.vgtworld.l4d2jsstats.user.User;

@Entity
@Table(name = "matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private GameMap map;
	
	@Column(name = "played_at")
	private Date playedAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public GameMap getMap() {
		return map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public Date getPlayedAt() {
		return playedAt;
	}
	
	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}
	
}
