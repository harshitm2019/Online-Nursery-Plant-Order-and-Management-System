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
import in.nursery.jwt.JwtService;
import in.nursery.model.Cart;
import in.nursery.repo.CustomerRepo;
import in.nursery.service.CartService;
import in.nursery.utils.GetIdFormToken;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class CartController{

	// Dependency Injection     
	@Autowired
	private CartService cartService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	GetIdFormToken formToken = new GetIdFormToken();
	
	//API Implementation for adding item into the cart
	@PostMapping("/addToCart")
	public ResponseEntity<String> addToCart(@RequestHeader("Authorization") String token,@RequestBody Cart cartDetails) {
		
		try {
			
			if(jwtService.isTokenExpired(token.substring(7)))
	    		return new ResponseEntity<>("{\"message\": \"Jwt Token Expired\"}",HttpStatus.UNAUTHORIZED);
			
			Integer id =  formToken.getId(token, jwtService, customerRepo).get().getcId();
			if(cartService.addToCart(cartDetails,id))
			
			return new ResponseEntity<String>("{\"message\": \"Added Successfully\"}",HttpStatus.OK);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("{\"message\": \"Not Added\"}",HttpStatus.BAD_REQUEST);
		
	}
	//API Implementation for get cart details
	@GetMapping("/getCartDetails")
	public ResponseEntity<?> getCartDetails(@RequestHeader("Authorization") String token){
		
		try {
			
			if(jwtService.isTokenExpired(token.substring(7)))
	    		return new ResponseEntity<>("{\"message\": \"Jwt Token Expired\"}",HttpStatus.UNAUTHORIZED);
			
			Integer id = formToken.getId(token, jwtService, customerRepo).get().getcId();
			
			
			List<Cart> list = cartService.getCartDetails(id);
			
			return new ResponseEntity<List<Cart>>(list,HttpStatus.OK);
			
		} catch (Exception e) {
			
			 e.printStackTrace();
			
		}
		
		return new ResponseEntity<>("{\"message\": \"An error occured\"}",HttpStatus.BAD_REQUEST);
		 
	}	
	
	//API Implementation for updating item in cart
	@PostMapping("/updateQty")
	public ResponseEntity<String> updateQty(@RequestHeader("Authorization") String token,@RequestBody Integer[] updatedQty){
		
		try {
			
			if(jwtService.isTokenExpired(token.substring(7)))
	    		return new ResponseEntity<>("{\"message\": \"Jwt Token Expired\"}",HttpStatus.UNAUTHORIZED);
			
			Integer id = formToken.getId(token, jwtService, customerRepo).get().getcId(); 
			
			if(cartService.updateQty(id,updatedQty[0],updatedQty[1]))
		     return  new ResponseEntity<String>("{\"message\": \"Updated\"}",HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		 return  new ResponseEntity<>("{\"message\": \"Not Updated\"}",HttpStatus.BAD_REQUEST);
		
		
	}
	
	//API Implementation for deleting item from the cart
	@PostMapping("/deleteItem")
	public ResponseEntity<String> deleteItem(@RequestHeader("Authorization") String token,@RequestBody Integer plantid){
		
		try {
			
			if(jwtService.isTokenExpired(token.substring(7)))
	    		return new ResponseEntity<>("{\"message\": \"Jwt Token Expired\"}",HttpStatus.UNAUTHORIZED);
			
			cartService.deleteItem(plantid);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
		return new ResponseEntity<String>("{\"message\": \"Item Deleted\"}",HttpStatus.OK);
	}
	
}
