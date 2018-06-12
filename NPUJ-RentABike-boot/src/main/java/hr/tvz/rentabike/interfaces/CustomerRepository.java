package hr.tvz.rentabike.interfaces;

import java.util.List;

import hr.tvz.rentabike.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

	Customer findOne(Integer id);

	Customer save(Customer c);
	
	void updateCustomer(Customer c);
	
	void deleteCustomer(Integer id);

}