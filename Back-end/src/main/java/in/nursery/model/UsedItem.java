package in.nursery.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Used Item Entity Class

@Entity
@Table(name = "Used Item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsedItem extends AbstractPersistable<Long>{

	 @Column(name = "Iname")
	 private String itemName;
	
	 @Column(name = "Qty")
	 private Integer qty;
	 
	 @Column(name = "Rate")
	 private Integer rate;
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "serv_id")
	 private PlantService plantService;
	 
	 
	 public PlantService getPlantService(){
		 
			return plantService;
		}

	 public void setPlantService(PlantService plantService){
		 
			this.plantService = plantService;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty){
		this.qty = qty;
	}

	public Integer getRate(){
		return rate;
	}

	public void setRate(Integer rate){
		this.rate = rate;
	}
	 
	 
	 
}
