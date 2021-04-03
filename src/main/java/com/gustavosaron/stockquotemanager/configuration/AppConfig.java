package com.gustavosaron.stockquotemanager.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
class LoadDatabase {

	@SuppressWarnings("unused")
	@Bean
	public CommandLineRunner registerToStockManager() {
		final RestTemplate rt = new RestTemplate();
		final String url = "http://localhost:8080/notification";
		//final String url = "http://stock-manager:8080/notification";
		
		return args -> {
			String body = "{\"host\": \"localhost\", \"port\": \"8081\"}";
			
			HttpHeaders headers = new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);   
		    
		    HttpEntity<String> request = new HttpEntity<String>(body, headers);
		    

			ResponseEntity<String> result = rt.postForEntity(url, request, String.class);
		};
	}
}