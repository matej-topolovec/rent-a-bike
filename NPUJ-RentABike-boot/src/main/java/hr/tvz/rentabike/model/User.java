package hr.tvz.rentabike.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name = "user", uniqueConstraints = {
@UniqueConstraint(columnNames = "id") })
public class User {
	
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
	@Size(min = 2, max = 20, message = "zaporka treba imati izmeðu 2 i 20 znakova")
	@Column(name = "password")
    String password;
	

	@NotEmpty(message = "Niste unjeli ime")
	@Size(min = 2, max = 20, message = "Ime treba imati izmeðu 2 i 20 znakova")
	@Column(name = "name")
	String name;
	

	@NotEmpty(message = "Niste unjeli mjesto stanovanja")
	@Size(min = 2, max = 20, message = "mjesto stanovanja treba imati izmeðu 2 i 20 znakova")
	@Column(name = "address")
	String address;
	
	
	@NotEmpty(message = "Niste unjeli osobni OIB")
	@Size(min = 11, max = 11, message = "Korisnièko ime treba imati toèno 11 znakova")
	@Column(name = "OIB")
	String OIB;
	

	@NotEmpty(message = "Niste unjeli telefonski broj ime")
	@Size(min = 2, max = 20, message = "Telefonski broj treba imati izmeðu 2 i 20 znakova")
	@Column(name = "phone")
	String phone;
	
	@Past(message = "Datum rodjena nije ispravan")
	@Column(name = "birhtdate")
	Date birhtdate;
	
	@NotBlank(message = "Unesite vašu email adresu")
	@Email(message= "Email adresa nije ispravno napisana")
	@Column(name = "email")
	String email;
	

	@Valid
	@OneToOne(targetEntity=MembershipType.class, cascade=CascadeType.ALL)
	@JoinTable(
			name="membershipType",
            joinColumns = @JoinColumn(name = "membershipId"),
            inverseJoinColumns = @JoinColumn(name = "id"))
	MembershipType membershipType;
    
     public User() {}	

	
	public User(String username, String password , String name, String address, String OIB, String phone,Date birthdate, String email ){
		this.username = username;
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
		return name;
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

	public String getPhone(String phone) {
		return this.phone;
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
		return this.phone;
	}

	public void set(MembershipType membership) {
		this.membershipType = membership;
	}

	public MembershipType getMembershipType() {
		return this.membershipType;
	}

	
	
	
}
