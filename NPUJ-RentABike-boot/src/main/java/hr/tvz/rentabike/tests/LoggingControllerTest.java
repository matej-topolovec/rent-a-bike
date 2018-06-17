package hr.tvz.rentabike.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import hr.tvz.rentabike.interfaces.LoggingRepository;
import hr.tvz.rentabike.model.Logging;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoggingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	LoggingRepository loggingRepository;
	
	@Test
	public void loggingGetFindAll() throws Exception{
		mockMvc.perform(get("/logging")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("logging"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("logging"));
	}
	
	@Test
	public void loggingPostSave() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Logging logging = new Logging();
		logging.setUsername("admin");
		logging.setActionTime(date);
		logging.setActions("Test that tests logging");
		
		loggingRepository.save(logging);
		
		List<Logging> listLogs = loggingRepository.findAll();
		
		Logging lastLog = listLogs.get(listLogs.size() -1);
		
		assertEquals(lastLog.getUsername(), "admin");
		assertEquals(lastLog.getActions(), "Test that tests logging");
	}
	
}










