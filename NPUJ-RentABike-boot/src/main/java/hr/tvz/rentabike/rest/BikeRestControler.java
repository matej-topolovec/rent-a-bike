package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import hr.tvz.rentabike.model.Bike;



@RestController
@RequestMapping("/api/bikes")
public class BikeRestControler {
	
	@Autowired
    hr.tvz.rentabike.db.JdbcBikeRepository JdbcBikeRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Bike save(@RequestBody Bike b) {
		return JdbcBikeRepository.save(b);
	}

	@GetMapping
	public List<Bike> findAll() {
		return JdbcBikeRepository.findAll();
	}

	public Bike findOne(Integer id) {
		return JdbcBikeRepository.findOne(id);
	}
	
	

}
