package pl.vgtworld.l4d2jsstats.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = User.QUERY_FIND_BY_LOGIN,
			query = "SELECT u FROM User u WHERE u.login = :login"),
		@NamedQuery(name = User.QUERY_FIND_ACTIVE,
			query = "SELECT u FROM User u WHERE u.active = TRUE ORDER BY u.login")
})
public class User {
	
	public static final String QUERY_FIND_BY_LOGIN = "User.findByLogin";
	
	public static final String QUERY_FIND_ACTIVE = "User.findActive";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String login;
	
	private String password;
	
	private String salt;
	
	private boolean active;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
