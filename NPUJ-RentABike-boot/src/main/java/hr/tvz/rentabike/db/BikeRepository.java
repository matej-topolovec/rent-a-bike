package hr.tvz.rentabike.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.BikeType;



public interface BikeRepository extends JpaRepository<Bike, Integer> {
	
	Bike save(Bike bike);
	List<Bike> findAll();
	BikeType findFirstById(Integer id);
	void delete(Integer id);
	Bike findOne(Integer id);
	
}
	

	

