package in.nursery.service;

import java.util.List;

import in.nursery.model.PlantMaster;
import in.nursery.utils.SearchCriteria;

public interface PlantMasterService {

	
	public boolean add(PlantMaster plantMaster);

	public List<PlantMaster> searchBasedOnCriteria(SearchCriteria searchCriteria);

	public List<Integer> getPlantId();

	
}
