package in.nursery.ServiceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nursery.model.Payment;
import in.nursery.repo.PaymentRepo;
import in.nursery.service.PaymentService;
import in.nursery.utils.GenerateRandomId;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepo repo;
	
	@Override
	public boolean savePayment(Payment payment){
		
		payment.setPayId(new GenerateRandomId().randomPaymentId());
		if(repo.save(payment) != null) 
	    return true;
		
		return false;
	}

	@Override
	public List<Payment> getTotalSales() {
		
	  return repo.findAll();
		
	}

	@Override
	public List<Payment> searchByDate(HashMap<String, Object> map) {
		
		if(map.get("from") != null && map.get("to") != null){
			
			String from = map.get("from").toString();
			String to = map.get("to").toString();
			
			LocalDate localDate = LocalDate.parse(from);
			LocalDate localDate2 = LocalDate.parse(to);
					
			
			return  repo.searchByDate(localDate,localDate2);
			
		}
		
		return null;
		
	}
	
	

}
