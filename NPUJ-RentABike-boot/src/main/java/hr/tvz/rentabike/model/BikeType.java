package hr.tvz.rentabike.model;

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
@Table(name = "Bike", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class BikeType {
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@NotEmpty(message = "Niste unjeli vrstu bicikla")
	@Size(min = 2, max = 20, message = "Vrsta Bicikla treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	private String name;
	

}
