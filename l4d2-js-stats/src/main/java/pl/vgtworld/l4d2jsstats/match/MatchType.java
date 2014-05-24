package pl.vgtworld.l4d2jsstats.match;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "match_types")
@NamedQueries({
		@NamedQuery(name = MatchType.QUERY_FIND_ALL,
			query = "SELECT t FROM MatchType t ORDER BY t.displayOrder ASC")
})
public class MatchType {
	
	public static final String QUERY_FIND_ALL = "MatchType.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String identifier;
	
	private String name;
	
	@Column(name = "display_order")
	private int displayOrder;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
