package hr.tvz.rentabike.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import hr.tvz.rentabike.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	hr.tvz.rentabike.interfaces.CustomerRepository CustomerRepository;
	
	@Test
	public void CustomerGetFindAllView() throws Exception{
		mockMvc.perform(get("/customers")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("customers"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("customers"));
	}
	
	@Test
	public void CustomerGetCreateView() throws Exception{
		mockMvc.perform(get("/customer/new")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("Customer"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("membershipType"))
		.andExpect(view().name("customersEdit"));
	}
	
	@Test
	public void CustomerGetDetailsView() throws Exception{
		mockMvc.perform(get("/customer/details/1")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("Customer"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("customersDetails"));
	}
	
	@Test
	public void CustomerGetEditView() throws Exception{
		mockMvc.perform(get("/customer/edit/1")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("Customer"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("membershipType"))
		.andExpect(view().name("customersEdit"));
	}
	
	@Test(expected= Exception.class)
	public void CustomerDeleteOne() throws Exception{
		List<Customer> Before = CustomerRepository.findAll();
		CustomerRepository.deleteCustomer(2);
		assertThat(Before, is(not(CustomerRepository.findAll())));
	}
	
	@Test
	public void CustomerFindOne() throws Exception{
		Customer c = CustomerRepository.findOne(1);
	   assertEquals(c.getName(), "Pero");
	   assertEquals(c.getSurname(), "Periæ");
	   assertEquals(c.getOIB(), "12345678901");
	   assertEquals(c.getBirthdate(), "2018-01-01");
	   assertEquals(c.getEmail(), "pero.peric@net.hr");
	   assertEquals(c.getAddress(), "Ulica 1, 10000 Zagreb");
	   assertEquals(c.getPhone(), "012/3456-789");
	   assertEquals(c.getMembershipType().getId(), 1);
			}
	
	
	
	@Test
	public void CustomerFindAll() throws Exception{
		List<Customer> list = CustomerRepository.findAll();
		assertNotNull(list);
	}
	
	@Test
	public void CustomerUpdateOne() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		Customer Before = CustomerRepository.findOne(1);
		Before.setName("Ivica");
		Before.setSurname("Ivièiæ");
		Before.setOIB("98765432109");
		Before.setBirthdate(new Date(2000, 1, 1));
		Before.setEmail("ivica.ivicic@mail.com");
		Before.setAddress("Ulica 2, 10000 Zagreb");;
		Before.setPhone("012/9876-543");
		CustomerRepository.updateCustomer(Before);
		assertThat(Before, is(not(CustomerRepository.findAll())));
	}
	
	
	@Test
	public void CustomerSaveOne() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
	    
		Customer customer = new Customer();
		customer.setName("Test");
		customer.setSurname("Test");
		customer.setOIB("98765432109");
		customer.setBirthdate(new Date(2000, 1, 1));
		customer.setEmail("test@mail.com");
		customer.setAddress("Test address");;
		customer.setPhone("012/9876-543");
		
		CustomerRepository.save(customer);
		
		List<Customer> customers = CustomerRepository.findAll();
		
		Customer lastCustomer = customers.get(customers.size() - 1);
		
		assertEquals(lastCustomer.getId(), 13);
		assertEquals(lastCustomer.getName(), "Test");
		assertEquals(lastCustomer.getSurname(), "Test");
		assertEquals(lastCustomer.getOIB(), "98765432109");
		Date birthdate = lastCustomer.getBirthdate();
		assertEquals(birthdate.getYear(), 2000);
		assertEquals(birthdate.getMonth(), 1);
		assertEquals(birthdate.getDate(), 1);
		assertEquals(lastCustomer.getEmail(), "test@mail.com");
		assertEquals(lastCustomer.getAddress(), "Test address");
		assertEquals(lastCustomer.getPhone(), "012/9876-543");
	}
}
