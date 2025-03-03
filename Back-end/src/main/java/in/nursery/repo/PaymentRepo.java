package in.nursery.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.nursery.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, String>{

	
	@Query("SELECT p FROM Payment p WHERE p.payDate BETWEEN :localDate AND :localDate2")
	List<Payment> searchByDate(LocalDate localDate, LocalDate localDate2);

	
	
}
