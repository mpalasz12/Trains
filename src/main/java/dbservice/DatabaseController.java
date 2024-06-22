package dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;

import dbservice.DAOs.*;
import dbservice.models.*;

import java.util.List;

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
			WagonDAO wagonDAO) { // add new DAOs as
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

	public Long addTrain(Train train) {
		return trainDAO.addTrainGetID(train);
	}

	public void deleteTrain(Long id) {
		trainDAO.deleteTrain(id);
	}

	public void changeLocomotive(Long id, Long newLocomotiveID) {
		trainDAO.changeLocomotive(id, newLocomotiveID);
	}

	public void changeLinestop(Long id, Long nextLinestopID) {
		trainDAO.changeLinestop(id, nextLinestopID);
	}

	public void advanceTrain(Long id) {
		trainDAO.changeLinestop(id, linestopDAO.getNextLinestopID(trainDAO.getLinestopID(id)));
	}

	/*
	 * -------------------------------- TRAVELER -----------------------------------
	 */

	public void addTraveler(Traveler traveler) {
		travelerDAO.addTraveler(traveler);
	}

	public void deleteTraveler(Long id) {
		travelerDAO.deleteTraveler(id);
	}

	public void changeMail(String newMail, Long id) {
		travelerDAO.changeMail(newMail, id);
	}

	public Long getTravelerIDbyMail(String mail) {
		return travelerDAO.getTravelerIDbyMail(mail);
	}

	/*
	 * ---------------------------------- CITY ------------------------------------
	 */

	public void addCity(City city) {
		cityDAO.addCity(city);
	}

	public String getCity(Long id) {
		return cityDAO.getCity(id);
	}

	public City getCityByName(String name) {
		City city;
		try {
			city = cityDAO.getCityByName(name);
		} catch (DataAccessException e) {
			return null;
		}
		return city;
	}

	/*
	 * ---------------------------------STATION ------------------------------------
	 */

	public void addStation(Station station) {
		stationDAO.addStation(station);
	}

	public Station getStation(Long id) {
		return stationDAO.getStation(id);
	}

	public List<Station> getAllStations() {
		return stationDAO.getAllStations();
	}

	public List<Station> getStationsByCityId(Long city_id) {
		return stationDAO.getStationsByCityId(city_id);
	}

	/*
	 * --------------------------------LINESTOP ------------------------------------
	 */

	// for regular stations, requires nextLinestop and nextDistance
	public Long addLinestop(Linestop linestop) {
		return linestopDAO.addLinestopGetID(linestop);
	}

	// for terminus, doesn't requrie nextLinestop and nextDistance
	public Long addTerminus(Linestop linestop) {
		return linestopDAO.addTerminusGetID(linestop);
	}

	public Long getNextLinestopID(Long id) {
		return linestopDAO.getNextLinestopID(id);
	}

	public int getNextLinestopDistance(int id) {
		return linestopDAO.getNextLinestopDistance(id);
	}

	public void setNextLinestop(Long id, Long nextLinestopID) {
		linestopDAO.setNextLinestop(id, nextLinestopID);
	}

	public void setNextLinestopDistance(Long id, Long distance) {
		linestopDAO.setNextLinestopDistance(id, distance);
	}

	/*
	 * --------------------------------LINE ----------------------------------------
	 */

	public Long addLine(Line line) {
		return lineDAO.addLineGetID(line);
	}

	public Line getLineByID(Long lineID) {
		return lineDAO.getLineByID(lineID);
	}

	public void updateLine(Line line) {
		lineDAO.updateLine(line);
	}

	/*
	 * --------------------------------LOCOMOTIVE
	 * ------------------------------------
	 */

	public void addLocomotive(Locomotive locomotive) {
		locomotiveDAO.addLocomotive(locomotive);
	}

	/*
	 * --------------------------------TICKET ------------------------------------
	 */

	public Long addTicket(Ticket ticket) {
		return ticketDAO.addTicketGetID(ticket);
	}

	public void expireTicket(Long id) {
		ticketDAO.expireTicket(id);
	}

	public List<Ticket> getTicketsByTravelerID(Long travelerID) {
		return ticketDAO.getTicketsByTravelerID(travelerID);
	}

	/*
	 * --------------------------------WAGON ------------------------------------
	 */

	public Long addWagon(Wagon wagon) {
		return wagonDAO.addWagonGetID(wagon);
	}

	public void deleteWagon(Long id) {
		wagonDAO.deleteWagon(id);
	}

	public void changeWagonNum(Long id, int newWagonNum) {
		wagonDAO.changeWagonNum(id, newWagonNum);
	}

	public int getWagonNum(int id) {
		return wagonDAO.getWagonNum(id);
	}

	public Long getTrainID(Long id) {
		return wagonDAO.getTrainID(id);
	}

	public void changeTrain(Long id, Long newTrainID) {
		wagonDAO.changeTrain(id, newTrainID);
	}
}
