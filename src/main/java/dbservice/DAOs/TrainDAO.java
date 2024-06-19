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

	public int acquireID() {
		String sql = "SELECT IDENTITY()";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public int addTrainGetID(Train train) {
		String sql = "INSERT INTO Trains (line_id, locomotive_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, train.getLineID(), train.getLocomotiveID());
		return acquireID();
	}

	public void deleteTrain(int id) {
		String sql = "DELETE FROM Trains WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public int getLinestopID(int id) {
		String sql = "SELECT current_linestop FROM Trains WHERE train_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void changeLocomotive(int id, int newLocomotiveID) {
		String sql = "UPDATE Trains SET locomotive_id = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, newLocomotiveID, id);
	}

	public void changeLinestop(int id, int nextLinestopID) {
		String sql = "UPDATE Trains SET current_linestop = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, id, nextLinestopID);
	}

}
