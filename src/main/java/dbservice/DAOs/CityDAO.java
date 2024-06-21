package dbservice.DAOs;

import dbservice.models.City;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

	public String getCity(Long id) {
		String sql = "SELECT name FROM Cities WHERE city_id = ?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	public City getCityByName(String name) {
		String sql = "SELECT * FROM Cities WHERE name = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<>(City.class));
	}
}
