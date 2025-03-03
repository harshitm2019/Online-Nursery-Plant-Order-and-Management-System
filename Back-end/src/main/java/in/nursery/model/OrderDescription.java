package in.nursery.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// OrderDescription Entity Class


//@Table(name = "Order Description")
@Entity
public class OrderDescription {
 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer odId;
	 
     @Column(name = "PQty")
	 private Integer qty;
     
     @Column(name = "Amt")
	 private Integer totalAmt;
     
     private String plantOrderNo;
     
     private Integer plantId;
     
     private String plantName;
	 
     public String getPlantName() {
		return plantName;
	}


	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}


	public String getPlantOrderNo() {
		return plantOrderNo;
	}


	public void setPlantOrderNo(String plantOrderNo) {
		this.plantOrderNo = plantOrderNo;
	}


	
	
     public Integer getOdId() {
		return odId;
	}


	public void setOdId(Integer odId) {
		this.odId = odId;
	}


	public Integer getPlantId() {
		return plantId;
	}


	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}


	public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}


	public Integer getTotalAmt() {
		return totalAmt;
	}


	public void setTotalAmt(Integer totalAmt) {
		this.totalAmt = totalAmt;
	}


	
	 
	
	
}
