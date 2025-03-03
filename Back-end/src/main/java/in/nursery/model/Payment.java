package in.nursery.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Payment")
public class Payment {

	@Id
	@Column(name = "pay_id")
	private String payId;
	
	@Column(name = "Pay_mode")
	private String payMode;
	
	@Column(name = "Pamount")
	private Integer payAmt;
	
	private Date payDate;
	
	private String PlantOrderNo;
	
	public String getPlantOrderNo() {
		return PlantOrderNo;
	}

	public void setPlantOrderNo(String plantOrderNo) {
		PlantOrderNo = plantOrderNo;
	}

	@Column(name = "Pay_status")
	private String payStatus;
	
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Integer getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Integer payAmt) {
		this.payAmt = payAmt;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus){
		
		this.payStatus = payStatus;
		
	}
		
	
}
