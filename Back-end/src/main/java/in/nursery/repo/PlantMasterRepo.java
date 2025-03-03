package in.nursery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.PlantMaster;

@Repository
public interface PlantMasterRepo extends JpaRepository<PlantMaster, Integer>{

	@Query("SELECT p.id FROM PlantMaster p")
	List<Integer> getIds();

	@Query("SELECT p FROM PlantMaster p WHERE p.plantId = :plantId")
	PlantMaster findbyId(Integer plantId);



	
	
	
}
