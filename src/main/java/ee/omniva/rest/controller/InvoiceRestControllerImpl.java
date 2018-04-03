package ee.omniva.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ee.omniva.domain.Invoice;
import ee.omniva.service.InvoiceService;

@RestController
@RequestMapping(value = InvoiceRestControllerImpl.PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoiceRestControllerImpl implements InvoiceRestController {

	public static final String PREFIX = "/invoices";
	
	private final Logger logger = LoggerFactory.getLogger(InvoiceRestControllerImpl.class);
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "")
	public List<Invoice> getAllInvoices() {
		logger.info(PREFIX + " request recieved");
		
		return invoiceService.getAllInvoices();
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/generate/{amount}")
	public Integer generateInvoices(@PathVariable("amount") Integer amount) {
		logger.info(PREFIX + "/generate/" + amount + " request recieved");
		
		return invoiceService.generateInvoices(amount);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Invoice getInvoiceById(@PathVariable("id") String id) {
		logger.info(PREFIX + "/" + id + " request recieved");
		
		return invoiceService.getInvoiceById(id);
	}
	
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/ispaid")
	public boolean isInvoicePaid(@PathVariable("id") String id) {
		logger.info(PREFIX + "/" + id + "/ispaid" + " request recieved");
		
		return invoiceService.isInvoicePaid(id);
	}

}
