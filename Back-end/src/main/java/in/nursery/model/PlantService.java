package in.nursery.model;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Plant Service Entity Class

@Entity
@Table(name = "Service")
public class PlantService {

	   
	@Id
	@Column(name = "serv_id")
	private String  servId;
	
	@Column(name = "M_data")
	private Date maintainenanceDate;
	
	@Column(name = "Description")
	private String description; 
	
	@Column(name = "Cost")
	private Integer cost;

	private String compId;
	
	@OneToMany(mappedBy = "plantService" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<UsedItem> usedItems;
	
	public List<UsedItem> getUsedItems(){
		return usedItems;
	}

	public void setUsedItems(List<UsedItem> usedItems){
		this.usedItems = usedItems;
	}
	
	public Date getMaintainenanceDate(){
		return maintainenanceDate;
	}

	public void setMaintainenanceDate(Date maintainenanceDate) {
		this.maintainenanceDate = maintainenanceDate;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost){
		this.cost = cost;
	}

	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
