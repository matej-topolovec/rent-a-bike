package hr.tvz.rentabike.db;

import java.util.List;

import hr.tvz.rentabike.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

	Customer findOne(String id);

	Customer save(Customer c);
	
	int updateCustomer(Customer c);
	
	void deleteCustomer(String id);

}