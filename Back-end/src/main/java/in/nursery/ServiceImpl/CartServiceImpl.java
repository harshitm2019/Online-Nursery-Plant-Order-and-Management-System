package in.nursery.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.nursery.model.Cart;
import in.nursery.repo.CartRepo;
import in.nursery.service.CartService;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepo cartRepo;
	
	//Method implementation for adding item into the cart 
	@Override
	public boolean addToCart(Cart cart,Integer id) {
		
		
		Cart cart2 = cartRepo.findQty(id, cart.getPlantid());
		
		if(cart2 != null) {
			
			 cart2.setQty(cart.getQty() + cart2.getQty());
			 cartRepo.save(cart2);
			 return true;
		}
		
		cart.setcId(id);
		
		if(cartRepo.save(cart) != null)
		return true;
		
		return false;
	}
	
	// Method implementation for getting cart details

	@Override
	public List<Cart> getCartDetails(Integer id){
		   
		   List<Cart> list =  cartRepo.findByCid(id);
		   return list;	  
	}

	// Method implementation for updating quantity
	@Override
	public boolean updateQty(Integer cid,Integer plantid,Integer updatedQty){
		
		 
		  Cart cart = cartRepo.findQty(cid, plantid);
		  
		  if(cart != null){
			   
			   cart.setQty(updatedQty);
			   cartRepo.save(cart);
			    return true;  
			
		  }
		
		return false;
	}

	// Method implementation for deleting item
	@Override
	public void deleteItem(Integer plantid) {
		
		
		 cartRepo.deleteByPlantid(plantid);
		
	}

	@Override
	public void deleteAll(Integer id) {

         cartRepo.delete(id);
		
	}		
	

}
