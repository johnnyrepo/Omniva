package ee.omniva.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void testIsInvoicePaidTrue() {
		Invoice invoice = createInvoice("123", "name1", "desc1", true);
		Mockito.when(repository.findById("123")).thenReturn(Optional.of(invoice));

		// 1st run - positive result
		boolean result = service.isInvoicePaid("123");
		// request to repository is made on this run
		Mockito.verify(repository).findById("123");
		assertTrue(result);

		// 2nd run - positive result
		result = service.isInvoicePaid("123");
		// request to repository is NOT made on this run,
		// because filter should be in action
		Mockito.verifyZeroInteractions(repository);
		assertTrue(result);
	}

	@Test
	public void testIsInvoicePaidFalse() {
		Invoice invoice = createInvoice("456", "name2", "desc2", false);
		Mockito.when(repository.findById("456")).thenReturn(Optional.of(invoice));

		// 1st run - negative result, as there is no such id
		boolean result = service.isInvoicePaid("11111");
		Mockito.verify(repository).findById("11111");
		assertFalse(result);

		// 2nd run - negative result, as invoice is not paid
		result = service.isInvoicePaid("456");
		// request to repository is made on this run
		Mockito.verify(repository).findById("456");
		assertFalse(result);
		
		Mockito.reset(repository);

		// 3rd run - negative result, as invoice is not paid
		result = service.isInvoicePaid("456");
		// request to repository is made on this run
		Mockito.verify(repository).findById("456");
		assertFalse(result);
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
