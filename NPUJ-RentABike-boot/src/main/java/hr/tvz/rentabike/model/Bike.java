package hr.tvz.rentabike.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;



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
	
	@NotEmpty(message = "Niste unjeli ime bicikla")
	@Size(min = 2, max = 20, message = "Ime Bicikla treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	private String name;
	
	@Column(name = "dateadded")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date dateAdded;
	
	@NotNull(message = "Niste unjeli kolièinu bicikla")
	@Column(name = "quantity")
	private int quantity;
	
	
	@NotNull(message = "Niste unjeli kolièinu dostupnih bicikla")
	@Column(name = "available")
	private int available;
		
	
	@ManyToOne
	@JoinColumn(name="typeid")
	public BikeType biketype;
	
		
  	
	@OneToMany(targetEntity=Reservation.class, mappedBy="bike", fetch=FetchType.LAZY)	
	public Set<Reservation> reservations; 
	
	
	
	public Bike() {}	
	
	public Bike(String name, Date date_Added, int quantity, int available, BikeType type_bike ){
		this.name = name;
		this.dateAdded = date_Added;
		this.quantity = quantity;
		this.available = available;
		this.biketype= type_bike;
		
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
		this.dateAdded= date_Added;
	}

	public Date getDate() {
		return dateAdded;
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


	public Set<Reservation> getSetBikes() {
		return this.reservations;	
	}
	
	public void setEmployees(Set<Reservation> reservations) {
		this.reservations = reservations;
		}


	
}
	
	


