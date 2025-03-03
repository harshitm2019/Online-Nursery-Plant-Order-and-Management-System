package in.nursery.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import in.nursery.model.OrderDescription;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUtils{
	
	    @Autowired
	    private JavaMailSender javaMailSender;
	    
	    public void sendMail(String to , String subject,String text,byte [] attachment) throws MessagingException{
	    	
	    	
	    	MimeMessage message = javaMailSender.createMimeMessage();
	    	MimeMessageHelper messageHelper  = new MimeMessageHelper(message,true,StandardCharsets.UTF_8.name());
	    	
	    	
	    	messageHelper.setTo(to);
	    	messageHelper.setSubject(subject);
	    	messageHelper.setText(text,true);
	    	
	    	messageHelper.addAttachment("Invoice", new ByteArrayResource(attachment),"application/pdf'");
	    	
	    	javaMailSender.send(message);
	    	
	    }
	    
	    public String setText(List<OrderDescription> orderDescriptions) {
	    	
	    	StringBuilder sb = new StringBuilder();
	    	
	    	sb.append("<html>"
	    			+ "<body>"
	    			+ "<h2>Order Description</h2>"
	    			+ "<table border=1>"
	    			+ "<tr>"
	    			+ "<th>Plant order no</th>"
	    			+ "<th>Plant id</th>"
	    			+ "<th>Qty</th>"
	    			+ "<th>Amt</th>"
	    			+ "</tr>");
	    	
	    	
	    		
	    		for(OrderDescription o : orderDescriptions){
	    			
	    			sb.append("<tr><td>"
	    					 +o.getPlantOrderNo()+"</td>"
	    					 +"<td>"
	    					 +o.getPlantId()+"</td>"
	    					 +"<td>"
	    					 +o.getQty()+"</td>"
	    					 +"<td>"
	    					 +o.getTotalAmt()+"</td></tr>");
	    			
	    		}
	    		
	    		return sb.toString();
	    		
	    	}
	    }
	    

