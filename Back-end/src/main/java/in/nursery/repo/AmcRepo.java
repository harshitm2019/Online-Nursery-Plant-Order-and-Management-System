package in.nursery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import in.nursery.model.AMC;
import jakarta.transaction.Transactional;


@Repository
public interface AmcRepo extends JpaRepository<AMC, Integer>{
    
	// custom Query for selecting all amc id in database
	
	@Query("SELECT a.amcId FROM AMC a")
	public List<Integer> findAllId();

	@Transactional
	@Modifying
	@Query("DELETE FROM AMC a where a.amcName = :name")
	public void deleteByName(String name);
	
}
