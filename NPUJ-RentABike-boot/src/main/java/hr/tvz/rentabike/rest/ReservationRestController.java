package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rentabike.interfaces.ReservationRepository;
import hr.tvz.rentabike.model.Reservation;

@RestController
@RequestMapping(path="/api/reservations", produces="application/json")
public class ReservationRestController {
	@Autowired
	ReservationRepository ReservationRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Reservation save(@RequestBody Reservation r) {
		return ReservationRepository.save(r);
	}
	@GetMapping
	public List<Reservation> findAll() {
		return ReservationRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Reservation> findOne(@PathVariable String id) {
		Reservation reservation = ReservationRepository.findOne(id);
		if(reservation != null) {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
			} else{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
	}
}
