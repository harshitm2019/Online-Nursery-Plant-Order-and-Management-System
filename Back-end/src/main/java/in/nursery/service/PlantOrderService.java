package in.nursery.service;

import java.util.List;
import in.nursery.model.PlantOrder;
import in.nursery.model.Plantation;

public interface PlantOrderService {

	 boolean savOrder(PlantOrder plantOrder);

	 List<PlantOrder> getOrders(Integer id);

	 List<PlantOrder> getPendingOrders();

	 void changeStatus(List<Plantation> plantations);

	

}
