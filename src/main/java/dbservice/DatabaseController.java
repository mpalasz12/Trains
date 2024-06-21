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
	private final LocomotiveDAO locomotiveDAO;
	private final TicketDAO ticketDAO;
	private final WagonDAO wagonDAO;

	@Autowired
	public DatabaseController(
			TrainDAO trainDAO,
			TravelerDAO travelerDAO,
			CityDAO cityDAO,
			StationDAO stationDAO,
			LinestopDAO linestopDAO,
			LineDAO lineDAO,
			LocomotiveDAO locomotiveDAO,
			TicketDAO ticketDAO,
			WagonDAO wagonDAO
	) { // add new DAOs as
																								// arguments
		// initialize new DAOs
		this.trainDAO = trainDAO;
		this.travelerDAO = travelerDAO;
		this.cityDAO = cityDAO;
		this.stationDAO = stationDAO;
		this.linestopDAO = linestopDAO;
		this.lineDAO = lineDAO;
		this.locomotiveDAO = locomotiveDAO;
		this.ticketDAO = ticketDAO;
		this.wagonDAO = wagonDAO;
	}

	/*
	 * ---------------------------------- TRAIN ------------------------------------
	 */

	public int addTrain(Train train) {
		return trainDAO.addTrainGetID(train);
	}

	public void deleteTrain(int id) {
		trainDAO.deleteTrain(id);
	}

	public void changeLocomotive(int id, int newLocomotiveID) {
		trainDAO.changeLocomotive(id, newLocomotiveID);
	}

	public void changeLinestop(int id, int nextLinestopID) {
		trainDAO.changeLinestop(id, nextLinestopID);
	}

	public void advanceTrain(int id) {
		trainDAO.changeLinestop(id, linestopDAO.getNextLinestopID(trainDAO.getLinestopID(id)));
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

	public String getCity(int id) {
		return cityDAO.getCity(id);
	}

	public City getCityByName(String name) {
		return cityDAO.getCityByName(name);
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

	// for regular stations, requires nextLinestop and nextDistance
	public int addLinestop(Linestop linestop) {
		return linestopDAO.addLinestopGetID(linestop);
	}

	// for terminus, doesn't requrie nextLinestop and nextDistance
	public int addTerminus(Linestop linestop) {
		return linestopDAO.addTerminusGetID(linestop);
	}

	public int getNextLinestopID(int id) {
		return linestopDAO.getNextLinestopID(id);
	}

	public int getNextLinestopDistance(int id) {
		return linestopDAO.getNextLinestopDistance(id);
	}

	public void setNextLinestop(int id, int nextLinestopID) {
		linestopDAO.setNextLinestop(id, nextLinestopID);
	}

	public void setNextLinestopDistance(int id, int distance) {
		linestopDAO.setNextLinestopDistance(id, distance);
	}
	
	/*
	 * --------------------------------LINE ----------------------------------------
	 */

	public int addLine(Line line) {
		return lineDAO.addLineGetID(line);
	}

	/*
	 * --------------------------------LOCOMOTIVE ------------------------------------
	 */

	public void addLocomotive(Locomotive locomotive) {
		locomotiveDAO.addLocomotive(locomotive);
	}

	/*
	 * --------------------------------TICKET ------------------------------------
	 */

	public void addTicket(Ticket ticket) {
		ticketDAO.addTicketGetID(ticket);
	}

	public void expireTicket(int id) {
		ticketDAO.expireTicket(id);
	}

	/*
	 * --------------------------------WAGON ------------------------------------
	 */

	public int addWagon(Wagon wagon) {
		return wagonDAO.addWagonGetID(wagon);
	}

	public void deleteWagon(int id) {
		wagonDAO.deleteWagon(id);
	}

	public void changeWagonNum(int id, int newWagonNum) {
		wagonDAO.changeWagonNum(id, newWagonNum);
	}

	public int getWagonNum(int id) {
		return wagonDAO.getWagonNum(id);
	}

	public int getTrainID(int id) {
		return wagonDAO.getTrainID(id);
	}

	public void changeTrain(int id, int newTrainID) {
		wagonDAO.changeTrain(id, newTrainID);
	}
}
