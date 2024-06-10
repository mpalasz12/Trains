package dbservice.DAOs;

import dbservice.models.Line;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LineDAO {

	private final JdbcTemplate jdbcTemplate;

	private int acquireID() {
		String sql = "SELECT IDENTITY()";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public LineDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addLineGetID(Line line) {
		String sql = "INSERT INTO Lines (name, first_stop_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, line.getName(), line.getFirstStopID());
		return acquireID();
	}
}

