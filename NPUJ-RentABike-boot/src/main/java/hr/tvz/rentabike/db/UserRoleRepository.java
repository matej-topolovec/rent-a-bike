package hr.tvz.rentabike.db;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rentabike.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	UserRole save(UserRole userRole);
}
