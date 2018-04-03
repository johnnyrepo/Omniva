package ee.omniva.repository;

import java.util.List;

import ee.omniva.domain.Invoice;

public interface InvoiceMongoRepository {

	List<Invoice> getAll();

	Invoice getById(String id);
	
	void insertAll(List<Invoice> invoices);

}
