package com.bd2.TransportManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "dbservice")
public class TransportManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportManagerApplication.class, args);
	}

}
