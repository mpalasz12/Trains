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
		City city3 = new City("Gdansk");
		database.addCity(city3);
		database.addCity(city);
		database.addCity(city2);

		city = database.getCityByName("Warszawa");
		city2 = database.getCityByName("Krakow");
		city3 = database.getCityByName("Gdansk");


		Station kcent_station = new Station("Krakow Centralny", city2.getCity_id());
		Station wwest_station = new Station("Warszawa Zachodnia", city.getCity_id());
		Station wcent_station = new Station("Warszawa Centralna", city.getCity_id());
		Station gdn_wrzeszcz_station = new Station("Gdansk Wrzeszcz", city3.getCity_id());

		database.addStation(kcent_station);
		database.addStation(wwest_station);
		database.addStation(wcent_station);
		database.addStation(gdn_wrzeszcz_station);



		Linestop ls1 = new Linestop( 4);
		Linestop ls2 = new Linestop( 1, 200,  3);


		ls1.setID(database.addLinestop(ls1));
		ls2.setID(database.addLinestop(ls2));

		Line line = new Line("Gdansk-Krakow", ls2.getID());
		database.addLine(line);

		Locomotive loco = new Locomotive("Test", "Poland");
		loco.setLocomotive_id(database.addLocomotive(loco));

		Train train = new Train(1, loco.getLocomotive_id());
		train.setTrain_id(database.addTrain(train));

		Wagon wagon = new Wagon(1, 100, train.getTrain_id());
		wagon.setWagon_id(database.addWagon(wagon));
	}
}
