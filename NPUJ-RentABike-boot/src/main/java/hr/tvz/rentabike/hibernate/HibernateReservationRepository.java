package hr.tvz.rentabike.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.rentabike.interfaces.ReservationRepository;
import hr.tvz.rentabike.model.Reservation;


@Repository
@Transactional
public class HibernateReservationRepository implements ReservationRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Reservation> findAll() {
		@SuppressWarnings("unchecked")
		List<Reservation> reservations = sessionFactory.getCurrentSession().createQuery("FROM Reservation").list();
		Hibernate.initialize(reservations);
		return reservations;
	}

	@Override
	public Reservation findOne(Integer id) {
		return sessionFactory.getCurrentSession().get(Reservation.class, id);
	}

	@Override
	public Reservation save(Reservation reservation) {
		int id = (int)sessionFactory.getCurrentSession().save(reservation);
		reservation.setId(id);
		return reservation;
	}

}
