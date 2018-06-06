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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class Customer implements Serializable{

	private static final long serialVersionUID = -5235678586005452594L;

	@Id
    @NotNull
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Niste unijeli ime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	String name;

	@NotEmpty(message = "Niste unijeli prezime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "surname")
	String surname;

	@Size(min = 11, max = 11, message = "OIB treba imati toèno 11 znakova")
	@Column(name = "OIB")
	String OIB;

	@Past(message = "Datum rodjena nije ispravan")
	@Column(name = "birthdate")
	public Date birthdate;

	@NotBlank(message = "Unesite email adresu")
	@Email(message= "Email adresa nije ispravno napisana")
	@Column(name = "email")
	String email;

	@NotEmpty(message = "Niste unjeli mjesto stanovanja")
	@Size(min = 2, max = 20, message = "mjesto stanovanja treba imati izmeðu 2 i 20 znakova")
	@Column(name = "address")
	String address;

	@NotEmpty(message = "Niste unjeli telefonski broj")
	@Size(min = 2, max = 20, message = "Telefonski broj treba imati izmeðu 2 i 20 znakova")
	@Column(name = "phone")
	String phone;

	
	@ManyToOne
	//@JoinColumn(name="membershipId")
	@JoinColumn(name="membershiptypeid")
	public MembershipType membershipType;

    public Customer() {}	

	public Customer(String name, String surname, String OIB, Date birthdate, String email, String address, String phone){
		this.name = name;
		this.surname = surname;
		this.OIB = OIB;
		this.birthdate = birthdate;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}

	public void setName(String name) {
		this.name= name;
	}

	public String getName() {
		return name;
	}

	public void setSurname(String surname) {
		this.surname= surname;
	}

	public String getSurname() {
		return surname;
	}

	public void setOIB(String OIB) {
		this.OIB = OIB;
	}

	public String getOIB() {
		return this.OIB;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setMembershipType(MembershipType membership) {
		this.membershipType = membership;
	}

	public MembershipType getMembershipType() {
		return this.membershipType;
	}
}
