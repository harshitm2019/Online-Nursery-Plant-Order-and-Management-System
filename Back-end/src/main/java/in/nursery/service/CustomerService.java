package in.nursery.service;

import java.util.Optional;

import in.nursery.model.Customer;

// Customer Service interface
public interface CustomerService {

	public boolean  addCustomer(Customer customer);

	public Optional<Customer> findCustomer(Integer id);

	public boolean checkCredentials(Customer customer);

	boolean changePassword(String oldPassword, String newPassword, String auString);

}
