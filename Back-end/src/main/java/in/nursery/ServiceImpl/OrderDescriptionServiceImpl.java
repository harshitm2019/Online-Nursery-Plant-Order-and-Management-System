package in.nursery.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.nursery.model.Cart;
import in.nursery.model.OrderDescription;
import in.nursery.repo.CartRepo;
import in.nursery.repo.OrderDescriptionRepo;
import in.nursery.service.OrderDescriptionService;


@Service
public class OrderDescriptionServiceImpl implements OrderDescriptionService {

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private OrderDescriptionRepo orderDescriptionRepo;
	
	@Override
	public List<OrderDescription> save(Integer id,String plantid) {
		
	    List<Cart> cart = cartRepo.findByCid(id);
	    
	    List<OrderDescription> orderDescription = new ArrayList<>();
	    
	    
	    for(Cart cart1 : cart) {
	    	
	    	OrderDescription orderDescription2 = new OrderDescription();
	    	orderDescription2.setPlantName(cart1.getPlantname());
	    	orderDescription2.setPlantOrderNo(plantid);
	    	orderDescription2.setQty(cart1.getQty());
	    	orderDescription2.setTotalAmt(cart1.getQty() * cart1.getRate());
	    	orderDescription2.setPlantId(cart1.getPlantid());
	    	
	 
	    	orderDescription.add(orderDescription2);
	    }
	    
	    if(orderDescriptionRepo.saveAll(orderDescription) != null) {
	    	
	    	return orderDescription;
	    	
	    }
		return null;
		
	}

	@Override
	public List<OrderDescription> getDescription(String plantOrderId) {
	
	  return orderDescriptionRepo.findAllByPlantOrderNo(plantOrderId);
		
	}

	 
	
	
}
