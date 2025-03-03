package in.nursery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart{

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer cartId;
	 
	 private Integer cid;
	 
	 private Integer plantid;
	 
	 private Integer qty;
	 
	 private String plantname;
	 
	 private Integer rate;
	 
	 public String getPlantname() {
		return plantname;
	}


	public void setPlantname(String plantname) {
		this.plantname = plantname;
	}


	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}



	public  Integer getPlantid(){
		return plantid;
	}
	 
	 

	public Cart(Integer plantid, Integer qty,Integer cid,Integer rate,String plantname){
		this.plantid = plantid;
		this.qty = qty;
		this.cid = cid;
		this.plantname  =plantname;
		this.rate = rate;
	}

	public Integer getcId() {
		return cid;
	}


	public void setcId(Integer cId) {
		this.cid = cId;
	}


	public void setPlantid(Integer plantid) {
		this.plantid = plantid;
	}


	public Integer getQty() {
		return qty;
	}


	public Cart() {
		super();
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
