package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rentabike.db.CustomerRepository;
import hr.tvz.rentabike.model.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired
	CustomerRepository CustomerRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Customer save(@RequestBody Customer c) {
		return CustomerRepository.save(c);
	}

	@GetMapping
	public List<Customer> findAll() {
		return CustomerRepository.findAll();
	}

	public Customer findOne(String id) {
		return CustomerRepository.findOne(id);
	}
}