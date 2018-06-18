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
import hr.tvz.rentabike.model.User;






@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BikeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	hr.tvz.rentabike.db.BikeRepository BikeRepository;
	


	@Test
	public void BikeGetFindAllView() throws Exception{
		mockMvc.perform(get("/bikes")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("bikes"))
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("bike"));
			}
	
	
	@Test
	public void BikeGetCreateView() throws Exception{
		mockMvc.perform(get("/bike/create")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("Bike"))
		.andExpect(model().attributeExists("BikeTypes"))
		.andExpect(view().name("EditBike"));
			}
	
	
	@Test
	public void BikeGetEditView() throws Exception{
		mockMvc.perform(get("/bike/edit/1")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("Bike"))
		.andExpect(model().attributeExists("BikeTypes"))
		.andExpect(view().name("EditBike"));
			}
	
	
	
	@Test
	public void BikeGetDetailsView() throws Exception{
		mockMvc.perform(get("/bike/details/1")
				.with(user("admin").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(model().attributeExists("bike"))
		.andExpect(view().name("bikeDetails"));
			}

	
	
	@Test
	public void BikeFindOne() throws Exception{
	   Bike b = BikeRepository.findOne(1);
	   assertEquals(b.getName(), "Trek");
	   assertEquals(b.getBikeType().getName(), "Cestovni");
			}
	
	
	
	@Test
	public void BikeFindAll() throws Exception{
		List<Bike> list = BikeRepository.findAll();
		assertNotNull(list);
	}
	
	
	
	@Test(expected= Exception.class)
	public void BikeDeleteOneException() throws Exception{
		List<Bike> Before = BikeRepository.findAll();
		BikeRepository.delete(2);
		assertThat(Before, is(not(BikeRepository.findAll())));
	}
	
		
	
	@Test
	public void BikeUpdateOne() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		Bike Before = BikeRepository.findOne(1);
		Before.setName("Fuji");
		Before.setAvailable(1);
		Before.setQuantity(1);
		Before.setDate(date);
		BikeRepository.updateBike(Before);;
		assertThat(Before, is(not(BikeRepository.findAll())));
	}
	
	
	@Test
	public void BikeSaveOne() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
	    BikeType bt = new BikeType();
	    bt.setId(1);
	    
		Bike bike = new Bike();
		bike.setName("Fuji");
		bike.setDate(date);
		bike.setQuantity(3);
		bike.setAvailable(3);
		bike.setBikeType(bt);
		
		BikeRepository.save(bike);
		
		List<Bike> bikes = BikeRepository.findAll();
		
		Bike lastbike = bikes.get(bikes.size() -1);
		
		assertEquals(lastbike.getId(), 4);
		assertEquals(lastbike.getName(), "Fuji");
		assertEquals(lastbike.getBikeType().getName(), "Cestovni");
	}

	
	@Test
	public void BikeDeleteOne() throws Exception{
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
	    BikeType bt = new BikeType();
	    bt.setId(1);
	    
		Bike bike = new Bike();
		bike.setName("Fuji");
		bike.setDate(date);
		bike.setQuantity(3);
		bike.setAvailable(3);
		bike.setBikeType(bt);
		
		BikeRepository.save(bike);
		
		
	List<Bike> listBefore = BikeRepository.findAll();
		
		BikeRepository.delete(bike.getId());
		
		assertThat(listBefore, is(not(BikeRepository.findAll())));
	}
	
	
	
}
