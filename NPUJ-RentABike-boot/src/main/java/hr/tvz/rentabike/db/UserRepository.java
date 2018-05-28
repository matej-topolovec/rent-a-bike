package hr.tvz.rentabike.db;

import java.util.List;

import hr.tvz.rentabike.model.User;


public interface UserRepository {
	
	List<User> findAll();
	
	User findOne(String id);
	
	User save(User user);

}
