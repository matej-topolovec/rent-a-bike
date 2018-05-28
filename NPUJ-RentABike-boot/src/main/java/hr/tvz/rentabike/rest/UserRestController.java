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

import hr.tvz.rentabike.db.UserRepository;
import hr.tvz.rentabike.model.User;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	UserRepository UserRepository;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public User save(@RequestBody User user) {
		return UserRepository.save(user);
	}
	
	@GetMapping
	public List<User> findAll(){
		return UserRepository.findAll();
	}
	
	public User findOne(String id){
		return UserRepository.findOne(id);
	}
}