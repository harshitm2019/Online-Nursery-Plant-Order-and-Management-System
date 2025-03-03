package in.nursery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Staff Entity Class

@Entity
@Table(name = "Staff")
public class Staff{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Staff_id")
	private Integer staffId;
	
	@Column(name = "Sname")
	private String sname;
	
	@Column(name = "Address")
	private String sAddress;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Contact")
	private String contactNo;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Job")
	private String job;


	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
}
