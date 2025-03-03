package in.nursery.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import in.nursery.model.AMC;
import in.nursery.service.AmcService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class AmcController {

	 @Autowired
	 private AmcService amcService;
	 
	 
	 @PostMapping("/addAmcPlan")
	 public ResponseEntity<String> addAmcPlan(@RequestHeader("Authorization") String authorizationHeader,@RequestBody AMC amc){
		 
		 try {
			 
		 if(amcService.addAmcPlan(amc))
			 return new ResponseEntity<>("{\"message\": \"Added Successfully\"}",HttpStatus.OK);
		 
		 }catch (Exception e){
			
			 e.printStackTrace();
				
		}
		 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
 
	 }
	 
	 @GetMapping("/getId")
	 public ResponseEntity<?> getId(@RequestHeader("Authorization") String authorizationHeader){
		 

		 try {
			 
		     return new ResponseEntity<>(amcService.getId(),HttpStatus.OK);
	    	 
		 
		 }catch (Exception e){
			
			 e.printStackTrace();
				
		}
		 
		 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		 
	 }
	 
	 @PostMapping("/delete")
	 public ResponseEntity<String> deletePlan(@RequestHeader("Authorization") String authorizationHeader,@RequestBody String name){
		 
         try {
			
	    	 
	    	 amcService.deletePlan(name);
		 
		     return new ResponseEntity<>("{\"message\": \"Deleted successfully\"}",HttpStatus.OK);
	    	 
		 
		 }catch (Exception e){
			
			 e.printStackTrace();
				
		}
		 
		 
         return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.OK);
		   
		 
	 }
	 
	 @GetMapping("/getAmcPlan")
	 public ResponseEntity<?> getAmcPlan(@RequestHeader("Authorization") String authorizationHeader){
		   
           try {
			 
			 
	    	 
	    	 return  new ResponseEntity<>(amcService.getAmcPlan(),HttpStatus.OK);  
		 
		 }catch (Exception e){
			
			 e.printStackTrace();
		}
		  
        return new ResponseEntity<>("{\"message\": \"Jwt token is expired\"}",HttpStatus.BAD_REQUEST);   
		  
	 }
	
	
}
