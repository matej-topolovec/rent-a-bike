package hr.tvz.rentabike.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "bike", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Bike implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;


	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "{validation.bike.name.notEmpty}")
	@Size(min = 2, max = 20, message = "{validation.bike.name.size}")
	@Column(name = "name")
	private String name;
	
	 
	@Column(name = "dateadded")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date dateadded;
	
	@Min(0)
	@NotNull(message = "{validation.bike.notNull}")
	@Column(name = "quantity")
	private int quantity;
	
	@Min(0)
	@NotNull(message = "{validation.bike.notNull}")
	@Column(name = "available")
	private int available;
		
	
    //@Valid
	@JsonIgnore
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="typeid")
	public BikeType biketype;
	
		
	@JsonIgnore
	@OneToMany(targetEntity=Reservation.class, mappedBy="bike", fetch=FetchType.EAGER)	
	public List<Reservation> reservations; 
	
	
	
	public Bike() {}	
	
	public Bike(String name, Date date_Added, int quantity, int available, BikeType biketype ){
		this.name = name;
		this.dateadded = date_Added;
		this.quantity = quantity;
		this.available = available;
		this.biketype= biketype;
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date_Added) {
		this.dateadded= date_Added;
	}

	public Date getDate() {
		return dateadded;
	}

	public int getQuantity() {
		return this.quantity;
	}
 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}

	public int getAvailable() {
		return this.available;
	}
 
	public void setBikeType(BikeType bike_type) {
		this.biketype = bike_type;
		
	}

	public BikeType getBikeType() {
		return this.biketype;
	}

	@JsonIgnore
	public List<Reservation> getSetBikes() {
		return this.reservations;	
	}
	
	@JsonIgnore
	public void setEmployees(List<Reservation> reservations) {
		this.reservations = reservations;
		}

	
}
	
	