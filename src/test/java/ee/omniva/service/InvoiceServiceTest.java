package ee.omniva.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ee.omniva.domain.Invoice;
import ee.omniva.repository.InvoiceRepository;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {
	
	@InjectMocks
	InvoiceService service = new InvoiceServiceImpl();
	
	@Mock
	InvoiceRepository repository;
	
	@Test
	public void test() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(Math.random());
		}
	}
	
	@Test
	public void testIsInvoicePaid() {
		Invoice invoice = createInvoice("123", "name1", "desc1", true);
		Mockito.when(repository.findById("123")).thenReturn(Optional.of(invoice));
		
		boolean result = service.isInvoicePaid("123");
		
		assertTrue(result);
	}
	
	@Test
	public void testGetAllInvoices() {
		List<Invoice> invoices = new ArrayList<>();
		invoices.add(createInvoice("123", "name1", "desc1", false));
		invoices.add(createInvoice("456", "name2", "desc2", true));
		Mockito.when(repository.findAll()).thenReturn(invoices);
		
		List<Invoice> result = service.getAllInvoices();
		
		assertEquals(2, result.size());
	}
	
	private Invoice createInvoice(String id, String name, String desc, boolean paid) {
		Invoice invoice = new Invoice();
		invoice.setId(id);
		invoice.setName(name);
		invoice.setDescription(desc);
		invoice.setPaid(paid);
		
		return invoice;
	}

}
