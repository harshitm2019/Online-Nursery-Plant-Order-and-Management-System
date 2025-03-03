package in.nursery.service;

import java.util.HashMap;
import java.util.List;

import in.nursery.model.Plantation;

public interface PlantationService{

	boolean save(List<Plantation> plantation);

	List<Plantation> getPlantationDetails();

	List<Plantation> search(HashMap<String, Object> map);

	void submitUpdateDetails(List<Plantation> plantation, List<Integer> list);

	
	
}
