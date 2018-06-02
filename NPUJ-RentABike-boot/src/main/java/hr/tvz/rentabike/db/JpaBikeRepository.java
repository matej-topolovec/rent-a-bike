package hr.tvz.rentabike.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rentabike.model.Bike;

public interface JpaBikeRepository extends JpaRepository<Bike, Integer> {
	
	Bike save(Bike bike);
	Bike findOne(int id);
	List<Bike>findAll();
	

}
