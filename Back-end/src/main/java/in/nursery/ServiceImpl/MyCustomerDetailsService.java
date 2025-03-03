package in.nursery.ServiceImpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.nursery.model.Customer;
import in.nursery.model.Staff;
import in.nursery.repo.CustomerRepo;
import in.nursery.repo.StaffRepo;

//Implementation of the Spring Security UserDetailsService interface.
// This service is responsible for loading user-specific data during authentication.

@Service
public class MyCustomerDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private StaffRepo staffRepo;
	
	private static String check = "";
	

	//Load user-specific data by userName.
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println(check);
		
		if("Staff".equals(check))
			
			return staff(username);
		
		Customer c = customerRepo.findByEmail(username);
		
		if(c == null)	
			
			throw new UsernameNotFoundException("Customer Not found");
			
		   return new User(c.getEmail(),c.getPassword(),Collections.emptyList());
		  		
	}
	
//	 private Collection<? extends GrantedAuthority> getAuthorities(){
//		 
//	        return Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
//	        
//	    }
	
	public void isCustomerOrStaff(String s) {
		
	      check = s;
		
	}
		
		public UserDetails staff(String username) {
			
			Staff s = staffRepo.findByEmail(username);
			
			if(s == null)
				
				throw new UsernameNotFoundException("Staff Not Found");
			
			return new User(s.getEmail(),s.getPassword(),Collections.emptyList());
			
		}

}
