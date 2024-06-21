package com.bd2.TransportManager;

import dbservice.DatabaseController;
import dbservice.models.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ConfigurableApplicationContext;

// packages used: dbservice, dataservice

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"dbservice", "dataservice"})
public class TransportManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TransportManagerApplication.class, args);
		DatabaseController database = context.getBean(DatabaseController.class);

		initDatabase(database);

	}

	public static void initDatabase(DatabaseController database) {
		City city = new City("Warszawa");
		database.addCity(city);
		city = database.getCityByName("Warszawa");

		Station wcent_station = new Station("Warszawa Centralna", city.getCity_id());
		Station wwest_station = new Station("Warszawa Zachodnia", city.getCity_id());
//
		database.addStation(wwest_station);
		database.addStation(wcent_station);
	}
}
