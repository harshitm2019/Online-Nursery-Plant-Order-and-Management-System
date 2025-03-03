package in.nursery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AMC {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer amcId;
	
	    private String amcName;
	    
	    private Integer cost;
	    
	    private String otherDetails;
	    
	    private Integer duration;
	    

		public Integer getDuration() {
			return duration;
		}

		public void setDuration(Integer duration) {
			this.duration = duration;
		}

		public Integer getAmcId() {
			return amcId;
		}

		public void setAmcId(Integer amcId) {
			this.amcId = amcId;
		}

		public String getAmcName() {
			return amcName;
		}

		public void setAmcName(String amcName) {
			this.amcName = amcName;
		}

		public Integer getCost() {
			return cost;
		}

		public void setCost(Integer cost) {
			this.cost = cost;
		}

		public String getOtherDetails() {
			return otherDetails;
		}

		public void setOtherDetails(String otherDetails) {
			this.otherDetails = otherDetails;
		}
}
