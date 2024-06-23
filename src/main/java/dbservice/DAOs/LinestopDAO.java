package dbservice.DAOs;

import dbservice.models.Linestop;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LinestopDAO {

	private final JdbcTemplate jdbcTemplate;

	public LinestopDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Integer acquireID() {
		// Get newest entry ID
		String sql = "SELECT TOP 1 linestop_id FROM Linestops ORDER BY linestop_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addTerminusGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (station_id) VALUES (?)";
		jdbcTemplate.update(sql, linestop.getStation_id());
		return acquireID();
	}

	public Integer addLinestopGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (next_linestop, distance_next, station_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, linestop.getNext_linestop(), linestop.getDistance_next(), linestop.getStation_id());
		return acquireID();
	}

	public Integer getNextLinestopID(Integer id) {
		String sql = "SELECT next_linestop FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}

	public int getNextLinestopDistance(int id) {
		String sql = "SELECT distance_next FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void setNextLinestop(Integer id, Integer nextLinestopID) {
		String sql = "UPDATE Linestops SET next_linestop = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, nextLinestopID, id);
	}

	public void setNextLinestopDistance(Integer id, Integer distance) {
		String sql = "UPDATE Linestops SET distance_next = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, distance, id);
	}

	public Linestop getLinestopByID(Integer id) {
		String sql = "SELECT * FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Linestop.class));
	}
}
