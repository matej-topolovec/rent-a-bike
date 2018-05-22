package hr.tvz.rentabike.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

@Entity
@Table(name = "reservation", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Reservation {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;
	
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "startTime")
	Date startTime;
	
	@Column(name = "endTime")
	Date endTime;
	
	
	
	@Valid
	@OneToOne(targetEntity=User.class, cascade=CascadeType.ALL)
	@JoinTable(
			name="User",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "id"))
	private User user;
	
	
	@Valid
	@OneToOne(targetEntity=Bike.class, cascade=CascadeType.ALL)
	@JoinTable(
			name="bike",
            joinColumns = @JoinColumn(name = "bikeId"),
            inverseJoinColumns = @JoinColumn(name = "id"))
	
	private Bike bike;
	
	
	
}
