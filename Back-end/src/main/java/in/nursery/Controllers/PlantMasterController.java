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

import in.nursery.model.PlantMaster;
import in.nursery.service.PlantMasterService;
import in.nursery.utils.SearchCriteria;

@CrossOrigin(allowedHeaders = "*",origins = "*")
@RestController
public class PlantMasterController {

	
	  @Autowired
	  private PlantMasterService plantMasterService;
	  
	
	  @PostMapping("/addPlant")
	  public ResponseEntity<String> addPlant(@RequestHeader("Authorization") String token,@RequestBody PlantMaster plantMaster){
		  
		    
		     if(plantMasterService.add(plantMaster))
             return new ResponseEntity<String>("{\"message\": \"Data posted successfully\"}",HttpStatus.OK); 		  
		  
		     return new ResponseEntity<String>("{\"message\": \"Data Addition Failed\"}",HttpStatus.BAD_REQUEST);
	  }
	  
	  @PostMapping("/updatePlant")
	  public ResponseEntity<String> updatePlant(@RequestHeader("Authorization") String token,@RequestBody PlantMaster plantMaster){
		  
		   
		   if(plantMasterService.add(plantMaster)) 
			   
			 return new ResponseEntity<String>("{\"message\": \"Data updated successfully\"}",HttpStatus.OK);
		   
		     return new ResponseEntity<String>("{\"message\": \"Updation Failed\"}",HttpStatus.BAD_REQUEST);
	  }
	  
	  @PostMapping("/searchPlant")
	  public List<PlantMaster> searchPlant(@RequestHeader("Authorization") String token,@RequestBody SearchCriteria searchCriteria) {
		 
		  return plantMasterService.searchBasedOnCriteria(searchCriteria);
		  
	  }
	  
	  @PostMapping("searchPlants")
	  public ResponseEntity<?> searchPlants(@RequestBody SearchCriteria searchCriteria){
		  
		   
		  return new ResponseEntity<>(plantMasterService.searchBasedOnCriteria(searchCriteria),HttpStatus.OK);
		  
	  }  
	  
	  @GetMapping("/getPlantId")
	  public ResponseEntity<?> getPlantId(@RequestHeader("Authorization") String token){
		  
		  
		     try {
				
		    	  return new ResponseEntity<>(plantMasterService.getPlantId(),HttpStatus.OK);
		    	 
			} catch(Exception e) {
				
				 e.printStackTrace();
				
			}  
		  
		  return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		  
	  }
 	  
 }
	

