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
@RequestMapping("/data") // sciezka bazowa dla wszystkich endpointow (localhost:8000/data)
public class DataService {

	private ApplicationContext context;
	private DatabaseController database;

	@Autowired
	public DataService(ApplicationContext context) {
		this.context = context;
		this.database = context.getBean(DatabaseController.class);
	}

	// APPLICATION TEST - GET CITY ENDPOINT
	@GetMapping("/city") // GET request, dajesz jaka chcesz sciezke do endpointu, np.
							// localhost:8000/data/city
	public String getCity(@RequestParam(name = "id") String id) { // RequestParam to beda parametry przekazywane w URL,
																	// np. localhost:8000/data/city?id=1. mozesz ich dac
																	// kilka
		// i tutaj kod, ktory bedzie zwracal odpowiedz na zapytanie
		// mozesz tez zwrocic np caly obiekt City, i wtedy on sie ladnie na jsona
		// zamieni
		Long idLong = Long.parseLong(id);
		return database.getCity(idLong); // w klasie DatabaseController sa dokladne metody ktore zwracaja rzeczy
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

	@GetMapping("/tickets_by_mail")
	public List<Ticket> getTicketsByMail(@RequestParam(name = "mail") String mail) {
		return database.getTicketsByTravelerID(database.getTravelerIDbyMail(mail));
	}

	@PostMapping("/add_traveler")
	public void addTraveler(
			@RequestParam(name = "first_name") String first_name,
			@RequestParam(name = "last_name") String last_name,
			@RequestParam(name = "mail") String mail) {
		database.addTraveler(new Traveler(first_name, last_name, mail));
	}

	@PostMapping("/add_ticket")
	public void addTicket(
			@RequestParam(name = "traveler_id") String traveler_id,
			@RequestParam(name = "first_stop") String first_stop,
			@RequestParam(name = "last_stop") String last_stop,
			@RequestParam(name = "train_id") String train_id,
			@RequestParam(name = "wagon_num") String wagon_num,
			@RequestParam(name = "seat_num") String seat_num) {
		Ticket ticket = new Ticket(
				Long.parseLong(traveler_id),
				Long.parseLong(first_stop),
				Long.parseLong(last_stop),
				Long.parseLong(train_id),
				Integer.parseInt(wagon_num),
				Integer.parseInt(seat_num));
		ticket.setTicketID(database.addTicket(ticket));
	}

	@GetMapping("/get_traveler_mail")
	public Long getTravelerMail(@RequestParam(name = "mail") String mail) {
		return database.getTravelerIDbyMail(mail);
	}

	@GetMapping("/add_train")
	public void addTrain(
			@RequestParam(name = "line_id") String line_id,
			@RequestParam(name = "locomotive_id") String locomotive_id) {
		Train train = new Train(Integer.parseInt(line_id), Integer.parseInt(locomotive_id));
		database.addTrain(train);
	}

	@GetMapping("/add_locomotive")
	public void addLocomotive(
			@RequestParam(name = "model") String model,
			@RequestParam(name = "origin_country") String country) {
		Locomotive locomotive = new Locomotive(model, country);
		database.addLocomotive(locomotive);
	}

	@GetMapping("/add_wagon")
	public void addWagon(
			@RequestParam(name = "wagon_num") String wagon_num,
			@RequestParam(name = "wagon_capacity") String wagon_capacity,
			@RequestParam(name = "train_id") String train_id) {
		Wagon wagon = new Wagon(Integer.parseInt(wagon_num), Integer.parseInt(wagon_capacity),
				Long.parseLong(train_id));
		database.addWagon(wagon);
	}

	// BASIC LINE ADDING INTERFACE, REQUIRES MANUAL ID ACQUISITION AND EXISTING
	// LINESTOP
	@GetMapping("/add_line_basic")
	public void addLineBasic(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "first_stop_id") String first_stop_id) {
		Line line = new Line(name, Long.parseLong(first_stop_id));
		database.addLine(line);
	}

	// PROPER LINE ADDING INTERFACE, EASIER TO USE
	@GetMapping("/add_line")
	public void addLine(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "station_id") String station_id) {
		// create linestop terminus for new line
		Linestop linestop = new Linestop(Long.parseLong(station_id));
		// acquire ID for linestop and upload to database
		linestop.setID(database.addTerminus(linestop));
		// add line to database with terminus
		Line line = new Line(name, linestop.getID());
		database.addLine(line);
	}

	// PROPER STOP ADDING - TODO: TEST THIS!!
	@GetMapping("/add_stop")
	public void addStop(
			@RequestParam(name = "line_id") String line_id,
			@RequestParam(name = "station_id") String station_id,
			@RequestParam(name = "distance") String distance) {
		// get line
		Line line = database.getLineByID(Long.parseLong(line_id));

		// create new linestop
		Linestop linestop = new Linestop(line.getFirstStopID(), Integer.parseInt(distance),
				Long.parseLong(station_id));

		// add linestop to database, acquire ID
		linestop.setID(database.addLinestop(linestop));

		// change line first stop to new linestop
		line.setFirstStopID(linestop.getID());

		// update line in database
		database.updateLine(line);
	}

	// MANUAL LINESTOP ADDING - NOT RECOMMENDED
	@GetMapping("/add_terminus")
	public void addTerminus(
			@RequestParam(name = "station_id") String station_id) {
		Linestop linestop = new Linestop(Long.parseLong(station_id));
		database.addTerminus(linestop);
	}

	@GetMapping("/add_linestop")
	public void addLinestop(
			@RequestParam(name = "next_linestop") String next_linestop,
			@RequestParam(name = "distance") String distance,
			@RequestParam(name = "station_id") String station_id) {
		Linestop linestop = new Linestop(Long.parseLong(next_linestop), Integer.parseInt(distance),
				Long.parseLong(station_id));
		database.addLinestop(linestop);
	}

	@GetMapping("/add_station")
	public void addStation(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "city_id") String city_id) {
		Station station = new Station(name, Long.parseLong(city_id));
		database.addStation(station);
	}

	@GetMapping("/add_city")
	public void addCity(
			@RequestParam(name = "name") String name) {
		City city = new City(name);
		database.addCity(city);
	}
}
