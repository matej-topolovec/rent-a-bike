package hr.tvz.rentabike.db;

import hr.tvz.rentabike.model.Reservation;



public interface ReservationRepository {

    Iterable<Reservation> findAll();
	
	Reservation findOne(String id);
	
	Reservation save(Reservation reservation);
	
}
