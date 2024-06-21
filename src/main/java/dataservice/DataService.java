package dataservice;

import dbservice.*;
import dbservice.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/data")  // sciezka bazowa dla wszystkich endpointow (localhost:8000/data)
public class DataService {
	
	private ApplicationContext context;
	private DatabaseController database;

	@Autowired
	public DataService(ApplicationContext context) {
		this.context = context;
		this.database = context.getBean(DatabaseController.class);
	}

	// APPLICATION TEST - GET CITY ENDPOINT
	@GetMapping("/city")  // GET request, dajesz jaka chcesz sciezke do endpointu, np. localhost:8000/data/city
	public String getCity(@RequestParam(name = "id") String id) {  // RequestParam to beda parametry przekazywane w URL, np. localhost:8000/data/city?id=1. mozesz ich dac kilka
		// i tutaj kod, ktory bedzie zwracal odpowiedz na zapytanie
		// mozesz tez zwrocic np caly obiekt City, i wtedy on sie ladnie na jsona zamieni
		Long idLong = Long.parseLong(id);
		return database.getCity(idLong);  // w klasie DatabaseController sa dokladne metody ktore zwracaja rzeczy
										 // jak chcesz dodac metode, to kazde DAO ma konkretne polecenia w SQL
										// - dzieki B) pozdrawiam
	}

	@GetMapping("/city_by_name")
	public City getCityByName(@RequestParam(name = "name") String name) {
		return database.getCityByName(name);
	}


	@GetMapping("/station_by_id")
	public Station getStation(@RequestParam(name = "id") String id) {
		Long idLong = Long.parseLong(id);
		return database.getStation(idLong);
	}

	@GetMapping("/all_stations")
	public List<Station> getAllStations() {
		return database.getAllStations();
	}

	@GetMapping("/stations_by_city_id")
	public List<Station> getStationsByCityId(@RequestParam(name = "city_id") String city_id) {
		Long city_idLong = Long.parseLong(city_id);
		return database.getStationsByCityId(city_idLong);
	}
}
