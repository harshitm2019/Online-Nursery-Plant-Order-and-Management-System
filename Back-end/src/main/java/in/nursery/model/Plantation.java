package in.nursery.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Plantation Entity Class


@Entity
@Table(name = "Plantation")
public class Plantation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Plantation_id")
	private Integer plantationId;
	
	private String plantOrderNo;
	
	private Integer staffId;
		
	@Column(name = "Plant_date")
	private Date plantDate;
	
	@Column(name = "Amc_start")
	private Date amcStart;
	
	@Column(name = "Amc_end")
	private Date amcEnd;
	
	@Column(name = "Other_cost")
	private Integer otherCost;
	
	
	public Integer getPlantationId() {
		return plantationId;
	}
	

	public void setPlantationId(Integer plantationId) {
		this.plantationId = plantationId;
	}
	
	public String getPlantOrderNo() {
		return plantOrderNo;
	}


	public void setPlantOrderNo(String plantOrderNo) {
		this.plantOrderNo = plantOrderNo;
	}


	public Integer getStaffId() {
		return staffId;
	}


	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}


	public Date getPlantDate() {
		return plantDate;
	}

	public void setPlantDate(Date plantDate) {
		this.plantDate = plantDate;
	}

	public Date getAmcStart() {
		return amcStart;
	}

	public void setAmcStart(Date amcStart) {
		this.amcStart = amcStart;
	}

	public Date getAmcEnd() {
		return amcEnd;
	}

	public void setAmcEnd(Date amcEnd) {
		this.amcEnd = amcEnd;
	}

	public Integer getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Integer otherCost) {
		this.otherCost = otherCost;
	}
	
	
	

}
