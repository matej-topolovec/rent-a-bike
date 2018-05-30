package hr.tvz.rentabike.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rentabike.db.UserRoleRepository;
import hr.tvz.rentabike.model.UserRole;

@RestController
@RequestMapping("/api/administrator")
public class UserRoleRestController {
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	public UserRole save(UserRole userRole){
		return userRoleRepository.save(userRole);
	}
}
