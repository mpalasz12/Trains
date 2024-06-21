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
		City city2 = new City("Krakow");
		database.addCity(city);
		database.addCity(city2);

		city = database.getCityByName("Warszawa");
		city2 = database.getCityByName("Krakow");



		Station wcent_station = new Station("Warszawa Centralna", city.getCity_id());
		Station wwest_station = new Station("Warszawa Zachodnia", city.getCity_id());
		Station kcent_station = new Station("Krakow Centralny", city2.getCity_id());


		database.addStation(kcent_station);
		database.addStation(wwest_station);
		database.addStation(wcent_station);
	}
}
