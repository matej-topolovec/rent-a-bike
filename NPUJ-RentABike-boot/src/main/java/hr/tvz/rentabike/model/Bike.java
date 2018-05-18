package hr.tvz.rentabike.model;

import java.io.Serializable;
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
	
	@Column(name = "dateAdded")
	private Date dateAdded;
	
	@NotEmpty(message = "Niste unjeli kolièinu bicikla")
	@Column(name = "quantity")
	private int quantity;
	
	@NotEmpty(message = "Niste unjeli dostupnost bicikla")
	@Column(name = "available")
	private int available;
		
	
	@Valid
	@OneToOne(targetEntity=BikeType.class, cascade=CascadeType.ALL)
	@JoinTable(
			name="type_bike",
            joinColumns = @JoinColumn(name = "typeid"),
            inverseJoinColumns = @JoinColumn(name = "id"))
	private BikeType biketype;
	
	
	
	
	public Bike() {}	

	
	public Bike(String name, Timestamp date_Added, int quantity, int available, BikeType type_bike ){
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




	
}
	
	


