package in.nursery.service;

import java.util.List;

import in.nursery.model.PlantService;

public interface PlantServiceService {

	boolean save(List<PlantService> plantService);

	List<PlantService> getServiceDetails();

	void submitUpdatedDetails(List<PlantService> plantServices, List<Integer> list);
	
}
