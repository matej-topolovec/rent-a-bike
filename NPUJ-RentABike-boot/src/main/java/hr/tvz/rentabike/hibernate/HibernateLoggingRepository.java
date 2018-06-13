package hr.tvz.rentabike.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.LoggingRepository;
import hr.tvz.rentabike.model.Logging;

@Repository
@Transactional
public class HibernateLoggingRepository implements LoggingRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Logging save(Logging logging) {
		sessionFactory.getCurrentSession().save(logging);
		return logging;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Logging> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Logging").list();
	}

}
