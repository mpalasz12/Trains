package dbservice.DAOs;

import dbservice.models.Linestop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LinestopDAO {

	private final JdbcTemplate jdbcTemplate;

	public LinestopDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private int acquireID() {
		// Get newest entry ID
		String sql = "SELECT IDENTITY()";  // TODO: Check if this works (same for Line)
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public int addTerminusGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (station_id) VALUES (?)";
		jdbcTemplate.update(sql, linestop.getStationID());
		return acquireID();
	}

	public int addLinestopGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (next_linestop, distance_next, station_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, linestop.getNextStop(), linestop.getNextDistance(), linestop.getStationID());
		return acquireID();
	}

	public int getNextLinestopID(int id) {
		String sql = "SELECT next_linestop FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public int getNextLinestopDistance(int id) {
		String sql = "SELECT distance_next FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void setNextLinestop(int id, int nextLinestopID) {
		String sql = "UPDATE Linestops SET next_linestop = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, nextLinestopID, id);
	}

	public void setNextLinestopDistance(int id, int distance) {
		String sql = "UPDATE Linestops SET distance_next = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, distance, id);
	}
}
