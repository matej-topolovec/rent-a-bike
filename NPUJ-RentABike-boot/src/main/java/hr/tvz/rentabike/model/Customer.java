package hr.tvz.rentabike.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

	@NotEmpty(message = "{validation.customer.name.notEmpty}")
	@Size(min = 2, max = 20, message = "{validation.customer.name.size}")
	@Column(name = "name")
	String name;

	@NotEmpty(message = "{validation.customer.surname.notEmpty}")
	@Size(min = 2, max = 20, message = "{validation.customer.surname.size}")
	@Column(name = "surname")
	String surname;

	@Size(min = 11, max = 11, message = "{validation.customer.OIB.range}")
	@Column(name = "OIB")
	String OIB;

	@Past(message = "{validation.customer.date}")
	@Column(name = "birthdate")
	public Date birthdate;

	@NotBlank(message = "{validation.customer.email.notEmpty}")
	@Email(message= "{validation.customer.email}")
	@Column(name = "email")
	String email;

	@NotEmpty(message = "{validation.customer.address.notEmpty}")
	@Size(min = 2, max = 50, message = "{validation.customer.address.range}")
	@Column(name = "address")
	String address;

	@NotEmpty(message = "{validation.customer.phone.notEmpty}")
	@Size(min = 2, max = 20, message = "{validation.customer.phone.range}")
	@Column(name = "phone")
	String phone;


	@ManyToOne
	//@JoinColumn(name="membershipId")
	@JoinColumn(name="membershiptypeid")
	public MembershipType membershipType;

	@JsonIgnore
	@OneToMany(targetEntity=Reservation.class, mappedBy="customer", fetch=FetchType.EAGER)
	public List<Reservation> reservations;

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

	@JsonIgnore
	public List<Reservation> getReservations() {
		return this.reservations;
	}

	@JsonIgnore
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
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
