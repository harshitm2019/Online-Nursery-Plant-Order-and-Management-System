package in.nursery.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import in.nursery.model.OrderDescription;
import in.nursery.model.PlantOrder;
import in.nursery.service.InvoiceService;
import in.nursery.utils.GenerateRandomId;


@Service
public class InvoiceServiceImpl implements InvoiceService{

	private GenerateRandomId randomId = new GenerateRandomId();
	
	public byte[] getInvoice(PlantOrder order, List<OrderDescription> orderDescription) {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Integer totalAmt = 0;
			
		try{
			
			
			PDDocument document = new PDDocument();
	
			PDPage page = new PDPage(PDRectangle.A4);
			
			document.addPage(page);
			
			float pageHeight = page.getMediaBox().getHeight() - 50;

			
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			
            // Title
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.5f);
			contentStream.newLineAtOffset(50, pageHeight);
			contentStream.showText("Invoice");
			contentStream.newLineAtOffset(0,-20);
			contentStream.showText("Online Nursery-Plant Order & Maintenance System");
			contentStream.endText();
			
			pageHeight -= 40;
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.5f);
			contentStream. newLineAtOffset(20, pageHeight);
			contentStream.showText("---------------------------------------------------------------------------------------------------------------------------------------------------");
			contentStream.endText();
			
			pageHeight -= 30;	
			
			// Invoice Details Such as invoice no , date , address
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.5f);
			contentStream.newLineAtOffset(50, pageHeight);
			contentStream.showText("Invoice Number " + randomId.generateInvoiceNo());
			contentStream.newLineAtOffset(0,-20);
			contentStream.showText("Date" + order.getOrderDate());
			contentStream.newLineAtOffset(0,-20);
			contentStream.showText("Address : " + order.getPlantSite());
			contentStream.endText();
			
			pageHeight -= 60;
			
			
			contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10.5f);
            contentStream.newLineAtOffset(20, pageHeight);
            contentStream.showText("---------------------------------------------------------------------------------------------------------------------------------------------------");
            contentStream.endText();
            
            pageHeight -= 50;
            
			// Order Description
			
			// Setting up the header
			
			contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
	        contentStream.newLineAtOffset(50, pageHeight);
	        contentStream.showText("Plant Name");
	        contentStream.newLineAtOffset(100, 0); // Move right 100 points
	        contentStream.showText("Qty");
	        contentStream.newLineAtOffset(100, 0); // Move right 100 points
	        contentStream.showText("Rate");
	        contentStream.newLineAtOffset(100, 0); // Move right 100 points
	        contentStream.showText("Amount");
	        contentStream.endText();
			
			// Setting up the Row 
				
			pageHeight -= 20;
			
			for(OrderDescription orderDes : orderDescription) {
				      
				 Integer x = (orderDes.getTotalAmt()/orderDes.getQty());	
				 
				 contentStream.beginText();
		            contentStream.setFont(PDType1Font.HELVETICA, 10);
		            contentStream.newLineAtOffset(50,pageHeight);
		            contentStream.showText(orderDes.getPlantName());
		            contentStream.newLineAtOffset(100, 0); // Move right 100 points
		            contentStream.showText(orderDes.getQty().toString());
		            contentStream.newLineAtOffset(100, 0); // Move right 100 points
		            contentStream.showText(x.toString());
		            contentStream.newLineAtOffset(100, 0); // Move right 100 points
		            contentStream.showText(orderDes.getTotalAmt().toString());
		            contentStream.endText();
				    
		            totalAmt += orderDes.getTotalAmt();
		            pageHeight -= 20;
			}
			
			pageHeight -= 30;
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
			contentStream.newLineAtOffset(50, pageHeight);
			contentStream.showText("Total Amt : INR " + totalAmt);
			contentStream.endText();
			
			pageHeight -= 45;
			
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			contentStream.newLineAtOffset(400, pageHeight);
			contentStream.showText("Authorized Signatory");
			contentStream.endText();
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
			contentStream.newLineAtOffset(270, pageHeight-30);
			contentStream.showText("For Online Nursery-Plant Order & Maintenance System");
			contentStream.endText();
			
			contentStream.close();
			document.save(outputStream);
			document.close();
					
		} catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		return outputStream.toByteArray();
	}

	 
	
}
