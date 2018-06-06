package hr.tvz.rentabike.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Reservation implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;
	
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "starttime")
	public Date startTime;
	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "endtime")
	public Date endTime;
	
	
	
	@ManyToOne
	@JoinColumn(name="userid")
	public User user;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bikeid")
	public Bike bike;
	
	
	
	
	
public Reservation() {}	

	
	public Reservation(Date startTime, Date endTime, User user, Bike bike ){
		this.startTime = startTime;
		this.endTime =endTime;
		this.user = user;
		this.bike = bike;
	
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	public Date getEndTime() {
		return endTime;
	}
	
	
	 
		public void setUser(User user) {
			this.user = user;
		}

		public User getUser() {
			return this.user;
		}


		public void setBike(Bike bike) {
			this.bike = bike;
		}

		public Bike getBike() {
			return this.bike;
		}
	
	
	
	
}
