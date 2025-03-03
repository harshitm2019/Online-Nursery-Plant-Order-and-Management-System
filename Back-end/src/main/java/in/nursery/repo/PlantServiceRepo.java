package in.nursery.repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.PlantService;
import jakarta.transaction.Transactional;

@Repository
public interface PlantServiceRepo  extends JpaRepository<PlantService, String>{

	@Transactional
	@Modifying
	@Query("UPDATE PlantService p SET p.maintainenanceDate = :md ,p.description = :d,p.cost= :c WHERE p.servId = :servId")
	void updatePlantService(Date md,String d,Integer c,String servId);
	
	

}
