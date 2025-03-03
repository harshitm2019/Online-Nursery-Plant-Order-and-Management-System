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

import in.nursery.jwt.JwtService;
import in.nursery.model.Complaint;
import in.nursery.service.ComplaintService;


@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private JwtService jwtService;
	

	
	@PostMapping("/submitComplaint")
	public ResponseEntity<?> submitComplaint(@RequestHeader("Authorization") String token
			,@RequestBody Complaint complaint){
		
		try {
			
		    complaint.setcId(jwtService.getCustomerIdFromToken(token.substring(7)));
			if(complaintService.submitComplaint(complaint)) 
				
				return new ResponseEntity<>("{\"message\": \"Complaint submited\"}",HttpStatus.OK);		
		}
		catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@GetMapping("/getComplaintDetails")
	public ResponseEntity<?>  getComplaintDetails(@RequestHeader("Authorization") String token){
		
		   try {
			   
			    Integer id = jwtService.getCustomerIdFromToken(token.substring(7));
			    
			    return new ResponseEntity<>(complaintService.getComplaintDetails(id),HttpStatus.OK);
			    
		   }
		   catch (Exception e){
			
			   e.printStackTrace();			   
		}
		 
		 return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getPendingComplaint")
	public ResponseEntity<?> getPendingComplaint(@RequestHeader("Authorization") String token){
		
		 return new ResponseEntity<>(complaintService.getPendingComplaint(),HttpStatus.OK); 
	}
	
	 @GetMapping("/getCompletedComplaint")
	 public ResponseEntity<?> getCompletedComplaint(@RequestHeader("Authorization") String token){
		 
		 
		  return new ResponseEntity<>(complaintService.getCompletedComplaint(),HttpStatus.OK);
		 
		
	 }
	
	
}
