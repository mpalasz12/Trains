package dbservice.DAOs;

import dbservice.models.Station;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StationDAO {

	private final JdbcTemplate jdbcTemplate;

	public StationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addStation(Station Station) {
		String sql = "INSERT INTO Station (name, city_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, Station.getName());
	}
}
