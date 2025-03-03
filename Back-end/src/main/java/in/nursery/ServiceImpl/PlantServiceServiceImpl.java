package in.nursery.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nursery.model.PlantService;
import in.nursery.model.UsedItem;
import in.nursery.repo.PlantServiceRepo;
import in.nursery.service.PlantServiceService;
import in.nursery.utils.GenerateRandomId;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class PlantServiceServiceImpl implements PlantServiceService{

	@Autowired
	private PlantServiceRepo repo;
	
	@Autowired
	private EntityManager entityManager;
	
	
	private GenerateRandomId generateRandomId = new GenerateRandomId();
	
	@Override
	public boolean save(List<PlantService> plantService){
		
		for(PlantService plantService2 : plantService) {
			
			plantService2.setServId(generateRandomId.randomServiceId());
			
			List<UsedItem> usedItem = plantService2.getUsedItems();
			
	        for(UsedItem  usedItem2 :usedItem) {
	        	
	               usedItem2.setPlantService(plantService2);
	        }
	        
			plantService2.setUsedItems(usedItem);
			
			repo.save(plantService2);
			
		}
			
		return true;
	}

	@Override
	public List<PlantService> getServiceDetails() {
		
	
		return repo.findAll();
		
	}

	@Override
	@Transactional
	public void submitUpdatedDetails(List<PlantService> services, List<Integer> list) {
		
		
		for(Integer i : list) {
			
			 PlantService p = services.get(i);
			 
			 List<UsedItem> list1 = p.getUsedItems();
			 
			 for(UsedItem u : list1) {
				 
				  u.setPlantService(p);
				  entityManager.merge(u); 
				  
			 }
			 
			 repo.updatePlantService(p.getMaintainenanceDate(),p.getDescription(),p.getCost(),p.getServId());
			 
		}
		
	}

	
	
}
