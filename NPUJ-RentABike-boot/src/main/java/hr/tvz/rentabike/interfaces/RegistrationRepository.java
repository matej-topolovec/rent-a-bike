package hr.tvz.rentabike.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.User;

public interface RegistrationRepository {
	User save(User user);
	List<User> findAll();
	User findByUsernameOrEmail(String username, String email);
	MembershipType findFirstById(int id);
	
	@Query(value = "SELECT * FROM user WHERE username not in (select username from user_role) and name is not null", nativeQuery = true)
	List<User> findAllUsers();
	
	User findById(int id);
	void delete(User user);
	void delete(int id);
}
