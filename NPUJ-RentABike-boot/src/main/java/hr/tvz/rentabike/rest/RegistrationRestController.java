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

import hr.tvz.rentabike.interfaces.RegistrationRepository;
import hr.tvz.rentabike.model.User;

@RestController
@RequestMapping("/api/registration")
public class RegistrationRestController {
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User save(@RequestBody User user) {
		return registrationRepository.save(user);
	}
	
	@PutMapping(consumes="application/json")
	public void update(@RequestBody User user){
		  registrationRepository.update(user);
	}
	
	@GetMapping
	public List<User> findAll(){
		return registrationRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = registrationRepository.findById(id);
		if(user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		registrationRepository.delete(id);
	}

}
