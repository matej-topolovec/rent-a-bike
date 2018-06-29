package hr.tvz.rentabike.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.BikeType;
import hr.tvz.rentabike.model.Customer;
import hr.tvz.rentabike.model.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	hr.tvz.rentabike.interfaces.ReservationRepository ReservationRepository;

	@Test
	public void ReservationsGetFindAllView() throws Exception {
		mockMvc.perform(get("/reservations").with(user("admin").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(model().attributeExists("reservations"))
				.andExpect(model().attributeExists("user")).andExpect(view().name("reservations"));
	}

	@Test
	public void ReservationGetNewView() throws Exception {
		mockMvc.perform(get("/reservations/new").with(user("admin").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(model().attributeExists("user"))
				.andExpect(model().attributeExists("Reservation")).andExpect(model().attributeExists("bike"))
				.andExpect(model().attributeExists("customer")).andExpect(view().name("reserveBike"));
	}

	@Test
	public void ReservationPostTestNew() throws Exception {
		List<Reservation> listBefore = ReservationRepository.findAll();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		BikeType bt = new BikeType();
		bt.setId(1);

		Bike bike = new Bike();
		bike.setId(5);
		bike.setName("Fuji");
		bike.setDate(date);
		bike.setQuantity(3);
		bike.setAvailable(3);
		bike.setBikeType(bt);

		Customer customer = new Customer();
		customer.setName("Test");
		customer.setSurname("Test");
		customer.setOIB("98765432109");
		customer.setBirthdate(new Date(2000, 1, 1));
		customer.setEmail("test@mail.com");
		customer.setAddress("Test address");
		;
		customer.setPhone("012/9876-543");

		Reservation r = new Reservation();
		r.setId(5);
		r.setStartTime(date);
		r.setEndTime(date);
		r.setCustomer(customer);
		r.setBike(bike);
		Error e = new Error();

		mockMvc.perform(post("reservations/new", r)).andExpect(status().is3xxRedirection());

		List<Reservation> listAfter = ReservationRepository.findAll();

		assertThat(listBefore, is(not(listAfter)));
	}

}
