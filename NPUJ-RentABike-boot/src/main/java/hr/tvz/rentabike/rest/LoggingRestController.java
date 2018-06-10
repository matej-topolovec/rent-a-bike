package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import hr.tvz.rentabike.interfaces.LoggingRepository;
import hr.tvz.rentabike.model.Logging;

@RestController
@RequestMapping("/api/logging")
public class LoggingRestController {
	
	@Autowired
	LoggingRepository loggingRepository;
	
	@GetMapping
	public List<Logging> findAll(){
		return loggingRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Logging save(@RequestBody Logging logging) {
		return loggingRepository.save(logging);
	}
}
