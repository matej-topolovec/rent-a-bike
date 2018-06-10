package hr.tvz.rentabike.hibernate;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.RegistrationRepository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.User;

@Repository
@Transactional
public class HibernateRegistrationRepository implements RegistrationRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User save(User user){
		sessionFactory.getCurrentSession().save(user);
		return user;
	}
	
	@Override
	public User findByUsernameOrEmail(String username, String email){
		Query query = sessionFactory.getCurrentSession().createQuery("FROM User where username = :username and email = :email");
		query.setParameter("username", username);
		query.setParameter("email", email);
		List<User> testList = query.list();
		if(testList.isEmpty())
			return null;
		else
			return testList.get(0);
	}
	
	@Override
	public List<User> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM User").list();
	}
	
	@Override
	public void delete(User user){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, user.getId()));
	}
	
	@Override
	public MembershipType findFirstById(int id){
		return null;
	}
	
	@Override
	public List<User> findAllUsers(){
		return sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE username not in (select username from UserRole) and name is not null").list();
	}
	
	@Override
	public User findById(int id){
		Query query = sessionFactory.getCurrentSession()
				.createQuery("FROM User where id = :id");
		query.setParameter("id", id);
		List<User> testList = query.list();
		return testList.get(0);
	}
}
