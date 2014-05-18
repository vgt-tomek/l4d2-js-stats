package pl.vgtworld.l4d2jsstats.difficulty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "difficulty_levels")
@NamedQueries({
		@NamedQuery(name = DifficultyLevel.QUERY_FIND_ALL,
			query = "SELECT d FROM DifficultyLevel d ORDER BY d.displayOrder")
})
public class DifficultyLevel {
	
	public static final String QUERY_FIND_ALL = "DifficultyLevel.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
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
	
	public int getDisplayOrder() {
		return displayOrder;
	}
	
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
}
