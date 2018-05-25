package hr.tvz.rentabike.db;



import hr.tvz.rentabike.model.User;

public interface UserRepository {
	
	Iterable<User> findAll();
	
	User findOne(String id);
	
	User save(User user);

}
