package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import hr.tvz.rentabike.db.BikeRepository;
import hr.tvz.rentabike.model.Bike;



@RestController
@RequestMapping(path="/api/bikes", produces="application/json")
public class BikeRestControler {
	
	@Autowired
     BikeRepository BikeRepository;
  
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes="application/json")
	public Bike save(@RequestBody Bike b) {
		return BikeRepository.save(b);
	}

	@GetMapping
	public List<Bike> findAll() {
		return BikeRepository.findAll();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Bike> findOne(@PathVariable("id") Integer id) {
		Bike bike = BikeRepository.findOne(id);
		if(bike != null) {
			return new ResponseEntity<>(bike, HttpStatus.OK);
			} else{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
	}
	
	
	//
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		 BikeRepository.delete(id);
	}

	
	// path="/put" 
	@PutMapping(consumes="application/json")
	public void update(@RequestBody Bike bike){
		  BikeRepository.updateBike(bike);
	}
	
}
