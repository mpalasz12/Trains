package dbservice.DAOs;

import dbservice.models.Locomotive;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocomotiveDAO {
	private final JdbcTemplate jdbcTemplate;

	private Integer acquireID() {
		String sql = "SELECT TOP 1 locomotive_id FROM Locomotives ORDER BY locomotive_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public LocomotiveDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer addLocomotive(Locomotive locomotive) {
		String sql = "INSERT INTO locomotives (model, origin_country) VALUES (?, ?)";
		jdbcTemplate.update(sql, locomotive.getModel(), locomotive.getOriginCountry());
		return acquireID();
	}
}
