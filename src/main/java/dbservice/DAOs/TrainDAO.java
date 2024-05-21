package dbservice.DAOs;

import dbservice.models.Train;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDAO {
	private final JdbcTemplate jdbcTemplate;

	public TrainDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addTrain(Train train) {
		String sql = "INSERT INTO trains (train_id) VALUES (?)";
		jdbcTemplate.update(sql, train.getTrainID());
	}
}
