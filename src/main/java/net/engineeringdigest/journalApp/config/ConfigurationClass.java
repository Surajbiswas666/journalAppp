package net.engineeringdigest.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationClass {
	
	 @Bean
	    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory)
	    {
	    	return new MongoTransactionManager(dbFactory);
	    }
	    
	    @Bean
	    public RestTemplate resTemplate() {
	    	return new RestTemplate();
	    }

}
