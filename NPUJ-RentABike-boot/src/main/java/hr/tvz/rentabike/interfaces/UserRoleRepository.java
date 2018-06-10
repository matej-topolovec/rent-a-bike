package hr.tvz.rentabike.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rentabike.model.UserRole;

public interface UserRoleRepository {
	UserRole save(UserRole userRole);
}
