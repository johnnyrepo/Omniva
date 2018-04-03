package ee.omniva.domain;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class InvoiceIdGenerator {
	
	public static final int MIN_ID_LENGTH = 10;
	
	public static final int MAX_ID_LENGTH = 25;

	public String generate() {
		Random rand = new Random();
		int idLength = MIN_ID_LENGTH + rand.nextInt(MAX_ID_LENGTH - MIN_ID_LENGTH + 1);
		String id = UUID.randomUUID().toString().substring(0, idLength);
		
		return id;
	}
	
}
