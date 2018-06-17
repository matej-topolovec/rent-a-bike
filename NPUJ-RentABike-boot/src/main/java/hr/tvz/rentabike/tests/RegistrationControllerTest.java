package hr.tvz.rentabike.tests;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withUnauthorizedRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import hr.tvz.rentabike.interfaces.RegistrationRepository;
import hr.tvz.rentabike.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Test
	public void loginOK() throws Exception{
		this.mockMvc
			.perform(get("/home").with(user("admin").password("password")
					.roles("ADMIN")))
			.andExpect(status().isOk());	
	}
	
	@Test
	public void loginNotOK() throws Exception{
		this.mockMvc
			.perform(get("/login").with(user("admin").password("p12345")
					.roles("ADMIN")))
			.andExpect(status().is(403));
	}
	
	@Test
	public void registrationGetTest() throws Exception{
		this.mockMvc
			.perform(get("/registration"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("User"))
			.andExpect(view().name("registration"));
	}
	
	@Test
	public void registrationRestGetFindAll() throws Exception{
		List<User> list = registrationRepository.findAll();
		assertNotNull(list);
	}
	
	@Test
	public void registrationRestGetFindOne() throws Exception{
		User user = registrationRepository.findById(1);
		assertEquals(user.getUsername(), "admin");
		assertEquals(user.getName(), "Adminko");
		assertEquals(user.getSurname(), "Adminko");
	}
	
	@Test
	public void registrationRestDelete() throws Exception{
		List<User> listBefore = registrationRepository.findAll();
		
		registrationRepository.delete(5);
		
		assertThat(listBefore, is(not(registrationRepository.findAll())));
	}
	
	@Test
	public void registrationRestPostTestOk() throws Exception{
		List<User> listBefore = registrationRepository.findAll();
		
		User user = new User();
		user.setId(10);
		user.setUsername("TestUsername");
		user.setPassword("password");
		user.setName("DenisTest");
		user.setSurname("AlibasicTest");
		user.setAddress("AdresaTest");
		user.setPhone("123456");
		user.setEmail("denisAli@email.com");
		
		
		mockMvc.perform(post("/api/registration", user))
			.andExpect(status().is3xxRedirection());
		 
		List<User> listAfter= registrationRepository.findAll();
		
		assertThat(listBefore, is(not(listAfter)));
	}
	
	@Test(expected = NestedServletException.class)
	public void registrationRestPostTestNotOk() throws Exception{
		User user = new User();
		user.setId(10);
		user.setUsername("TestUsername123");
		user.setPassword("password");
		user.setName("");
		user.setSurname("AlibasicTest");
		user.setAddress("AdresaTest");
		user.setPhone("123456");
		user.setEmail("denisAli12@email.com");
		
		mockMvc.perform(post("/registration", user))
			.andExpect(status().is4xxClientError());
	}
}














