package in.nursery.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nursery.model.Staff;
import in.nursery.repo.StaffRepo;
import in.nursery.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffRepo staffRepo;
	
	// Method Implementation for adding staff other than admin
	
	@Override
	public boolean add(Staff staff){		
		
		Staff checkEmail = staffRepo.findByEmail(staff.getEmail());
		if(checkEmail != null) return false;
		
		Staff staff2 = staffRepo.save(staff);
		
		if(staff2 == null) return false;
		
		return true;
	}
	
	// Method Implementation for checking admin login credentials

	@Override
	public Staff checkCredentials(Staff staff){
	
		  System.out.println(staff.getEmail());
		
		  return staffRepo.findByPassword(staff.getPassword()); 
		
	     

	}
	
}
