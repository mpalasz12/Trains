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

	public Integer acquireID() {
		String sql = "SELECT TOP 1 station_id FROM Stations ORDER BY station_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addStation(Station Station) {
		String sql = "INSERT INTO Stations (name, city_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, Station.getName(), Station.getCity_id());
		return acquireID();
	}

	public Station getStation(Integer id) {
		String sql = "SELECT * FROM Stations WHERE station_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Station.class));
	}

	public List<Station> getAllStations() {
		String sql = "SELECT * FROM Stations";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Station.class));
	}

	public List<Station> getStationsByCityId(Integer city_id) {
		String sql = "SELECT * FROM Stations WHERE city_id = ?";
		return jdbcTemplate.query(sql, new Object[] { city_id }, new BeanPropertyRowMapper<>(Station.class));
	}
}
