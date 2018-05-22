package hr.tvz.rentabike.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name = "bike_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class BikeType implements Serializable{
	
	
	
	private static final long serialVersionUID = -5235678586005452594L;
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@NotEmpty(message = "Niste unjeli vrstu bicikla")
	@Size(min = 2, max = 20, message = "Vrsta Bicikla treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	private String name;


	public void setId(int id) {
		this.id= id;
		
	}
	
	public int getId(int id) {
		return this.id;
		
	}
	
	
	public void setName(String name) {
		this.name= name;
		
	}
	
	public String getName(int id) {
		return this.name;
		
	}
	

}
