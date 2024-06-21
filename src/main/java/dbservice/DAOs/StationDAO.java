package dbservice.DAOs;

import dbservice.models.Station;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAO {

	private final JdbcTemplate jdbcTemplate;

	public StationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addStation(Station Station) {
		String sql = "INSERT INTO Stations (name, city_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, Station.getName(), Station.getCity_id());
	}

	public Station getStation(Long id) {
		String sql = "SELECT * FROM Stations WHERE station_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Station.class));
	}

	public List<Station> getAllStations() {
		String sql = "SELECT * FROM Stations";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Station.class));
	}

	public List<Station> getStationsByCityId(Long city_id) {
		String sql = "SELECT * FROM Stations WHERE city_id = ?";
		return jdbcTemplate.query(sql, new Object[]{city_id}, new BeanPropertyRowMapper<>(Station.class));
	}
}
