package hr.tvz.rentabike.db;

import java.util.List;


import hr.tvz.rentabike.model.Reservation;


public interface ReservationRepository  {

    List<Reservation> findAll();
	
	Reservation findOne(String id);
	
	Reservation save(Reservation reservation);
	
}
