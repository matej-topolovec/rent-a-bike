package hr.tvz.rentabike.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.CustomerRepository;
import hr.tvz.rentabike.model.Customer;

@Repository
@Primary
@Transactional
public class HibernateCustomerRepository implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> findAll() {
		List<Customer> customers = sessionFactory.getCurrentSession().createQuery("FROM Customer").list();
		Hibernate.initialize(customers);
		return customers;
	}
	
    @Override
	public Customer findOne(Integer id) {
		return sessionFactory.getCurrentSession().get(Customer.class, id);
	}
	
	@Override
	public Customer save(Customer c){
		sessionFactory.getCurrentSession().save(c);
		return c;
	}
	
	@Override
	public void deleteCustomer(Integer id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Customer.class, id));
	}
	
	@Override
	public void updateCustomer(Customer c) {
		sessionFactory.getCurrentSession().update(c);
	}
}
