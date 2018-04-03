package ee.omniva;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmnivaApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(OmnivaApplication.class, args);
	}

}
