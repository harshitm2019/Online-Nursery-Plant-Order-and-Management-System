package in.nursery.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.nursery.model.PlantMaster;
import in.nursery.repo.PlantMasterRepo;
import in.nursery.service.PlantMasterService;
import in.nursery.utils.SearchCriteria;

@Service
public class PlantMasterServiceImpl implements PlantMasterService{

	@Autowired
	private PlantMasterRepo plantMasterRepo;
	

	@SuppressWarnings("deprecation")
	@Override
	public boolean add(PlantMaster plantMaster) {
		
		 PlantMaster plantMaster1 = null;
		
         if(plantMasterRepo.existsById(plantMaster.getPlantId())) {
        	 
        	 plantMaster1 = plantMasterRepo.findbyId(plantMaster.getPlantId());
        	 plantMaster1.setBenefits(plantMaster.getBenefits());
        	 plantMaster1.setImgAddr(plantMaster.getImgAddr());
        	 plantMaster1.setPlantName(plantMaster.getPlantName());
        	 plantMaster1.setPlantType(plantMaster.getPlantType());
        	 plantMaster1.setRate(plantMaster.getRate());
        	 plantMaster1.setSeason(plantMaster.getSeason());
        	 plantMasterRepo.save(plantMaster1);
        	 return true;
     
         }
        	 
		 plantMaster1 = plantMasterRepo.save(plantMaster);
		 
		 if(plantMaster1 != null) return true;
		 
		 return false;
		
	}

	@Override
	public List<PlantMaster> searchBasedOnCriteria(SearchCriteria searchCriteria) {
		
		
		PlantMaster plantMaster = new PlantMaster();
		 
		if("all".equals(searchCriteria.getSeason()))
		 return plantMasterRepo.findAll();
		
		if(!"".equals(searchCriteria.getPlantName()))
		plantMaster.setPlantName(searchCriteria.getPlantName());
		
		
		if(!"".equals(searchCriteria.getSeason()))
		plantMaster.setSeason(searchCriteria.getSeason());	
		
		Example<PlantMaster> of = Example.of(plantMaster);
		
		List<PlantMaster> list = plantMasterRepo.findAll(of);
				
		plantMaster = null;
		
		return list;
		
	}

	@Override
	public List<Integer> getPlantId() {

		return  plantMasterRepo.getIds(); 
		
	}
}
