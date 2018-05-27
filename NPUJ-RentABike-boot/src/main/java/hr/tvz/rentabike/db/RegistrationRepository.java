package hr.tvz.rentabike.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.User;

public interface RegistrationRepository extends JpaRepository<User, Integer> {
	User save(User user);
	List<User> findAll();
	User findByUsernameOrEmail(String username, String email);
	MembershipType findFirstById(int id);
}
