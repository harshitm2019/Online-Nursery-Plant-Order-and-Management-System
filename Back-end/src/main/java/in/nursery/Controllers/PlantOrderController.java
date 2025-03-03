package in.nursery.Controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nursery.jwt.JwtService;
import in.nursery.model.OrderDescription;
import in.nursery.model.Payment;
import in.nursery.model.PlantOrder;
import in.nursery.service.CartService;
import in.nursery.service.InvoiceService;
import in.nursery.service.OrderDescriptionService;
import in.nursery.service.PaymentService;
import in.nursery.service.PlantOrderService;
import in.nursery.utils.EmailUtils;
import in.nursery.utils.GenerateRandomId;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PlantOrderController {
	
	   @Autowired
	   private JwtService jwtService;
	   
	   @Autowired 
	   private PaymentService paymentService;
	   
	   @Autowired 
	   private CartService cartService;
	   
	   @Autowired
	   private PlantOrderService pos;
	   
	   @Autowired 
	   private OrderDescriptionService orderDescriptionService;
	   
	   @Autowired
	   private InvoiceService invoiceService;
	   
	   GenerateRandomId randomId = new GenerateRandomId();
	   
	   @Autowired
	   private EmailUtils email;
	   
	   private final ObjectMapper objectMapper = new ObjectMapper();

	   @PostMapping("/plantOrder")
	   public ResponseEntity<?> plantOrder
	   (@RequestHeader("Authorization") String authorizationHeader,@RequestBody HashMap<String, Object> objects){
		   
		   byte[] by = null;
		   
		   try {
			   
			    String token = authorizationHeader.substring(7);
			    
			    
			    Integer id = jwtService.getCustomerIdFromToken(token);
			    PlantOrder order = objectMapper.convertValue(objects.get("plantOrder"), PlantOrder.class);
			    Payment payment = objectMapper.convertValue(objects.get("payment"), Payment.class);
			    
			       
			    order.setCustId(id);	
			    order.setPlantOrderId(randomId.randomId().substring(0, 8));
			    payment.setPayStatus("Done");
			    payment.setPlantOrderNo(order.getPlantOrderId());
			    
			    List<OrderDescription> orderDescription = orderDescriptionService.save(id,order.getPlantOrderId());
			    
			    by  = invoiceService.getInvoice(order,orderDescription);
			    
			    
			    if(pos.savOrder(order) && paymentService.savePayment(payment) 
			    		&& orderDescription != null)
			    	
			    {
			    	String text = email.setText(orderDescriptionService.getDescription(order.getPlantOrderId()));
					     
						    email.sendMail(objects.get("email").toString(), "OrderDescription", text,by);
			    }
			    	  
			    HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("filename", "Invoice.pdf");
		        headers.setContentLength(by.length);
			    
			    cartService.deleteAll(id);
			    return new ResponseEntity<>(by,HttpStatus.OK);  
		   }
		   catch(Exception e) {
			
			   e.printStackTrace();
		   }
		   
		   return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		   
	   }
	   
	   @GetMapping("/getOrders")
	   public ResponseEntity<?> getOrders(@RequestHeader("Authorization") String authorizationHeader){
		   
		   Integer id = 0;
		   
		   try {
			   
			   if(jwtService.isTokenExpired(authorizationHeader.substring(7)))
				   return new ResponseEntity<>("{\"message\": \"Jwt token Expired\"}",HttpStatus.UNAUTHORIZED);
			   
			 id  = jwtService.getCustomerIdFromToken(authorizationHeader.substring(7));
			   
		   }
		   catch (Exception e) {
			
			   e.printStackTrace();
            }
		  
		    return new ResponseEntity<>(pos.getOrders(id),HttpStatus.OK);
		   
	   }
	   
	   
}
