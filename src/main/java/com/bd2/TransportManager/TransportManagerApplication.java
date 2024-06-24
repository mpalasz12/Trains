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
		addCities(database);
		initStationsLines(database);

	}

	public static void addCities(DatabaseController database) {
		City city1 = new City("Gdansk");
		City city2 = new City("Kolobrzeg");
		City city3 = new City("Bialystok");
		City city4 = new City("Warszawa");
		City city5 = new City("Poznan");
		City city6 = new City("Lodz");
		City city7 = new City("Wroclaw");
		City city8 = new City("Krakow");
		City city9 = new City("Rzeszow");
		database.addCity(city1);
		database.addCity(city2);
		database.addCity(city3);
		database.addCity(city4);
		database.addCity(city5);
		database.addCity(city6);
		database.addCity(city7);
		database.addCity(city8);
		database.addCity(city9);
	}

	public static void initStationsLines(DatabaseController database) {
		City city1 = database.getCityByName("Gdansk");
		City city2 = database.getCityByName("Kolobrzeg");
		City city3 = database.getCityByName("Bialystok");
		City city4 = database.getCityByName("Warszawa");
		City city5 = database.getCityByName("Poznan");
		City city6 = database.getCityByName("Lodz");
		City city7 = database.getCityByName("Wroclaw");
		City city8 = database.getCityByName("Krakow");
		City city9 = database.getCityByName("Rzeszow");
		Station station1 = new Station("Gdansk Glowny", city1.getCity_id());
		Station station2 = new Station("Kolobrzeg Glowny", city2.getCity_id());
		Station station3 = new Station("Bialystok Glowny", city3.getCity_id());
		Station station4 = new Station("Warszawa Centralna", city4.getCity_id());
		Station station5 = new Station("Poznan Glowny", city5.getCity_id());
		Station station6 = new Station("Lodz Fabryczna", city6.getCity_id());
		Station station7 = new Station("Wroclaw Glowny", city7.getCity_id());
		Station station8 = new Station("Krakow Glowny", city8.getCity_id());
		Station station9 = new Station("Rzeszow Glowny", city9.getCity_id());
		station1.setStation_id(database.addStation(station1));
		station2.setStation_id(database.addStation(station2));
		station3.setStation_id(database.addStation(station3));
		station4.setStation_id(database.addStation(station4));
		station5.setStation_id(database.addStation(station5));
		station6.setStation_id(database.addStation(station6));
		station7.setStation_id(database.addStation(station7));
		station8.setStation_id(database.addStation(station8));
		station9.setStation_id(database.addStation(station9));

		Integer KR_id = database.createLine("Kolobrzeg-Rzeszow", station2.getStation_id());
		// kolobrzeg - gdansk - warszawa - krakow - rzeszow - krakow - warszawa - gdansk - kolobrzeg
		database.addStopToLine(KR_id, 240, station1.getStation_id());
		database.addStopToLine(KR_id, 340, station4.getStation_id());
		database.addStopToLine(KR_id, 300, station8.getStation_id());
		database.addStopToLine(KR_id, 160, station9.getStation_id());
		database.addStopToLine(KR_id, 160, station8.getStation_id());
		database.addStopToLine(KR_id, 300, station4.getStation_id());
		database.addStopToLine(KR_id, 340, station1.getStation_id());
		database.addStopToLine(KR_id, 240, station2.getStation_id());

		Integer WB_id = database.createLine("Wroclaw-Bialystok", station3.getStation_id());
		// wroclaw - poznan - lodz - warszawa - bialystok - warszawa - lodz - poznan - wroclaw
		database.addStopToLine(WB_id, 180, station5.getStation_id());
		database.addStopToLine(WB_id, 210, station6.getStation_id());
		database.addStopToLine(WB_id, 140, station4.getStation_id());
		database.addStopToLine(WB_id, 200, station3.getStation_id());
		database.addStopToLine(WB_id, 200, station4.getStation_id());
		database.addStopToLine(WB_id, 140, station6.getStation_id());
		database.addStopToLine(WB_id, 210, station5.getStation_id());
		database.addStopToLine(WB_id, 180, station7.getStation_id());

		Integer LB_id = database.createLine("Lodz-Bialystok", station5.getStation_id());
		// lodz - warszawa - bialystok - warszawa - lodz
		database.addStopToLine(LB_id, 140, station4.getStation_id());
		database.addStopToLine(LB_id, 200, station3.getStation_id());
		database.addStopToLine(LB_id, 200, station4.getStation_id());
		database.addStopToLine(LB_id, 140, station6.getStation_id());

		Integer KrR_id = database.createLine("Krakow-Rzeszow", station9.getStation_id());
		// krakow - rzeszow - krakow
		database.addStopToLine(KrR_id, 160, station9.getStation_id());
		database.addStopToLine(KrR_id, 160, station8.getStation_id());

		Locomotive loc1 = new Locomotive("IC9215", "Poland");
		Locomotive loc2 = new Locomotive("LI3122", "Germany");
		Locomotive loc3 = new Locomotive("LI3122", "Germany");
		Locomotive loc4 = new Locomotive("KK1233", "Sweden");


		Train train1 = new Train(KR_id, database.addLocomotive(loc1));
		Train train2 = new Train(WB_id, database.addLocomotive(loc2));
		Train train3 = new Train(LB_id, database.addLocomotive(loc3));
		Train train4 = new Train(KrR_id, database.addLocomotive(loc4));

		train1.setTrain_id(database.addTrain(train1));
		train2.setTrain_id(database.addTrain(train2));
		train3.setTrain_id(database.addTrain(train3));
		train4.setTrain_id(database.addTrain(train4));



	}


}
