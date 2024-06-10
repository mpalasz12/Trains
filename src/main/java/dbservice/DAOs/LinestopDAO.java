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

	public int addLinestopGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (station_id) VALUES (?)";
		jdbcTemplate.update(sql, linestop.getStationID());
		return acquireID();
	}
}
