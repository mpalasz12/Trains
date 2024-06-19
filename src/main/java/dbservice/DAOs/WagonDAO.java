package dbservice.DAOs;

import dbservice.models.Wagon;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WagonDAO {
	private final JdbcTemplate jdbcTemplate;

	public WagonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int acquireID() {
		String sql = "SELECT IDENTITY()";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public int addWagonGetID(Wagon wagon) {
		String sql = "INSERT INTO Wagons (wagon_num, wagon_capacity, train_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, wagon.getWagonNum(), wagon.getWagonCapacity(), wagon.getTrainID());
		return acquireID();
	}

	public void deleteWagon(int id) {
		String sql = "DELETE FROM Wagons WHERE wagon_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void changeWagonNum(int id, int newWagonNum) {
		String sql = "UPDATE Wagons SET wagon_num = ? WHERE wagon_id = ?";
		jdbcTemplate.update(sql, newWagonNum, id);
	}

	public int getWagonNum(int id) {
		String sql = "SELECT wagon_num FROM Wagons WHERE wagon_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public int getTrainID(int id) {
		String sql = "SELECT train_id FROM Wagons WHERE wagon_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void changeTrain(int id, int newTrainID) {
		String sql = "UPDATE Wagons SET train_id = ? WHERE wagon_id = ?";
		jdbcTemplate.update(sql, newTrainID, id);
	}
}
