package ee.omniva.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ee.omniva.OmnivaApplication;
import ee.omniva.domain.Invoice;
import ee.omniva.repository.InvoiceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { OmnivaApplication.class })
@WebAppConfiguration
public class InvoiceRestControllerIntegrationTest {
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		invoiceRepository.save(createInvoice("12345abcde", "name1", "desc1", true));
		invoiceRepository.save(createInvoice("67890fgij", "name2", "desc2", false));
		invoiceRepository.save(createInvoice("abcde12345", "name3", "desc3", false));
		// +1 invoice in import.sql
		
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	private Invoice createInvoice(String id, String name, String desc, boolean paid) {
		Invoice invoice = new Invoice();
		invoice.setId(id);
		invoice.setName(name);
		invoice.setDescription(desc);
		invoice.setPaid(paid);
		
		return invoice;
	}
	
	@Test
	public void testIsInvoicePaidTrue() throws Exception {
		mockMvc.perform(get("/invoices/12345abcde/ispaid"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.is(true)));
	}
	
	@Test
	public void testIsInvoicePaidFalse() throws Exception {
		mockMvc.perform(get("/invoices/67890fgij/ispaid"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.is(false)));
	}
	
	@Test
	public void testGetInvoiceById() throws Exception {
		mockMvc.perform(get("/invoices/abcde12345"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.is("abcde12345")))
			.andExpect(jsonPath("$.name", Matchers.is("name3")))
			.andExpect(jsonPath("$.description", Matchers.is("desc3")))
			.andExpect(jsonPath("$.paid", Matchers.is(false)));
	}

	@Test
	public void testGetAllInvoices() throws Exception {
		mockMvc.perform(get("/invoices"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(4)));
	}

}
