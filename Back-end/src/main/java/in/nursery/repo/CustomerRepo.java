package in.nursery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nursery.model.Customer;

@Repository
public interface  CustomerRepo extends JpaRepository<Customer, Integer>{

	// Retrieve recored by email
	Customer findByEmail(String email);

	// Retrieve record by email and password
	Customer findByEmailAndPassword(String email, String password);
	
	// Retrieve record by password
	Customer findByPassword(String password);
 	


	

	     
	 
	
	
}
