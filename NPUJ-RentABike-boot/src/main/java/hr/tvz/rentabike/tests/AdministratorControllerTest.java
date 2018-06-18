package hr.tvz.rentabike.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import hr.tvz.rentabike.interfaces.RegistrationRepository;
import hr.tvz.rentabike.interfaces.UserRoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdministratorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Test
	public void administratorGetPage() throws Exception{
		mockMvc.perform(get("/administrator")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("Newuser"))
		.andExpect(view().name("administrator"));
	}
	
	@Test
	public void administratorSaveUser() throws Exception{
		
	}
	
	@Test
	public void administratorDeleteUser() throws Exception{
		
	}
}









