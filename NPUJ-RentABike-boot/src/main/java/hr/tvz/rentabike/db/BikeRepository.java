package hr.tvz.rentabike.db;

import java.util.List;

import hr.tvz.rentabike.model.Bike;




public interface BikeRepository {
	
	Bike save(Bike bike);
	
	List<Bike> findAll();
	
	void delete(Integer id);
	
	Bike findOne(Integer id);
	
	void updateBike(Bike bike);
	
}
	

	

