package in.nursery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nursery.ServiceImpl.MyCustomerDetailsService;
import in.nursery.config.MyData;
import in.nursery.jwt.JwtService;
import in.nursery.model.Staff;
import in.nursery.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*" ,allowedHeaders = "*")
@RestController
public class StaffContoller {

	 // Dependency Injection
	 @Autowired
	 private  StaffService  staffService;   
	 
	 @Autowired
	 private JwtService jwtService;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private MyCustomerDetailsService myCustomerDetailsService;
	 
	 
	 // Creating object 
	 private final ObjectMapper objectMapper = new ObjectMapper();
	 
	 @Autowired
	 private PasswordEncoder encoder;
	 
	
	 //API for admin login
	 
	 @PostMapping("/staffLogin")
	 public ResponseEntity<String> login(@RequestBody Staff staff){
		 
		 
		 myCustomerDetailsService.isCustomerOrStaff("Staff");
		 
		 UsernamePasswordAuthenticationToken token = 
		    		new UsernamePasswordAuthenticationToken(staff.getEmail(), staff.getPassword());
		    
		    try {
		    	
		    	Authentication authentication = authenticationManager.authenticate(token);
		    	if(authentication.isAuthenticated()) {
		    		
		    		String jwtToken = jwtService.generateToken(staff.getEmail());
		    		Integer Id = jwtService.getStaffIdFromToken(jwtToken);
		    		String jsonResponse = objectMapper.writeValueAsString(new MyData(jwtToken,Id));
	                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	                
		    	}	
		    }
		       catch (Exception e) {
				e.printStackTrace();
			} 
		    
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid Credentials\"}");		  
	 }
	 
	 //API for Staff Registration
	 
	 @PostMapping("/createStaff")
	 public ResponseEntity<String> createStaff(@RequestBody Staff staff){
		 
		  String encode = encoder.encode(staff.getPassword());
		  staff.setPassword(encode);
		    
		    if(staffService.add(staff)) return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Staff created\"}");
		    
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Duplicate Email\"}");
		 
	 }
	 
	// API implementation for checking Admin password
	 @PostMapping(value = "/checkAdminPassword")
	 public ResponseEntity<String> checkAdminPassword(@RequestBody Staff staff){
		 
		     System.out.println(staff.getPassword()); 
		 
             if(staffService.checkCredentials(staff) != null){
            	
            	return new ResponseEntity<String>("{\"message\": \"Password Valid!\"}",HttpStatus.OK);		   
            	
            }
		 
		     return  new ResponseEntity<String>("{\"message\": \"Invalid Password\"}",HttpStatus.BAD_REQUEST);
	 }
	 
	// API implementation for logout
	 @PostMapping("/logout")
	 public ResponseEntity<String> logout(Authentication authentication,HttpServletResponse response ,HttpServletRequest request){
		 
		  myCustomerDetailsService.isCustomerOrStaff(null);

 	    try {
 	    	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
 	    	logoutHandler.logout(request, response, authentication);
 	  
             return new ResponseEntity<String>("{\"message\": \"Logout success\"}",HttpStatus.OK);
 	    }
 	    catch (Exception e) {
		        
 	    	e.printStackTrace();
			}

      return new ResponseEntity<String>("{\"message\": \"Logout not success\"}",HttpStatus.BAD_GATEWAY);  
		 
	 }
}
