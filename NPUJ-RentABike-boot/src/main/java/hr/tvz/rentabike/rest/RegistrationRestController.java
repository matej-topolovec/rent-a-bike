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
	
	@GetMapping
	public List<User> findAll(){
		return registrationRepository.findAll();
	}
	
	public User findByUsernameOrEmail(String username, String email){
		return registrationRepository.findByUsernameOrEmail(username, email);
	}
	
	public List<User> findAllUsers(){
		return registrationRepository.findAllUsers();
	}
	
	public User findById(int id){
		return registrationRepository.findById(id);
	}
	
	public void delete(User user){
		registrationRepository.delete(user);
	}

}
