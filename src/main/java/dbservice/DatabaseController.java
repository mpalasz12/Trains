package dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import dbservice.DAOs.*;
import dbservice.models.*;

@RestController
public class DatabaseController {
	// DAOs objects go here
	private final TrainDAO trainDAO;
	private final TravelerDAO travelerDAO;
	private final CityDAO cityDAO;
	private final StationDAO stationDAO;
	private final LinestopDAO linestopDAO;
	private final LineDAO lineDAO;

	@Autowired
	public DatabaseController(
			TrainDAO trainDAO,
			TravelerDAO travelerDAO,
			CityDAO cityDAO,
			StationDAO stationDAO,
			LinestopDAO linestopDAO,
			LineDAO lineDAO
	) { // add new DAOs as
																								// arguments
		// initialize new DAOs
		this.trainDAO = trainDAO;
		this.travelerDAO = travelerDAO;
		this.cityDAO = cityDAO;
		this.stationDAO = stationDAO;
		this.linestopDAO = linestopDAO;
		this.lineDAO = lineDAO;
	}

	/*
	 * ---------------------------------- TRAIN ------------------------------------
	 */

	public void addTrain(Train train) {
		trainDAO.addTrain(train);
	}

	/*
	 * -------------------------------- TRAVELER -----------------------------------
	 */

	public void addTraveler(Traveler traveler) {
		travelerDAO.addTraveler(traveler);
	}

	public void deleteTraveler(int id) {
		travelerDAO.deleteTraveler(id);
	}

	public void changeMail(String newMail, int id) {
		travelerDAO.changeMail(newMail, id);
	}

	/*
	 * ---------------------------------- CITY ------------------------------------
	 */

	public void addCity(City city) {
		cityDAO.addCity(city);
	}

	/*
	 * ---------------------------------STATION ------------------------------------
	 */

	public void addStation(Station station) {
		stationDAO.addStation(station);
	}

	/*
	 * --------------------------------LINESTOP ------------------------------------
	 */

	public int addLinestopUpdateID(Linestop linestop) {
		return linestopDAO.addLinestopGetID(linestop);
	}

	/*
	 * --------------------------------LINESTOP ------------------------------------
	 */

	public int addLineUpdateID(Line line) {
		return lineDAO.addLineGetID(line);
	}
}
