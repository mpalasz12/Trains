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

	public Long acquireID() {
		String sql = "SELECT TOP 1 train_id FROM Trains ORDER BY train_id DESC";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public Long addTrainGetID(Train train) {
		String sql = "INSERT INTO Trains (line_id, locomotive_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, train.getLineID(), train.getLocomotiveID());
		return acquireID();
	}

	public void deleteTrain(Long id) {
		String sql = "DELETE FROM Trains WHERE train_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public Long getLinestopID(Long id) {
		String sql = "SELECT current_linestop FROM Trains WHERE train_id = ?";
		return jdbcTemplate.queryForObject(sql, Long.class, id);
	}

	public void changeLocomotive(Long id, Long newLocomotiveID) {
		String sql = "UPDATE Trains SET locomotive_id = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, newLocomotiveID, id);
	}

	public void changeLinestop(Long id, Long nextLinestopID) {
		String sql = "UPDATE Trains SET current_linestop = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, id, nextLinestopID);
	}

}
