package ee.omniva.repository.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

//@Configuration
//@EnableMongoRepositories("ee.omniva.repository")
public class MongoDatabaseConfiguration extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private Integer port;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.username}")
	private String username;

	@Value("${spring.data.mongodb.password}")
	private String password;

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	public String getDatabaseName() {
		return database;
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(new ServerAddress(host, port),
				MongoCredential.createCredential(username, database, password.toCharArray()), 
				new MongoClientOptions.Builder().build());
	}

}