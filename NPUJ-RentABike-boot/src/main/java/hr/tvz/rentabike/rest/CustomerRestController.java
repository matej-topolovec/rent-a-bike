package hr.tvz.rentabike.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.rentabike.interfaces.CustomerRepository;
import hr.tvz.rentabike.model.Customer;

@RestController
@RequestMapping(path="/api/customers", produces="application/json")
@CrossOrigin(origins="*")
public class CustomerRestController {

	@Autowired
	CustomerRepository CustomerRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Customer save(@RequestBody Customer c) {
		return CustomerRepository.save(c);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> findAll() {
		return CustomerRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findOne(@PathVariable Integer id) {
		Customer c = CustomerRepository.findOne(id);
		if(c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		CustomerRepository.deleteCustomer(id);
	}
	
	@PutMapping
	public void update(@RequestBody Customer c){
		  CustomerRepository.updateCustomer(c);
	}
}