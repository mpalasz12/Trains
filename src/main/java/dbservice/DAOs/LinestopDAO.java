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

	private Long acquireID() {
		// Get newest entry ID
		String sql = "SELECT TOP 1 linestop_id FROM Linestops ORDER BY linestop_id DESC";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public Long addTerminusGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (station_id) VALUES (?)";
		jdbcTemplate.update(sql, linestop.getStationID());
		return acquireID();
	}

	public Long addLinestopGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (next_linestop, distance_next, station_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, linestop.getNextStop(), linestop.getNextDistance(), linestop.getStationID());
		return acquireID();
	}

	public Long getNextLinestopID(Long id) {
		String sql = "SELECT next_linestop FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, Long.class, id);
	}

	public int getNextLinestopDistance(int id) {
		String sql = "SELECT distance_next FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void setNextLinestop(Long id, Long nextLinestopID) {
		String sql = "UPDATE Linestops SET next_linestop = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, nextLinestopID, id);
	}

	public void setNextLinestopDistance(Long id, Long distance) {
		String sql = "UPDATE Linestops SET distance_next = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, distance, id);
	}
}
