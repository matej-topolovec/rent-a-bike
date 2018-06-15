package hr.tvz.rentabike.interfaces;

import java.util.List;


import hr.tvz.rentabike.model.Reservation;


public interface ReservationRepository  {

    List<Reservation> findAll();
	
	Reservation findOne(Integer id);
	
	Reservation save(Reservation reservation);
	
}
