package in.nursery.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.nursery.model.AMC;
import in.nursery.repo.AmcRepo;
import in.nursery.service.AmcService;

@Service
public class AmcServiceImpl implements AmcService {

	@Autowired
	private AmcRepo repo;
	
	@Override
	public boolean addAmcPlan(AMC amc){
		
		Optional<AMC> amc1;
		System.out.println(amc.getAmcId());
		if(amc.getAmcId() == 0) {
		
		System.out.println("afe");	
	    if(repo.save(amc) != null) return true;   
		}
		else { 
			amc1 = repo.findById(amc.getAmcId());
		    amc1.get().setAmcName(amc.getAmcName());
		    amc1.get().setCost(amc.getCost());
		    amc1.get().setDuration(amc.getDuration());
		    amc1.get().setOtherDetails(amc.getOtherDetails());
		    repo.save(amc1.get());
		    return true;
		}
			
		return false;
		
	}

	@Override
	public List<Integer> getId() {
		
		return repo.findAllId();
	}

	@Override
	public void deletePlan(String name) {
		
		 repo.deleteByName(name);
		 
	}

	@Override
	public List<AMC> getAmcPlan() {
		
		return repo.findAll();
		
		
	}

}
