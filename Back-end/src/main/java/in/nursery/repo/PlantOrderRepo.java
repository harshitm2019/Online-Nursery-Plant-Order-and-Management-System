package in.nursery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.PlantOrder;
import jakarta.transaction.Transactional;

@Repository
public interface PlantOrderRepo extends JpaRepository<PlantOrder, String>{

	@Query("SELECT p FROM PlantOrder p WHERE p.custId = :id ORDER BY p.plantOrderId")
	List<PlantOrder> findAllByCustId(Integer id);
	

	@Query("SELECT p FROM PlantOrder p WHERE p.status = 'pending'")
	List<PlantOrder> findAllPendingOrders();

	@Transactional
	@Modifying
	@Query("UPDATE PlantOrder p SET p.status = :status WHERE p.plantOrderId IN :list")
	void updateOrderStatus(List<String> list,String status);

	
}
