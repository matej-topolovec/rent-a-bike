package hr.tvz.rentabike.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "membershipType", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class MembershipType {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;
	
	
	@Id
    @NotNull
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Niste unjeli ime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	private String name;
	

    @Column(name= "discountRate")
	private int discountRate;


    @Column(name= "durationInMonths")
	private int durationInMonths;
	
	
    public MembershipType() {}	

	
	public MembershipType(String name, int discountrate , int durationInMonths){
		this.name = name;
		this.discountRate = discountrate;
		this.durationInMonths = durationInMonths;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	
	
	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate= discountRate;
	}

	public int getDiscountRate() {
		return this.discountRate;
	}

	
	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths= durationInMonths;
	}

	public int getDurationInMonths() {
		return this.durationInMonths;
	}
	
	
}
