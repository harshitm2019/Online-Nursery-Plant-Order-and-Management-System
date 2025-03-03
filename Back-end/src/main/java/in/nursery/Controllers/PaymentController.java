package in.nursery.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.nursery.service.PaymentService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PaymentController {

     @Autowired
     private PaymentService paymentService;
	
	 @GetMapping("getTotalSales")
	 public ResponseEntity<?> getTotalSales(@RequestHeader("Authorization") String token){
		 
		   try {
			
			   return new ResponseEntity<>(paymentService.getTotalSales(),HttpStatus.OK);
					   
		} catch (Exception e) {
		
			 e.printStackTrace();
			    
		}
		  
           return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);		
		   
	 }
	 
	 @PostMapping("/searchByDate")
	 public ResponseEntity<?> searchByDate(@RequestHeader("Authorization") String token
			 ,@RequestBody HashMap<String, Object> map){
		 
		 
		    try {
				
		    	 return new ResponseEntity<>(paymentService.searchByDate(map),HttpStatus.OK);
		    	
			} catch (Exception e) {
				
				 e.printStackTrace();
				
			}
		 
		 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		 
	 }
	

}
