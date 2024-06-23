package dbservice.DAOs;

import dbservice.models.Station;
import dbservice.models.Train;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainDAO {
	private final JdbcTemplate jdbcTemplate;

	public TrainDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer acquireID() {
		String sql = "SELECT TOP 1 train_id FROM Trains ORDER BY train_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addTrainGetID(Train train) {
		String sql = "INSERT INTO Trains (line_id, locomotive_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, train.getLine_id(), train.getLocomotive_id());
		return acquireID();
	}

	public void deleteTrain(Integer id) {
		String sql = "DELETE FROM Trains WHERE train_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public Integer getLinestopID(Integer id) {
		String sql = "SELECT curr_linestop FROM Trains WHERE train_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}

	public void changeLocomotive(Integer id, Integer newLocomotiveID) {
		String sql = "UPDATE Trains SET locomotive_id = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, newLocomotiveID, id);
	}

	public void changeLinestop(Integer id, Integer nextLinestopID) {
		String sql = "UPDATE Trains SET curr_linestop = ? WHERE train_id = ?";
		jdbcTemplate.update(sql, nextLinestopID, id);
	}

	public Train getTrainByID(Integer id) {
		String sql = "SELECT * FROM Trains WHERE train_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Train.class));
	}

	public List<Train> getActiveTrains() {
		String sql = "SELECT * FROM Trains WHERE curr_linestop IS NOT NULL";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Train.class));
	}
}
