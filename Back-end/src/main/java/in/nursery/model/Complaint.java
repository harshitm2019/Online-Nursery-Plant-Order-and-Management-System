package in.nursery.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Complaint Entity Class

@Entity
@Table(name = "Complaint")
public class Complaint {

	@Id
	@Column(name = "comp_id")
	private String  compId;
	
	@Column(name = "Dead_plant")
	private String deadPlant;
	
	@Column(name = "Plant_qty")
	private Integer plantQty;
	
	@Column(name = "Comp_date")
	private Date compDate;
	
	@Column(name = "Other_problem")
	private String otherProblem;
	
	private Integer plantationId;
	
	private String status;
	
	private Integer cId;
	

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPlantationId() {
		return plantationId;
	}

	public void setPlantationId(Integer plantationId) {
		this.plantationId = plantationId;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getDeadPlant() {
		return deadPlant;
	}

	public void setDeadPlant(String deadPlant) {
		this.deadPlant = deadPlant;
	}

	public Integer getPlantQty() {
		return plantQty;
	}

	public void setPlantQty(Integer plantQty) {
		this.plantQty = plantQty;
	}

	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}

	public String getOtherProblem(){
		return otherProblem;
	}

	public void setOtherProblem(String otherProblem){
		this.otherProblem = otherProblem;
	}
}
