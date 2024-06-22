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
@ComponentScan(basePackages = { "dbservice", "dataservice" })
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

		Station kcent_station = new Station("Krakow Centralny", city2.getCity_id());
		Station wwest_station = new Station("Warszawa Zachodnia", city.getCity_id());
		Station wcent_station = new Station("Warszawa Centralna", city.getCity_id());

		database.addStation(kcent_station);
		database.addStation(wwest_station);
		database.addStation(wcent_station);

		Locomotive loco = new Locomotive("Test", "Poland");
		database.addLocomotive(loco);

		Linestop ls1 = new Linestop((long) 1);
		Linestop ls2 = new Linestop((long) 3, 200, (long) 3);

		ls1.setID(database.addLinestop(ls1));
		ls2.setID(database.addLinestop(ls2));

		Line line = new Line("Warszawa-Krakow", ls1.getID());
		database.addLine(line);

	}
}
