package dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import dbservice.DAOs.*;
import dbservice.models.*;

@RestController
public class DatabaseController {
	// DAOs objects go here
	private final TrainDAO trainDAO;

	@Autowired
	public DatabaseController(TrainDAO trainDAO) { // add new DAOs as arguments
		// initialize new DAOs
		this.trainDAO = trainDAO;
	}

	/*
	 * ---------------------------------- TRAIN ------------------------------------
	 */

	public void addTrain(Train train) {
		trainDAO.addTrain(train);
	}
}
