package dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import dbservice.DAOs.*;
import dbservice.models.*;

@RestController
public class DatabaseController {
	// DAOs objects go here
	private final TrainDAO trainDAO;
	private final PassengerDAO passengerDAO;
	private final CityDAO cityDAO;

	@Autowired
	public DatabaseController(TrainDAO trainDAO, PassengerDAO passengerDAO, CityDAO cityDAO) { // add new DAOs as
																								// arguments
		// initialize new DAOs
		this.trainDAO = trainDAO;
		this.passengerDAO = passengerDAO;
		this.cityDAO = cityDAO;
	}

	/*
	 * ---------------------------------- TRAIN ------------------------------------
	 */

	public void addTrain(Train train) {
		trainDAO.addTrain(train);
	}

	/*
	 * -------------------------------- PASSENGER ----------------------------------
	 */

	public void addPassenger(Passenger passenger) {
		passengerDAO.addPassenger(passenger);
	}

	public void deletePassenger(int id) {
		passengerDAO.deletePassenger(id);
	}

	public void changeMail(String newMail, String id) {
		passengerDAO.changeMail(newMail, id);
	}

	/*
	 * ---------------------------------- CITY ------------------------------------
	 */

	public void addCity(City city) {
		cityDAO.addCity(city);
	}

}
