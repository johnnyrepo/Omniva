package ee.omniva.rest.controller;

import java.util.List;

import ee.omniva.domain.Invoice;

public interface InvoiceRestController {
	
	boolean isInvoicePaid(String id);
	
	Invoice getInvoiceById(String id);

	List<Invoice> getAllInvoices();
	
	Integer generateInvoices(Integer amount);
	
}
