package com.example.discounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;

@Configuration
public class TestDiscountsApplication {

	@Bean
	@ServiceConnection
	MongoDBContainer mongoDBContainer() {
		return new MongoDBContainer("mongo:4.4.4");
	}

	public static void main(String[] args) {
		SpringApplication
				.from(DiscountsApplication::main)
				.with(TestDiscountsApplication.class);
	}

}
