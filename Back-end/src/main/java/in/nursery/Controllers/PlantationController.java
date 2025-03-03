package in.nursery.Controllers;

import java.util.HashMap;
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

import in.nursery.jwt.JwtService;
import in.nursery.model.Plantation;
import in.nursery.service.PlantOrderService;
import in.nursery.service.PlantationService;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PlantationController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PlantOrderService pos;
	
	@Autowired
	private PlantationService ps;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	  
	@GetMapping("/getPendingOrders")
	public ResponseEntity<?> getPendingOrders(@RequestHeader("Authorization") String authorizationHeader){
		   
		   try {
			   
			   if(jwtService.isTokenExpired(authorizationHeader.substring(7)))
				   return new ResponseEntity<>("{\"message\": \"Jwt token is Expired\"}",HttpStatus.UNAUTHORIZED);
			   
		   }
		   catch (Exception e) {
			
			   e.printStackTrace();
		}
		   
		    return new ResponseEntity<>(pos.getPendingOrders(),HttpStatus.OK);
		   
	   }
	
	@PostMapping("/submitPlantationDetails")
	public ResponseEntity<?> submitPlantationDetails(@RequestHeader("Authorization") String authorizationHeader,@RequestBody List<Plantation> plantation){
		
		   try{
			   
			  if(jwtService.isTokenExpired(authorizationHeader.substring(7)))
				  return new ResponseEntity<>("{\"message\": \"Jwt token is Expired\"}",HttpStatus.UNAUTHORIZED);
			  
			  
			   if(ps.save(plantation)) { 
				   
				    pos.changeStatus(plantation);   
				   
				   return new ResponseEntity<>("{\"message\": \"Data posted Successfully\"}",HttpStatus.OK);
			   }
			   
		   }catch (Exception e) {
			
			   e.printStackTrace();
		}
		 
	 	 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/getCompletedOrders")
	public ResponseEntity<?> getCompletedOrders(@RequestHeader("Authorization") String authorizationHeader){
		
		    try {
		    	
		    	if(jwtService.isTokenExpired(authorizationHeader.substring(7)))
		    		return new ResponseEntity<>("{\"message\": \"Jwt Token Expired\"}",HttpStatus.UNAUTHORIZED);
		    	
		    	    return new ResponseEntity<>(ps.getPlantationDetails(),HttpStatus.OK);
		    	
		    	
		    }catch (Exception e){
				
		    	e.printStackTrace();
		    	
			}
		 
		    return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/getPlantationDetailsOnSearch")
	public ResponseEntity<?> getPlantationDetailsOnSearch(@RequestHeader("Authorization") String authorizationHeader, @RequestBody HashMap<String, Object> map){
		
		   try {
			   
			    return new ResponseEntity<>(ps.search(map),HttpStatus.OK);
			   
		   }catch (Exception e){
			
			   e.printStackTrace();
		}
		
		return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/submitUpdateDetails")
	public ResponseEntity<?> submitUpdateDetails(@RequestHeader("Authorization") String authorization,@RequestBody Object[] objects){
	
		try {
			 
			  List<Plantation> plantations =  objectMapper.convertValue(objects[0], new TypeReference<List<Plantation>>(){});
			  List<Integer> list = objectMapper.readValue(objects[1].toString(), new TypeReference<List<Integer>>(){});
			  
			  ps.submitUpdateDetails(plantations,list);
			  
			  return new ResponseEntity<>("{\"message\": \"Update Success\"}",HttpStatus.OK);
			 
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	     	
		return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		
	}
	 
}

