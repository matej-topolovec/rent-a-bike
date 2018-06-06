package hr.tvz.rentabike.db;

import java.io.Serializable;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import hr.tvz.rentabike.model.Bike;

@Primary
@Repository
@Transactional
public class HibernateBikeRepository implements BikeRepository{

	private SessionFactory sessionFactory;
	
	@Autowired
	public HibernateBikeRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bike> findAll() {
		List<Bike> bikes = 

				sessionFactory.getCurrentSession().createQuery("FROM Bike").list();	
		Hibernate.initialize(bikes);
		return bikes;

	}

    @Override
	public Bike findOne(Integer id) {
		return sessionFactory.getCurrentSession().get(Bike.class, id);
	}

	
	
	@Override
	public Bike save(Bike bike) {
	
		Serializable id = sessionFactory.getCurrentSession().save(bike);
		bike.setId((Integer) id);
		return bike;
	}

	@Override
	public void delete(Integer id) {
	     sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Bike.class, id));
		
	}

	@Override
	public void updateBike(Bike bike) {
	   sessionFactory.getCurrentSession().update(bike);
	}
	
	
}
