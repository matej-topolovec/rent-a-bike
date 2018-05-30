package hr.tvz.rentabike.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257421884338115618L;
	
	@Id
    @NotNull
	@Column(name= "user_role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "role")
	public String role;
	
	public UserRole(){}

	public UserRole(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
