package hr.tvz.rentabike.jobs;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import hr.tvz.rentabike.interfaces.ReservationRepository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.Reservation;

public class StartReservationJob extends QuartzJobBean {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		List<Reservation> reservations = reservationRepository.findAll();
		Date d = new Date();
	    
		for (Reservation r : reservations) {
			if(r.getStartTime().after(d)) {
				Bike b = r.getBike();
				if(b.getAvailable() > 0) {
					b.setAvailable(b.getAvailable() - 1);
				}
			}	
		}
	}
}