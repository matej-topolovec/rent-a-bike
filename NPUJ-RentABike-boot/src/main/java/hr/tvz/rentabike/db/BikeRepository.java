package hr.tvz.rentabike.db;

import hr.tvz.rentabike.model.Bike;

public interface BikeRepository {
	

	Iterable<Bike> findAll();
	
	Bike findOne(String id);
	
	Bike save(Bike bike);

}
