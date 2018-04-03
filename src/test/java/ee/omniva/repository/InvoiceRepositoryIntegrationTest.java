package ee.omniva.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ee.omniva.domain.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = {OmnivaApplication.class})
public class InvoiceRepositoryIntegrationTest {
	
	@Autowired
	InvoiceRepository repository;
	
	@Test
	public void testFindById() {
		Optional<Invoice> result = repository.findById("123");
		
		assertTrue(result.isPresent());
		assertEquals("123", result.get().getId());
	}

}
