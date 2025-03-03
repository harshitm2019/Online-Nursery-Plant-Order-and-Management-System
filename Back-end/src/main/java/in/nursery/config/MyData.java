package in.nursery.config;


public class MyData {

	    private String token;
	    
	    private Integer staffId;

		public Integer getStaffId() {
			return staffId;
		}

		public void setStaffId(Integer staffId) {
			this.staffId = staffId;
		}

		public MyData(String token,Integer id) {
	
			staffId = id; 
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	    
	
}
