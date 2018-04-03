package ee.omniva.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class InvoiceIdGeneratorTest {

	InvoiceIdGenerator generator = new InvoiceIdGenerator();
	
	@Test
	public void testGenerate() {
		String id = generator.generate();
		assertId(id);
		
		id = generator.generate();
		assertId(id);
		
		id= generator.generate();
		assertId(id);
	}
	
	private void assertId(String id) {
		assertTrue(id.length() >= InvoiceIdGenerator.MIN_ID_LENGTH 
				&& id.length() <= InvoiceIdGenerator.MAX_ID_LENGTH);
	}
	
}
