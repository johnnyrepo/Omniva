package ee.omniva.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import ee.omniva.domain.Invoice;
import ee.omniva.domain.InvoiceIdGenerator;
import ee.omniva.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceIdGenerator idGenerator;

	private BloomFilter<String> filter;
	
	public InvoiceServiceImpl() {
		filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 500_000_000, 0.01);
	}

	@Override
	public boolean isInvoicePaid(String id) {
		boolean isPaid = false;

		if (filter.mightContain(id)) {
			isPaid = true;
		} else {
			Optional<Invoice> invoice = invoiceRepository.findById(id);
			if (invoice.isPresent()) {
				isPaid = invoice.get().isPaid();
				if (isPaid) {
					filter.put(invoice.get().getId());
				}
			}
		}

		return isPaid;
	}

	@Override
	public Invoice getInvoiceById(String id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);

		return invoice.isPresent() ? invoice.get() : null;
	}

	@Override
	public List<Invoice> getAllInvoices() {
		Iterable<Invoice> invoices = invoiceRepository.findAll();

		List<Invoice> list = new ArrayList<>();
		invoices.forEach(list::add);

		return list;
	}

	@Override
	public int generateInvoices(int amount) {
		List<Invoice> invoices = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			invoices.add(generateInvoice());
		}

		invoiceRepository.saveAll(invoices);

		return invoices.size();
	}

	private Invoice generateInvoice() {
		Invoice invoice = new Invoice();
		invoice.setId(idGenerator.generate());
		invoice.setName("Name " + invoice.getId());
		invoice.setDescription("Description " + invoice.getId());
		invoice.setPaid(Math.random() < 0.5d ? false : true);

		return invoice;
	}

}
