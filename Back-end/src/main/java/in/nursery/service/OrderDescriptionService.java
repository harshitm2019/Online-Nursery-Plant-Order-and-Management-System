package in.nursery.service;

import java.util.List;

import in.nursery.model.OrderDescription;

public interface OrderDescriptionService {

	  public List<OrderDescription> save(Integer id,String plantid);

	public List<OrderDescription> getDescription(String plantOrderId);

	
	 
}
