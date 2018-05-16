package hr.tvz.rentabike.web;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hr.tvz.rentabike.db.JdbcBikeRepository;
import hr.tvz.rentabike.db.LoggingRepository;
import hr.tvz.rentabike.model.Logging;

@Controller
public class RentABikeController {
	
	@Autowired
	LoggingRepository loggingRepository;
	

	@Autowired
	JdbcBikeRepository JdbcBikeRepository;
	
	@GetMapping("/home")
	public String showForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Logging logging = new Logging();
		logging.setUsername(auth.getName());
		logging.setActionTime(timestamp);
		logging.setActions("Get request on homepage");
		
		loggingRepository.save(logging);
		
		return "homePage";
	}
	
	
	
	@GetMapping("/logging")
	@Secured({"ROLE_DEMO" , "ROLE_ADMIN"})
	public String loggingPage(Model model) {
		model.addAttribute("logging", loggingRepository.findAll());
		return "logging";
	}
	
	@GetMapping("/rentabike")
	@Secured({"ROLE_DEMO" , "ROLE_ADMIN"})
	public String RentABike(Model model) {
		model.addAttribute("bike", JdbcBikeRepository.findAll());
		return "bike";
	}
	
	
}
