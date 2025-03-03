package in.nursery.utils;

import java.util.Optional;

import in.nursery.jwt.JwtService;
import in.nursery.model.Customer;
import in.nursery.repo.CustomerRepo;

// for retrieve customer id to use it for fetching other records 
public class GetIdFormToken {

	
	 public Optional<Customer> getId(String auString, JwtService jwtService, CustomerRepo customerRepo) {
		 
		 try {
 	    	
	    	 String token = auString.substring(7);
	    	 
	    	 Integer id = jwtService.getCustomerIdFromToken(token);
	    	 
	    	 Optional<Customer> c = customerRepo.findById(id);
	    	 
	    	 return c;
	 }
		 catch (Exception e) {
			
			  e.printStackTrace();
			 
		}
		 
		 return Optional.empty();
}
}
