package in.nursery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.OrderDescription;

@Repository
public interface OrderDescriptionRepo  extends JpaRepository<OrderDescription, Integer>{

	
	@Query("SELECT o FROM OrderDescription o WHERE o.plantOrderNo = :plantOrderNo")
	List<OrderDescription> findAllByPlantOrderNo(String plantOrderNo);

	
	 
}
