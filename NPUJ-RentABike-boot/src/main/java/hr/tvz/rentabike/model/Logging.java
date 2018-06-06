package hr.tvz.rentabike.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;





@Entity
@Table(name = "logging", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Logging implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;
	
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	@Column(name = "username")
	public String username;
	@Column(name = "action")
	public String actions;
	@Column(name = "action_time")
	public Date action_time;
	
	public Logging(){}
	
	public Logging(String username, String actions, Date action_time){
		this.username = username;
		this.actions = actions;
		this.action_time = action_time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Date getActionTime() {
		return action_time;
	}

	public void setActionTime(Date action_time) {
		this.action_time = action_time;
	}
	
	
}
