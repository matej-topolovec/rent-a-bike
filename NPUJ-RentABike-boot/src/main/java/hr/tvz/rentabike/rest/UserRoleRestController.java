package hr.tvz.rentabike.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rentabike.interfaces.UserRoleRepository;
import hr.tvz.rentabike.model.UserRole;

@RestController
@RequestMapping("/api/userrole")
public class UserRoleRestController {
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserRole save(@RequestBody UserRole userRole){
		return userRoleRepository.save(userRole);
	}
}
