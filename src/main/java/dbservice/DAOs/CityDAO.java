package dbservice.DAOs;

import dbservice.models.City;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAO {

	private final JdbcTemplate jdbcTemplate;

	public CityDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addCity(City city) {
		String sql = "INSERT INTO Cities (name) VALUES (?)";
		jdbcTemplate.update(sql, city.getName());
	}
}
