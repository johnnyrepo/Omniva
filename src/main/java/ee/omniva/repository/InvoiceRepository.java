package ee.omniva.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ee.omniva.domain.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {

}
