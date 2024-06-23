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

// UWAGA - niektórych endpointow uzywam do tego zeby testowac co nie dziala
// potem bedzie mozna je usunac, ale poki co niech zostana
// do testowania jak co dziala - zeby nie miec tez problemow z postem
// polecam wtyczke na chrome Tabbed Postman - wtedy mozna sobie testowac
// gety i posty - zobaczyc co jest zwracane itd
// dla porownania na stan bazy danych to localhost:{port - u mnie 8080 - wyswietla sie w konsoli}
// /h2-console i jako jdbc url: jdbc:h2:mem:transportdb
	// APPLICATION TEST - GET CITY ENDPOINT
	@GetMapping("/city")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
// najlepiej 1. skopiowac komentarz "/ komentarz do usuniecia- jak juz bedzie z apka to odkomentowac"
// 2. przy pomocy ctrl f zamienic wszystkie na puste
	public String getCity(@RequestParam(name = "id") String id) {
		Integer idInteger = Integer.parseInt(id);
		try {
			return database.getCity(idInteger);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/city_by_name")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public City getCityByName(@RequestParam(name = "name") String name) {
		return database.getCityByName(name);
	}

	@GetMapping("/station_by_id")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public Station getStation(@RequestParam(name = "id") String id) {
		Integer idInteger = Integer.parseInt(id);
		try {
			return database.getStation(idInteger);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/all_stations")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public List<Station> getAllStations() {
		return database.getAllStations();
	}

	@GetMapping("/stations_by_city_id")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public List<Station> getStationsByCityId(@RequestParam(name = "city_id") String city_id) {
		Integer city_idInteger = Integer.parseInt(city_id);
		try {
			return database.getStationsByCityId(city_idInteger);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/tickets_by_mail")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public List<Ticket> getTicketsByMail(@RequestParam(name = "mail") String mail) {
		return database.getTicketsByTravelerID(database.getTravelerIDbyMail(mail));
	}

	@PostMapping("/add_traveler")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addTraveler(
			@RequestParam(name = "first_name") String first_name,
			@RequestParam(name = "last_name") String last_name,
			@RequestParam(name = "mail") String mail) {
		database.addTraveler(new Traveler(first_name, last_name, mail));
	}

	@PostMapping("/add_ticket")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addTicket(
			@RequestParam(name = "traveler_id") String traveler_id,
			@RequestParam(name = "first_stop") String first_stop,
			@RequestParam(name = "last_stop") String last_stop,
			@RequestParam(name = "train_id") String train_id,
			@RequestParam(name = "wagon_num") String wagon_num,
			@RequestParam(name = "seat_num") String seat_num) {
		Ticket ticket = new Ticket(
				Integer.parseInt(traveler_id),
				Integer.parseInt(first_stop),
				Integer.parseInt(last_stop),
				Integer.parseInt(train_id),
				Integer.parseInt(wagon_num),
				Integer.parseInt(seat_num));
		ticket.setTicket_id(database.addTicket(ticket));
	}

	@GetMapping("/get_traveler_mail")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public Integer getTravelerMail(@RequestParam(name = "mail") String mail) {
		return database.getTravelerIDbyMail(mail);
	}

	@PostMapping("/add_train")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addTrain(
			@RequestParam(name = "line_id") String line_id,
			@RequestParam(name = "locomotive_id") String locomotive_id) {
		Train train = new Train(Integer.parseInt(line_id), Integer.parseInt(locomotive_id));
		database.addTrain(train);
	}

	@PostMapping("/add_locomotive")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addLocomotive(
			@RequestParam(name = "model") String model,
			@RequestParam(name = "origin_country") String country) {
		Locomotive locomotive = new Locomotive(model, country);
		database.addLocomotive(locomotive);
	}

	@PostMapping("/add_wagon")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addWagon(
			@RequestParam(name = "wagon_num") String wagon_num,
			@RequestParam(name = "wagon_capacity") String wagon_capacity,
			@RequestParam(name = "train_id") String train_id) {
		Wagon wagon = new Wagon(Integer.parseInt(wagon_num), Integer.parseInt(wagon_capacity),
				Integer.parseInt(train_id));
		database.addWagon(wagon);
	}

	// BASIC LINE ADDING INTERFACE, REQUIRES MANUAL ID ACQUISITION AND EXISTING
	// LINESTOP
	@PostMapping("/add_line_basic")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addLineBasic(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "first_stop_id") String first_stop_id) {
		Line line = new Line(name, Integer.parseInt(first_stop_id));
		database.addLine(line);
	}

	// PROPER LINE ADDING INTERFACE, EASIER TO USE
	@PostMapping("/add_line")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addLine(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "station_id") String station_id) {
		// create linestop terminus for new line
		Linestop linestop = new Linestop(Integer.parseInt(station_id));
		// acquire ID for linestop and upload to database
		linestop.setID(database.addTerminus(linestop));
		// add line to database with terminus
		Line line = new Line(name, linestop.getID());
		database.addLine(line);
	}

	@GetMapping("/get_line")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public Line getLine(@RequestParam(name = "line_id") String line_id) {
		try {
			return database.getLineByID(Integer.parseInt(line_id));
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/get_active_trains")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public List<Train> getActiveTrains() {
		return database.getActiveTrains();
	}

	@PostMapping("/add_stop")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addStop(
			@RequestParam(name = "line_id") String line_id,
			@RequestParam(name = "station_id") String station_id,
			@RequestParam(name = "distance") String distance) {
		// get line
		Line line = database.getLineByID(Integer.parseInt(line_id));

		// create new linestop
		Linestop linestop = new Linestop(line.getFirst_stop_id(), Integer.parseInt(distance),
				Integer.parseInt(station_id));

		// add linestop to database, acquire ID
		linestop.setID(database.addLinestop(linestop));

		// change line first stop to new linestop
		line.setFirst_stop_id(linestop.getID());

		// update line in database
		database.updateLine(line);
	}

	// MANUAL LINESTOP ADDING - NOT RECOMMENDED
	@PostMapping("/add_terminus")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addTerminus(
			@RequestParam(name = "station_id") String station_id) {
		Linestop linestop = new Linestop(Integer.parseInt(station_id));
		database.addTerminus(linestop);
	}

	@PostMapping("/add_linestop")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addLinestop(
			@RequestParam(name = "next_linestop") String next_linestop,
			@RequestParam(name = "distance") String distance,
			@RequestParam(name = "station_id") String station_id) {
		Linestop linestop = new Linestop(Integer.parseInt(next_linestop), Integer.parseInt(distance),
				Integer.parseInt(station_id));
		database.addLinestop(linestop);
	}

	@PostMapping("/add_station")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addStation(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "city_id") String city_id) {
		Station station = new Station(name, Integer.parseInt(city_id));
		database.addStation(station);
	}

	@PostMapping("/add_city")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void addCity(
			@RequestParam(name = "name") String name) {
		City city = new City(name);
		database.addCity(city);
	}

	@PostMapping("/advance_train")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void advanceTrain(@RequestParam(name = "train_id") String train_id) {
		Integer train_idInt = Integer.parseInt(train_id);
		database.advanceTrain(train_idInt);
	}

	@GetMapping("/get_train")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public Train getTrain(@RequestParam(name = "train_id") String train_id) {
		return database.getTrainByID(Integer.parseInt(train_id));
	}

	@PostMapping("/change_linestop") // glownie pomocnicze do testowania
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public void changeLinestop(@RequestParam(name = "train_id") String train_id, @RequestParam(name = "next_linestop") String next_linestop) {
		database.changeLinestop(Integer.parseInt(train_id), Integer.parseInt(next_linestop));
	}

	@GetMapping("/first_stop")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public Integer getFirstStop(@RequestParam(name = "line_id") String line_id) {
		return database.getFirstStopID(Integer.parseInt(line_id));
	}

	@GetMapping("/get_train_capacity")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173"
	public Integer getTrainCapacity(@RequestParam(name = "train_id") String train_id) {
		return database.getTrainCapacity(Integer.parseInt(train_id));
	}

	@GetMapping("/get_seats_taken")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public List<Integer> getSeatsTaken(@RequestParam(name = "train_id") String train_id, @RequestParam(name = "wagon_num") String wagon_num) {
		return database.getSeatsTaken(Integer.parseInt(train_id), Integer.parseInt(wagon_num));
	}

	@GetMapping("/get_train_wagons")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public List<Wagon> getTrainWagons(@RequestParam(name = "train_id") String train_id) {
		return database.getWagonsByTrainID(Integer.parseInt(train_id));
	}

	@GetMapping("/get_all_trains")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public List<Train> getAllTrains() {
		return database.getAllTrains();
	}

	@GetMapping("/get_traveler_id_by_mail")
// komentarz do usuniecia- jak juz bedzie z apka to odkomentowac	@CrossOrigin(origins = "http://localhost:5173")
	public Integer getTravelerIDbyMail(@RequestParam(name = "mail") String mail) {
		return database.getTravelerIDbyMail(mail);
	}
}
