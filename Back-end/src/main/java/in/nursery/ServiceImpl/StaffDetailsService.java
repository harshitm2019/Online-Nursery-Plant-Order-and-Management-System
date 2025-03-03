package in.nursery.ServiceImpl;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.nursery.model.Staff;
import in.nursery.repo.StaffRepo;

@Service
public class StaffDetailsService implements UserDetailsService{

	@Autowired
	private StaffRepo staffRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Staff s = staffRepo.findByEmail(username);
	    if(s == null)
	    	
	    	throw new UsernameNotFoundException("Staff not found with : " + username);
	    	
	    return new User(s.getEmail(),s.getPassword(),getAuthorities());	
	
	 
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(){
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_STAFF"));
		
	}

}
