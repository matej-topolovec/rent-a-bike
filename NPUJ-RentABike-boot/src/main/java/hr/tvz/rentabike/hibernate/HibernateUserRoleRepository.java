package hr.tvz.rentabike.hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.UserRoleRepository;
import hr.tvz.rentabike.model.UserRole;

@Repository
@Transactional
public class HibernateUserRoleRepository implements UserRoleRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserRole save(UserRole userRole) {
		sessionFactory.getCurrentSession().save(userRole);
		return userRole;
	}

}
