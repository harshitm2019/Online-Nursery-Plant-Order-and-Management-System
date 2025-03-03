package in.nursery.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.nursery.model.Complaint;
import in.nursery.model.PlantService;
import in.nursery.repo.ComplaintRepo;
import in.nursery.repo.PlantationRepo;
import in.nursery.service.ComplaintService;
import in.nursery.utils.GenerateRandomId;


@Service
public class ComplaintServiceImpl implements ComplaintService{

	@Autowired
	private ComplaintRepo complaintRepo;
	
	@Autowired
	private PlantationRepo plantationRepo;
	
	private GenerateRandomId generateRandomId = new GenerateRandomId();
	
	@Override
	public boolean submitComplaint(Complaint complaint){
		
		complaint.setCompId(generateRandomId.randomComplaintId());
		complaint.setStatus("In Progress");
		
		if(plantationRepo.findById(complaint.getPlantationId()) != null && complaintRepo.save(complaint) != null) 
			return true;	
		
		    return false;
	}

	@Override
	public List<Complaint> getComplaintDetails(Integer id) {
		
		return complaintRepo.findByCid(id);
	}

	@Override
	public List<Complaint> getPendingComplaint() {
		
		return complaintRepo.findByStatus();
	}

	@Override
	public void changeStatus(List<PlantService> plantServices){
		
		complaintRepo.changeStatus(plantServices);
		
	}

	@Override
	public List<Complaint> getCompletedComplaint() {
		
	 return complaintRepo.findByCompletedStatus();
	 
   }
}
