package ee.omniva.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ee.omniva.domain.Invoice;

@Repository
public class InvoiceMongoRepositoryImpl implements InvoiceMongoRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<Invoice> getAll() {
		return mongoOperations.findAll(Invoice.class);
	}

	@Override
	public Invoice getById(String id) {
		Query query = new Query(Criteria.where("id").is(id));

		return mongoOperations.findOne(query, Invoice.class);
	}

	@Override
	public void insertAll(List<Invoice> invoices) {
		mongoOperations.insertAll(invoices);
	}

}
