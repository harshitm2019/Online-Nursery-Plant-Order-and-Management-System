package in.nursery.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.nursery.jwt.JwtService;
import in.nursery.model.Customer;
import in.nursery.repo.CustomerRepo;
import in.nursery.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	// Dependency injection
	@Autowired
	private CustomerRepo cRepo;
	
    @Autowired(required = true)
    private PasswordEncoder encoder;
 
	@Autowired
	private JwtService jwtService;
	
	// Method implementation for adding the customer
	
	@Override
	public boolean addCustomer(Customer customer) {
	
		Customer  checkEmail =   cRepo.findByEmail(customer.getEmail());
		
		if(checkEmail != null) return false;
		
        Customer c = cRepo.save(customer);
		if(c == null) return false;
		return true;
	}

	// Method implementation for finding the customer details from the database
	
	@Override
	public Optional<Customer> findCustomer(Integer id) {
		
	  Optional<Customer> customer = cRepo.findById(id); 
		return customer;
	}

	// Method implementation for checking the customer credentials for login
	
	@Override
	public boolean checkCredentials(Customer customer) {
		
		System.out.println(customer.getPassword());
		
		if(cRepo.findByEmailAndPassword(customer.getEmail(), encoder.encode(customer.getPassword())) != null) {
		
	    System.out.println(encoder.encode(customer.getPassword()));
		return true;
		}	
		return false;
	}
  
	// Method implementation for changing password
	
	@Override
	public boolean changePassword(String oldPassword ,String newPassword,String token){
		
	    
		try {
	    	 
	    	 String token1 = token.substring(7);
	         
	         Integer id = jwtService.getCustomerIdFromToken(token1);
	         
	         Optional<Customer> customer = cRepo.findById(id);
	              
	         if(encoder.matches(oldPassword, customer.get().getPassword())) {
	         customer.get().setPassword(encoder.encode(newPassword));	
	         cRepo.save(customer.get());
	         return true;
	         
	         }
	    	 }
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	
	
	
}
