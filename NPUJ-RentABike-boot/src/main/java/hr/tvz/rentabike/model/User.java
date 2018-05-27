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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name = "user", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5235678586005452594L;
	
	
	@Id
    @NotNull
	@Column(name= "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@NotEmpty(message = "Niste unjeli korisnièko ime")
	@Size(min = 2, max = 20, message = "Korisnièko ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "username")
	String username;
	

	@NotEmpty(message = "Niste unjeli password")
	@Size(min = 2, max = 100, message = "zaporka treba imati izmeðu 2 i 100 znakova")
	@Column(name = "password")
    String password;
	

	@NotEmpty(message = "Niste unjeli ime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	String name;
	

	@NotEmpty(message = "Niste unjeli prezime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "surname")
	String surname;
	
	
	
	@NotEmpty(message = "Niste unjeli mjesto stanovanja")
	@Size(min = 2, max = 20, message = "mjesto stanovanja treba imati izmeðu 2 i 20 znakova")
	@Column(name = "address")
	String address;
	
	
	@Size(min = 11, max = 11, message = "OIB treba imati toèno 11 znakova")
	@Column(name = "OIB")
	String OIB;
	

	@NotEmpty(message = "Niste unjeli telefonski broj")
	@Size(min = 2, max = 20, message = "Telefonski broj treba imati izmeðu 2 i 20 znakova")
	@Column(name = "phone")
	String phone;
	
	@Past(message = "Datum rodjena nije ispravan")
	@Column(name = "birhtdate")
	public Date birhtdate;
	
	@NotBlank(message = "Unesite vašu email adresu")
	@Email(message= "Email adresa nije ispravno napisana")
	@Column(name = "email")
	String email;
	

	
	@ManyToOne
	//@JoinColumn(name="membershipId")
	@JoinColumn(name="membershiptypeid")
	public MembershipType membershipType;
    
	
	
	@OneToMany(targetEntity=Reservation.class, mappedBy="user", fetch=FetchType.EAGER)	
	public Set<Reservation> reservations; 
	
	
     public User() {}	

	
	public User(String username, String password , String name, String surname , String address, String OIB, String phone,Date birthdate, String email ){
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.address = address;
		this.OIB = OIB;
		this.phone = phone;
		this.birhtdate = birthdate;
		this.email = email;
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password= password;
	}

	public String getPassword() {
		return password;
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
	
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
 
	
	
	public void setOIB(String OIB) {
		this.OIB = OIB;
	}

	
	public String getAvailable() {
		return this.OIB;
	}
 
	
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

    
	public void setBirhtDate(Date birhtdate) {
		this.birhtdate = birhtdate;
	}

	public Date getBirhtDate() {
		return this.birhtdate;
	}

	
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	

	public void set(MembershipType membership) {
		this.membershipType = membership;
	}

	public MembershipType getMembershipType() {
		return this.membershipType;
	}

	
	
	
}
