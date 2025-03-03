package in.nursery.service;

import java.util.List;

import in.nursery.model.AMC;


public interface AmcService {

	 boolean addAmcPlan(AMC amc);

	 List<Integer> getId();

	 void deletePlan(String name);

	 List<AMC> getAmcPlan();

	 
	
}
