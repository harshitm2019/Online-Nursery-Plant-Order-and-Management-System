package in.nursery.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nursery.ServiceImpl.MyCustomerDetailsService;
import in.nursery.config.MyData;
import in.nursery.jwt.JwtService;
import in.nursery.model.Customer;
import in.nursery.repo.CustomerRepo;
import in.nursery.service.CustomerService;
import in.nursery.utils.Address;
import in.nursery.utils.ContactUs;
import in.nursery.utils.EmailUtils;
import in.nursery.utils.GetIdFormToken;
import in.nursery.utils.OneParameter;
import in.nursery.utils.TwoParameters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*" ,allowedHeaders = "*")
@RestController
public class CustomerController {

	// Dependency Injection
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	 private CustomerService customerService;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MyCustomerDetailsService myCustomerDetailsService;
	
	
	// Creating object
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private GetIdFormToken idFormToken = new GetIdFormToken();
	
	
	// API Implementation for customer registration
	
	 @PostMapping(value = "/register")
	 public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
		 
		  
		 String encodedPwd = passwordEncoder.encode(customer.getPassword());
		 customer.setPassword(encodedPwd);
		 System.out.println(encodedPwd);
		 
		  boolean  status = customerService.addCustomer(customer);
		  
		  if(status)  return ResponseEntity.status(HttpStatus.CREATED)
                  .contentType(MediaType.APPLICATION_JSON)
                  .body("{\"message\": \"Registration successful!\"}");
		  
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Invalid data or duplicate username."); 		 
	 }
	 
	//  API Implementation for customer login
	 
	 @PostMapping("/login")
	 @PreAuthorize("hasRole('ROLE_CUSTOMER')")
	 public ResponseEntity<String> handleLogin(@RequestBody Customer customer){
		    
		     myCustomerDetailsService.isCustomerOrStaff("Customer");
		 
		     try {
		    	
		    	 UsernamePasswordAuthenticationToken token = 
				    		new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
				   
		    	Authentication authentication = authenticationManager.authenticate(token);
		    	SecurityContextHolder.getContext().setAuthentication(authentication);
		    	
		    		if(authentication.isAuthenticated()) {
		    		String jwtToken = jwtService.generateToken(customer.getEmail());
		    		Integer id = jwtService.getCustomerIdFromToken(jwtToken);
		    		String jsonResponse = objectMapper.writeValueAsString(new MyData(jwtToken,id));
	                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		    		
		    		}
		    	
		    }
		       catch (Exception e){
		    	   
				e.printStackTrace();
				
			} 
		        	
		    	
			return new ResponseEntity<>("Invalid Credentials",HttpStatus.BAD_REQUEST);
							
	 }	      
	 
	//  API Implementation for get customer details
	 
     @GetMapping("/getCustomer")
     public ResponseEntity<?> getCustomer(@RequestHeader(value = "Authorization") String authorizationHeader,HttpServletResponse res){
    	     	 
    	 try {
    		
    	 String token = authorizationHeader.substring(7);
    	 
    	 if(jwtService.isTokenExpired(token))
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Jwt token is expired\"}");	 
 	     	
         
        
         Integer id = jwtService.getCustomerIdFromToken(token);
         
         Optional<Customer> customer = customerRepo.findById(id);
         return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    	 }
      catch (Exception e) {
         
         e.printStackTrace(); // Log the exception for debugging
         return new ResponseEntity<>("{\"message\": \"An error occurred\"}",HttpStatus.BAD_REQUEST); // Return null or handle the exception appropriately
     }
    	   

     }
     
     //API implementation for Password changing
     @PostMapping("/changePasswordCustomer")
     public ResponseEntity<String> changePassword(@RequestHeader("Authorization")String auString ,  @RequestBody TwoParameters twoParameters){
    	 
    	 
    	  if(customerService.changePassword(twoParameters.getParam1(), twoParameters.getParam2(),auString))
    		  return new ResponseEntity<String>("{\"message\": \"Password Changed\"}",HttpStatus.OK);
    	  
    	  else return new ResponseEntity<String>("{\"message\": \"Password Not changed\"}",HttpStatus.BAD_REQUEST);
    	  
     }
     
     // API implementation for Address Changing
     @PostMapping("/changeAddress")
     public ResponseEntity<String> changeAddress(@RequestHeader("Authorization")String auString ,@RequestBody Address address){
    	    
    	         Optional<Customer> c = idFormToken.getId(auString,jwtService, customerRepo);
    	         
    	    	 if(c != null){
    	    		 
    	    	 c.get().setState(address.getState());
    	    	 c.get().setCity(address.getCity());
    	    	 c.get().setPincode(address.getPincode());
    	    	 c.get().setcAdress(address.getAddress());
    	    	 customerRepo.save(c.get()); 
    	    	 return new ResponseEntity<>("{\"message\": \"Address Changed\"}",HttpStatus.OK);
    	    	 
    	    	 } 	   	 
    	  return new ResponseEntity<>("{\"message\": \"Addres not Changed\"}",HttpStatus.BAD_GATEWAY);
    	 
     }
     
  // API implementation for Contact Changing
     @PostMapping("/changeContact")
     public ResponseEntity<String> changeContact(@RequestHeader("Authorization")String auString,@RequestBody OneParameter oneP){
    	 
    	 
    	 Optional<Customer> c = idFormToken.getId(auString,jwtService,customerRepo);
    	 
    	 if(c != null) {
    		 
    		 c.get().setContactNo(oneP.getParam1());
    		 customerRepo.save(c.get());
    		 return new ResponseEntity<>("{\"message\": \"Contact changed\"}",HttpStatus.OK);
    		 
    	 }
    		 
    	 return new ResponseEntity<>("{\"message\": \"Contact not Changed\"}",HttpStatus.BAD_REQUEST);
    	 
     }
     
  // API implementation for logout
     @RequestMapping("/login")
     @GetMapping("/logout")
     public ResponseEntity<String> logout(Authentication authentication,HttpServletRequest request,HttpServletResponse response){
       
    	         myCustomerDetailsService.isCustomerOrStaff(null);
    	 
    	    try {
    	    	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    	    	logoutHandler.logout(request, response, authentication);
    	  
                return new ResponseEntity<String>("{\"message\": \"Logout success\"}",HttpStatus.OK);
    	    }
    	    catch (Exception e){
		        
    	    	e.printStackTrace();
			}
 
         return new ResponseEntity<String>("{\"message\": \"Logout not success\"}",HttpStatus.BAD_GATEWAY);   	  
         
     }
     
     @PostMapping("/contactUs")
     public ResponseEntity<?> contactUs(@RequestBody ContactUs contactUs){
    	 
    	  try {
    		  
    		  
    		  String text =  "Your Query : " + "\n" +  contactUs.getMessage();
        	  emailUtils.sendMail(contactUs.getEmail(), "Our Agent will reach You shortly",text,null);
        	  return new ResponseEntity<>("{\"message\": \"Query Submitted\"}",HttpStatus.OK);
    		  
		} catch(Exception e){
			
			e.printStackTrace();
			
		}
    	 
    	 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
     }
     
     
     @DeleteMapping("/delete")
     public ResponseEntity<String> dele(){
    	 
    	 return new ResponseEntity<String>("a",HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    	 
     }
     
}
