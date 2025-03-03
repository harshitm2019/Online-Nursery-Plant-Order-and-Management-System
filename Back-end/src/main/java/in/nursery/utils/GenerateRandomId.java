	package in.nursery.utils;
	
	import java.util.UUID;
	
	public class GenerateRandomId {
	
		 public String randomId() {
		 UUID uuid = UUID.randomUUID();
		 
		  return "Order" + uuid.toString();
		 
		 }
		 
		 public String randomPaymentId() {
			 
			 UUID uuid = UUID.randomUUID();
			 
			  return "PID" + uuid.toString();
			 
		 }
		 
		 public String randomComplaintId() {
			 
			  UUID uuid = UUID.randomUUID();
			  return "CompId" + uuid.toString().substring(0,12);
			  
		 }
	
		public String randomServiceId() {
	
			UUID uuid = UUID.randomUUID();
			return "ServId" + uuid.toString().substring(0,12);
		}
	
		public String generateInvoiceNo() {
			
			UUID uuid = UUID.randomUUID();
			return "Inv" + uuid.toString().substring(0,6);
			
		}
	}
