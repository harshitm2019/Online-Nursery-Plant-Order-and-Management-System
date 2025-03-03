package in.nursery.service;

import java.util.HashMap;
import java.util.List;

import in.nursery.model.Payment;

public interface PaymentService {

	boolean savePayment(Payment payment);

	List<Payment> getTotalSales();

	List<Payment> searchByDate(HashMap<String, Object> map);

}
