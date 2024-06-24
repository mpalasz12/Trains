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

	public Integer addTrain(Train train) {
		return trainDAO.addTrainGetID(train);
	}

	public void deleteTrain(Integer id) {
		trainDAO.deleteTrain(id);
	}

	public void changeLocomotive(Integer id, Integer newLocomotiveID) {
		trainDAO.changeLocomotive(id, newLocomotiveID);
	}

	public void changeLinestop(Integer id, Integer nextLinestopID) {
		trainDAO.changeLinestop(id, nextLinestopID);
	}

	public Train getTrainByID(Integer id) {
		return trainDAO.getTrainByID(id);
	}

	public void advanceTrain(Integer id) {
		Train train = trainDAO.getTrainByID(id);

		if (train.getCurr_linestop() != null) {
			trainDAO.changeLinestop(id, linestopDAO.getNextLinestopID(train.getCurr_linestop()));
		} else {
			trainDAO.changeLinestop(id, lineDAO.getFirstStopID(train.getLine_id()));
		}
	}

	public List<Train> getActiveTrains() {
		return trainDAO.getActiveTrains();
	}

	public Integer getTrainCapacity(Integer train_id) {
		return wagonDAO.getTrainCapacity(train_id);
	}

	public List<Train> getAllTrains() {
		return trainDAO.getAllTrains();
	}

	/*
	 * -------------------------------- TRAVELER -----------------------------------
	 */

	public void addTraveler(Traveler traveler) {
		travelerDAO.addTraveler(traveler);
	}

	public void deleteTraveler(Integer id) {
		travelerDAO.deleteTraveler(id);
	}

	public void changeMail(String newMail, Integer id) {
		travelerDAO.changeMail(newMail, id);
	}

	public Integer getTravelerIDbyMail(String mail) {
		return travelerDAO.getTravelerIDbyMail(mail);
	}

	/*
	 * ---------------------------------- CITY ------------------------------------
	 */

	public void addCity(City city) {
		cityDAO.addCity(city);
	}

	public String getCity(Integer id) {
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

	public List<City> getAllCities() {
		return cityDAO.getAllCities();
	}

	/*
	 * ---------------------------------STATION ------------------------------------
	 */

	public Integer addStation(Station station) {
		return stationDAO.addStation(station);
	}

	public Station getStation(Integer id) {
		return stationDAO.getStation(id);
	}

	public List<Station> getAllStations() {
		return stationDAO.getAllStations();
	}

	public List<Station> getStationsByCityId(Integer city_id) {
		return stationDAO.getStationsByCityId(city_id);
	}

	public Station getStationByLinestopID(Integer linestopID) {
		Integer stationID = linestopDAO.getLinestopByID(linestopID).getStation_id();
		return stationDAO.getStation(stationID);
	}
	/*
	 * --------------------------------LINESTOP ------------------------------------
	 */

	// for regular stations, requires nextLinestop and nextDistance
	public Integer addLinestop(Linestop linestop) {
		return linestopDAO.addLinestopGetID(linestop);
	}

	// for terminus, doesn't requrie nextLinestop and nextDistance
	public Integer addTerminus(Linestop linestop) {
		return linestopDAO.addTerminusGetID(linestop);
	}

	public Integer getNextLinestopID(Integer id) {
		return linestopDAO.getNextLinestopID(id);
	}

	public int getNextLinestopDistance(int id) {
		return linestopDAO.getNextLinestopDistance(id);
	}

	public void setNextLinestop(Integer id, Integer nextLinestopID) {
		linestopDAO.setNextLinestop(id, nextLinestopID);
	}

	public void setNextLinestopDistance(Integer id, Integer distance) {
		linestopDAO.setNextLinestopDistance(id, distance);
	}

	public Linestop getLinestopByID(Integer id) {
		return linestopDAO.getLinestopByID(id);
	}
	/*
	 * --------------------------------LINE ----------------------------------------
	 */

	public Integer createLine(String name, Integer stationId) {
		Linestop linestop = new Linestop(stationId);
		// acquire ID for linestop and upload to database
		linestop.setLinestop_id(addTerminus(linestop));
		// add line to database with terminus
		Line line = new Line(name, linestop.getLinestop_id());
		return addLine(line);
	}

	public void addStopToLine(Integer lineID, Integer distance, Integer stationID) {
		Line line = getLineByID(lineID);
		Linestop linestop = new Linestop(line.getFirst_stop_id(), distance, stationID);
		linestop.setLinestop_id(addLinestop(linestop));
		line.setFirst_stop_id(linestop.getLinestop_id());
		updateLine(line);
	}

	public Integer addLine(Line line) {
		return lineDAO.addLineGetID(line);
	}

	public Line getLineByID(Integer lineID) {
		return lineDAO.getLineByID(lineID);
	}

	public void updateLine(Line line) {
		lineDAO.updateLine(line);
	}

	public Integer getFirstStopID(Integer lineID) {
		return lineDAO.getFirstStopID(lineID);
	}

	public List<Line> getAllLines() {
		return lineDAO.getAllLines();
	}

	public Line getLineByName(String name) {
		return lineDAO.getLineByName(name);
	}

	/*
	 * --------------------------------LOCOMOTIVE
	 * ------------------------------------
	 */

	public Integer addLocomotive(Locomotive locomotive) {
		return locomotiveDAO.addLocomotive(locomotive);
	}

	public Locomotive getLocomotiveByID(Integer id) {
		return locomotiveDAO.getLocomotiveByID(id);
	}

	public List<Locomotive> getAllLocomotives() {
		return locomotiveDAO.getAllLocomotives();
	}

	/*
	 * --------------------------------TICKET ------------------------------------
	 */

	public Integer addTicket(Ticket ticket) {
		return ticketDAO.addTicketGetID(ticket);
	}

	public void expireTicket(Integer id) {
		ticketDAO.expireTicket(id);
	}

	public List<Ticket> getTicketsByTravelerID(Integer travelerID) {
		return ticketDAO.getTicketsByTravelerID(travelerID);
	}

	public List<Integer> getSeatsTaken(Integer trainID, Integer wagonNum) {
		return ticketDAO.getSeatsTaken(trainID, wagonNum);
	}

	/*
	 * --------------------------------WAGON ------------------------------------
	 */

	public Integer addWagon(Wagon wagon) {
		return wagonDAO.addWagonGetID(wagon);
	}

	public void deleteWagon(Integer id) {
		wagonDAO.deleteWagon(id);
	}

	public void changeWagonNum(Integer id, int newWagonNum) {
		wagonDAO.changeWagonNum(id, newWagonNum);
	}

	public int getWagonNum(int id) {
		return wagonDAO.getWagonNum(id);
	}

	public Integer getTrainID(Integer id) {
		return wagonDAO.getTrainID(id);
	}

	public void changeTrain(Integer id, Integer newTrainID) {
		wagonDAO.changeTrain(id, newTrainID);
	}

	public List<Wagon> getWagonsByTrainID(Integer trainID) {
		return wagonDAO.getWagonsByTrainID(trainID);
	}
}

