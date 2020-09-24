package com.spring.camel.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCamelIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCamelIntegrationApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplates()	{
		return new RestTemplate();
	}
	
}