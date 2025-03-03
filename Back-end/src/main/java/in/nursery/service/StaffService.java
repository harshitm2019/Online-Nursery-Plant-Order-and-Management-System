package in.nursery.service;

import in.nursery.model.Staff;

public interface StaffService {

	boolean add(Staff staff);

	Staff checkCredentials(Staff staff);

}
