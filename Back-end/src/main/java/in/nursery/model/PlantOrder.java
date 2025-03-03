package in.nursery.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


// Plant Order Entity Class

@Entity
@Table(name = "Plant_Order")
public class PlantOrder {

	@Id
	@Column(name = "Plant_ord_id")
	private String plantOrderId;
	
	@Column(name = "Order_date")
	private Date orderDate;
	
	@Column(name = "Plant site")
	private String plantSite;
	
	@Column(name = "Amc_opted")
	private String amcOpted;

	@Column(name = "Status")
	private String status;
	
	private Integer custId;
	
	private Integer amcid;
	
	public Integer getAmcid() {
		return amcid;
	}

	public void setAmcid(Integer amcid) {
		this.amcid = amcid;
	}


	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getAmcId() {
		return amcid;
	}

	public void setAmcId(Integer amcId) {
		this.amcid = amcId;
	}
	


	public String getPlantOrderId() {
		return plantOrderId;
	}

	public void setPlantOrderId(String plantOrderId) {
		this.plantOrderId = plantOrderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPlantSite() {
		return plantSite;
	}

	public void setPlantSite(String plantSite) {
		this.plantSite = plantSite;
	}

	public String getAmcOpted() {
		return amcOpted;
	}

	public void setAmcOpted(String amcOpted) {
		this.amcOpted = amcOpted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
