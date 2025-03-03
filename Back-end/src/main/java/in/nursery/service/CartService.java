package in.nursery.service;

import java.util.List;

import in.nursery.model.Cart;


public interface CartService {

	
	public List<Cart> getCartDetails(Integer id);

 	public boolean addToCart(Cart cart,Integer id);
	
 	public boolean updateQty(Integer id, Integer plantid, Integer updatedQty);

	public void deleteItem(Integer plantid);

	public void deleteAll(Integer id);
 	
}
