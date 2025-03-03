package in.nursery.repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.Plantation;
import jakarta.transaction.Transactional;

@Repository
public interface PlantationRepo  extends JpaRepository<Plantation, Integer>{

	 @Transactional
	 @Modifying
	 @Query("UPDATE Plantation p SET p.staffId = :staffId ,p.plantDate = :plantDate,p.amcStart = :amcStart , p.amcEnd = :amcEnd,"
	 		+ "p.otherCost = :otherCost WHERE p.plantationId = :id")
	 
	 void updatePlantation(Integer id,Integer staffId,Date plantDate,Date amcStart,Date amcEnd,Integer otherCost);
	 
	 
	
	
}
