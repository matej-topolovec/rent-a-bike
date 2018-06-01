package hr.tvz.rentabike.db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.model.BikeType;
import hr.tvz.rentabike.model.Reservation;

@Repository
@Transactional
public class HibernateReservationRepository implements ReservationRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Reservation> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Reservation", Reservation.class).getResultList();
	}

	@Override
	public Reservation findOne(String id) {
		return sessionFactory.getCurrentSession().get(Reservation.class, id);
	}

	@Override
	public Reservation save(Reservation reservation) {
		int id = (int)sessionFactory.getCurrentSession().save(reservation);
		reservation.setId(id);
		return reservation;
	}

}
