package in.nursery.ServiceImpl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nursery.model.Plantation;
import in.nursery.repo.PlantationRepo;
import in.nursery.service.PlantationService;

@Service
public class PlantationServiceImpl implements PlantationService{

	@Autowired
	private PlantationRepo repo;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public boolean save(List<Plantation> plantation){
		
		int size = plantation.size();
		
		System.out.println(size);
		
		for(int i = 0;i < size;i++) {
			
			System.out.println(plantation.get(i).getPlantOrderNo());
			if(plantation.get(i).getPlantOrderNo().equals("")) plantation.remove(i);
			
		}
		
		System.out.println(plantation.size());
			
		if(plantation.size() >  0) {
		repo.saveAll(plantation);
		
		return true;
		}
		
		return false;
	}

	@Override
	public List<Plantation> getPlantationDetails() {
		
		return repo.findAll();
	}

	@Override
	public List<Plantation> search(HashMap<String, Object> map) {
		
		Plantation plantation = new Plantation();
		
		if(map.get("plantDate") != null)
			plantation.setPlantDate(objectMapper.convertValue(map.get("plantDate"), Date.class));
		
		System.out.println(plantation.getPlantDate());
		
		if(map.get("staffId") != null)
			plantation.setStaffId(Integer.valueOf(map.get("staffId").toString()));
		
		
		if(map.get("plantOrderNo") != null)
			plantation.setPlantOrderNo(map.get("plantOrderNo").toString());
		
		
		if(map.get("plantationId") != null)
			plantation.setPlantationId(Integer.valueOf(map.get("plantationId").toString()));
		
		
		Example<Plantation> of = Example.of(plantation);
		
		List<Plantation> plantations = repo.findAll(of);
		
		return plantations;
	}

	@Override
	public void submitUpdateDetails(List<Plantation>plantation,List<Integer> list) {
		 
		 
	     for(Integer i : list){
	    	 
	    	  Plantation p = plantation.get(i);
	    	  repo.updatePlantation(p.getPlantationId(), p.getStaffId(), p.getPlantDate(), p.getAmcStart(), p.getAmcEnd(), p.getOtherCost());
	    	 
	     }	
	}
}
