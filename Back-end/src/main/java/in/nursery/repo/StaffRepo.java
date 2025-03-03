package in.nursery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nursery.model.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Integer>{

	// Retrieve record by email and password
	Staff findByEmailAndPassword(String email, String password);

	// Retrieve record by email
	Staff findByEmail(String email);

	// Retrieve record by password
    public Staff findByPassword(String password);
	
	

}
