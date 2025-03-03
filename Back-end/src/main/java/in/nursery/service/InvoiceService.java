package in.nursery.service;

import java.util.List;

import in.nursery.model.OrderDescription;
import in.nursery.model.PlantOrder;

public interface InvoiceService {

	byte[] getInvoice(PlantOrder order, List<OrderDescription> orderDescription);
	
}
