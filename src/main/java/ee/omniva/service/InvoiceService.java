package ee.omniva.service;

import java.util.List;

import ee.omniva.domain.Invoice;

public interface InvoiceService {

	boolean isInvoicePaid(String id);

	Invoice getInvoiceById(String id);

	List<Invoice> getAllInvoices();

	int generateInvoices(int amount);

}
