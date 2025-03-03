package in.nursery.service;

import java.util.List;

import in.nursery.model.Complaint;
import in.nursery.model.PlantService;

public interface ComplaintService {

	boolean submitComplaint(Complaint complaint);

	List<Complaint> getComplaintDetails(Integer id);

	List<Complaint> getPendingComplaint();

	void changeStatus(List<PlantService> plantServices);

	List<Complaint> getCompletedComplaint();

}
