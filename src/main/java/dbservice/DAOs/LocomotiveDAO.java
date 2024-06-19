package dbservice.DAOs;

import dbservice.models.Locomotive;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocomotiveDAO {
	private final JdbcTemplate jdbcTemplate;

	public LocomotiveDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addLocomotive(Locomotive locomotive) {
		String sql = "INSERT INTO locomotives (model, origin_country) VALUES (?, ?)";
		jdbcTemplate.update(sql, locomotive.getModel(), locomotive.getOriginCountry());
	}
}
