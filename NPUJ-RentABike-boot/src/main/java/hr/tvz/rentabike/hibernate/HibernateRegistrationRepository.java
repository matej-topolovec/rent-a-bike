package hr.tvz.rentabike.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.RegistrationRepository;
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
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM User").list();
	}
	
	@Override
	public void delete(User user){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, user.getId()));
	}
	
	@Override
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, id));
	}
	
	@Override
	public MembershipType findFirstById(int id){
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers(){
		return sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE username not in (select username from UserRole) and name is not null").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findById(int id){
		Query query = sessionFactory.getCurrentSession()
				.createQuery("FROM User where id = :id");
		query.setParameter("id", id);
		List<User> testList = query.list();
		return testList.get(0);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
		
	}
}
