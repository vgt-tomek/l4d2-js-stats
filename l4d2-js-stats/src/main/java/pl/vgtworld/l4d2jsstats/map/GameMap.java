package pl.vgtworld.l4d2jsstats.map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "maps")
@NamedQueries({
		@NamedQuery(name = GameMap.QUERY_FIND_ALL,
			query = "SELECT m FROM GameMap m ORDER BY m.displayOrder"),
		@NamedQuery(name = GameMap.QUERY_MOST_PLAYED,
			query = "SELECT new pl.vgtworld.l4d2jsstats.map.dto.MostPlayedMapDto(m.map.id, m.map.name, COUNT(m)) "
				+ "FROM Match m WHERE m.active = TRUE GROUP BY m.map.id ORDER BY COUNT(m) DESC")
})
public class GameMap {
	
	public static final String QUERY_FIND_ALL = "GameMap.findAll";
	
	public static final String QUERY_MOST_PLAYED = "GameMap.mostPlayed";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String image;
	
	@Column(name = "display_order")
	private int displayOrder;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getDisplayOrder() {
		return displayOrder;
	}
	
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
}
