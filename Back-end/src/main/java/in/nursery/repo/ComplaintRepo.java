package in.nursery.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.Complaint;
import in.nursery.model.PlantService;
import jakarta.transaction.Transactional;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, String>{

	@Query("SELECT c FROM Complaint c WHERE c.cId = :id")
	List<Complaint> findByCid(Integer id);

	@Query("SELECT c FROM Complaint c WHERE c.status = 'In Progress'")
	List<Complaint> findByStatus();

	@Transactional
	@Modifying
	@Query("UPDATE Complaint c SET c.status  = 'Resolved' WHERE c.compId IN (SELECT p.compId FROM PlantService p WHERE p  IN :plantServices)")
	void changeStatus(List<PlantService> plantServices);

	@Query("SELECT c FROM Complaint c WHERE c.status = 'Resolved'")
	List<Complaint> findByCompletedStatus();

	
	
}
