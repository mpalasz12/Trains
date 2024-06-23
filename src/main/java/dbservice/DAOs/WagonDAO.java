package dbservice.DAOs;

import dbservice.models.Train;
import dbservice.models.Wagon;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WagonDAO {
	private final JdbcTemplate jdbcTemplate;

	public WagonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer acquireID() {
		String sql = "SELECT TOP 1 wagon_id FROM Wagons ORDER BY wagon_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addWagonGetID(Wagon wagon) {
		String sql = "INSERT INTO Wagons (wagon_num, wagon_capacity, train_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, wagon.getWagon_num(), wagon.getWagon_capacity(), wagon.getTrain_id());
		return acquireID();
	}

	public void deleteWagon(Integer id) {
		String sql = "DELETE FROM Wagons WHERE wagon_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void changeWagonNum(Integer id, int newWagonNum) {
		String sql = "UPDATE Wagons SET wagon_num = ? WHERE wagon_id = ?";
		jdbcTemplate.update(sql, newWagonNum, id);
	}

	public int getWagonNum(int id) {
		String sql = "SELECT wagon_num FROM Wagons WHERE wagon_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public Integer getTrainID(Integer id) {
		String sql = "SELECT train_id FROM Wagons WHERE wagon_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}

	public void changeTrain(Integer id, Integer newTrainID) {
		String sql = "UPDATE Wagons SET train_id = ? WHERE wagon_id = ?";
		jdbcTemplate.update(sql, newTrainID, id);
	}

	public Integer getTrainCapacity(Integer train_id) {
		String sql = "SELECT SUM(wagon_capacity) FROM Wagons WHERE train_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, train_id);
	}

	public List<Wagon> getWagonsByTrainID(Integer trainID) {
		String sql = "SELECT * FROM Wagons WHERE train_id = ?";
		return jdbcTemplate.query(sql, new Object[] { trainID }, new BeanPropertyRowMapper<>(Wagon.class));
	}
}
