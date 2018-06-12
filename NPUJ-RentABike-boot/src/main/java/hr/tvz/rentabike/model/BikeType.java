package hr.tvz.rentabike.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "bike_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id") })
public class BikeType implements Serializable{
	
	
	
	private static final long serialVersionUID = -5235678586005452594L;
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	
	@NotEmpty(message = "Niste unjeli vrstu bicikla")
	@Size(min = 2, max = 20, message = "Vrsta Bicikla treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	public String name;

	
	
	
	@JsonIgnore
	@OneToMany(targetEntity=Bike.class, mappedBy="biketype",  orphanRemoval = true , cascade = CascadeType.ALL, fetch=FetchType.LAZY)	
	@Column(nullable = true)
//	@OneToMany
//	@JoinTable(name="bike",
//    joinColumns = @JoinColumn(name="id"),
//	inverseJoinColumns = @JoinColumn(name="typeid", unique=true))
	public List<Bike> bikes = new ArrayList<Bike>();
	
	
	
	public BikeType() {}

	public void setId(int id) {
		this.id= id;
		
	}
	
	public int getId() {
		return this.id;
		
	}
	
	
	public void setName(String name) {
		this.name= name;
		
	}
	

	public String getName() {
		return this.name;
		
	}
	
	
	@JsonIgnore
	 public List<Bike> getSetBikes() {
		return this.bikes;	
	}
	
	@JsonIgnore
	public void setEmployees(List<Bike> bikes) {
		this.bikes = bikes;
		}
	

}
