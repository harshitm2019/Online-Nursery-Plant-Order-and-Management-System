package in.nursery.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.nursery.model.PlantOrder;
import in.nursery.model.Plantation;
import in.nursery.repo.PlantOrderRepo;
import in.nursery.service.PlantOrderService;

@Service
public class PlantOrderServiceImpl implements PlantOrderService{

	 @Autowired
	 private PlantOrderRepo repo;
	 

	@Override
	public boolean savOrder(PlantOrder plantOrder) {
		
		if(repo.save(plantOrder) != null)
		return true;	
		
		return false;
	}

	@Override
	public List<PlantOrder> getOrders(Integer id){
		
		return repo.findAllByCustId(id);
		 
	}
	
	

	@Override
	public List<PlantOrder> getPendingOrders() {
		
   	  return repo.findAllPendingOrders();
		
	}

	@Override
	public void changeStatus(List<Plantation> plantations) {
		
		List<String> list = new ArrayList<>();
		
		for(Plantation p : plantations){
			  
			 list.add(p.getPlantOrderNo());
		}
		
		repo.updateOrderStatus(list,"completed");
		
	}

	
	
}
