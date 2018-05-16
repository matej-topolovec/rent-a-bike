package hr.tvz.rentabike.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;







@Entity
@Table(name = "Bike", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class Bike{
	
	/**
	 * 
	 */

	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Niste unjeli ime bicikla")
	@Size(min = 2, max = 20, message = "Ime Bicikla treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	private String name;
	
	@Column(name = "date_Added")
	Timestamp date_Added;
	
	@NotEmpty(message = "Niste unjeli kolièinu bicikla")
	@Column(name = "quantity")
	int quantity;
	
	@NotEmpty(message = "Niste unjeli dostupnost bicikla")
	@Column(name = "available")
	int available;
		
	
	@Valid
	@OneToOne(targetEntity=BikeType.class, cascade=CascadeType.ALL)
	@JoinTable(
			name="Bike_Type",
            joinColumns = @JoinColumn(name = "BikeType"),
            inverseJoinColumns = @JoinColumn(name = "Bike"))
	private BikeType bike_type;
	
	public Bike() {}	

	
	public Bike(String name, Timestamp date_Added, int quantity, int available ){
		this.name = name;
		this.date_Added = date_Added;
		this.quantity = quantity;
		this.available = available;
		
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

	public void setDate(Timestamp date_Added) {
		this.date_Added= date_Added;
	}

	public Timestamp setDate() {
		return date_Added;
	}

	public int getQuntity() {
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
		this.bike_type = bike_type;
	}

	public BikeType getBikeType() {
		return this.bike_type;
	}




	
}
	
	


