package net.engineeringdigest.journalApp.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	 @Bean
	    public OpenAPI myCustomConfig() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Journal App APIs")
	                        .description("By Suraj")
	                        .version("1.0")) // version is recommended
	                .servers(Arrays.asList(
	                        new Server().url("http://localhost:8080").description("local"),
	                        new Server().url("http://your-live-url.com").description("live")
	                ))
	                .tags(Arrays.asList(
	                		new Tag().name("Public APIs"),
	                		new Tag().name("User APIs"),
	                		new Tag().name("Journal APIs"),
	                		new Tag().name("Admin APIs")));
	    }
}
