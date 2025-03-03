package in.nursery.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nursery.model.PlantService;
import in.nursery.service.ComplaintService;
import in.nursery.service.PlantServiceService;


@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PlantServiceController {

	 @Autowired
	 private PlantServiceService pService;
	 
	 @Autowired
	 private ComplaintService complaintService;
	 
	 private ObjectMapper objectMapper = new ObjectMapper();
	
	
	 @PostMapping("/submitServiceDetails")
	 public ResponseEntity<?> submitServiceDetails(@RequestHeader("Authorization") String token,@RequestBody List<PlantService> plantService){
		  
		   
		 
		    if(pService.save(plantService))
		    {
		        complaintService.changeStatus(plantService);    
		    	return new ResponseEntity<>("{\"message\": \"Saved Successfully\"}",HttpStatus.OK);
		    }
		    return new ResponseEntity<>("{\"message\": \"Logout success\"}",HttpStatus.BAD_REQUEST);
		 
	 }
	 
	@GetMapping("/getServiceDetails")
	public ResponseEntity<?> getServiceDetails(@RequestHeader("Authorization") String token){
		
		try {
		return new ResponseEntity<>(pService.getServiceDetails(),HttpStatus.OK);
		}catch (Exception e) {
			
	        e.printStackTrace();
		}
		
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping(value = "/submitUpdatedServiceDetails")
	public ResponseEntity<?> submitUpdatedDetails(@RequestHeader("Authorization") String token,@RequestBody Object[] obj){
				
		  
		      try { 
		    	  
		    	  List<PlantService> plantServices =  objectMapper.convertValue(obj[0], new TypeReference<List<PlantService>>(){});
				  List<Integer> list = objectMapper.readValue(obj[1].toString(), new TypeReference<List<Integer>>(){});
				  
			   
			       pService.submitUpdatedDetails(plantServices,list);
				   
				   return new ResponseEntity<>("{\"message\": \"Submitted Successfully\"}",HttpStatus.OK);
			   
			   
		   }catch (Exception e) {
			
			    e.printStackTrace();
		}
		 
		return new ResponseEntity<>("{\"message\": \"An error occcured\"}",HttpStatus.BAD_REQUEST);	
	}
 	  
}
