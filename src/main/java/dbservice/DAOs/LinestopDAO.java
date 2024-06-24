package dbservice.DAOs;

import dbservice.models.Linestop;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LinestopDAO {

	private final JdbcTemplate jdbcTemplate;

	public LinestopDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Integer acquireID() {
		// Get newest entry ID
		String sql = "SELECT TOP 1 linestop_id FROM Linestops ORDER BY linestop_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addTerminusGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (station_id, line_id, is_first) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, linestop.getStation_id(), linestop.getLine_id(), linestop.getIs_first());
		return acquireID();
	}

	public Integer addLinestopGetID(Linestop linestop) {
		String sql = "INSERT INTO Linestops (next_linestop, distance_next, station_id, line_id, is_first) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, linestop.getNext_linestop(), linestop.getDistance_next(), linestop.getStation_id(), linestop.getLine_id(), linestop.getIs_first());
		return acquireID();
	}

	public Integer getNextLinestopID(Integer id) {
		String sql = "SELECT next_linestop FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, id);
	}

	public int getNextLinestopDistance(int id) {
		String sql = "SELECT distance_next FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, int.class, id);
	}

	public void setNextLinestop(Integer id, Integer nextLinestopID) {
		String sql = "UPDATE Linestops SET next_linestop = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, nextLinestopID, id);
	}

	public void setNextLinestopDistance(Integer id, Integer distance) {
		String sql = "UPDATE Linestops SET distance_next = ? WHERE linestop_id = ?";
		jdbcTemplate.update(sql, distance, id);
	}

	public Linestop getLinestopByID(Integer id) {
		String sql = "SELECT * FROM Linestops WHERE linestop_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Linestop.class));
	}

	public Integer getFirstLinestopID(Integer lineID) {
		String sql = "SELECT linestop_id FROM Linestops WHERE line_id = ? AND is_first = true";
		return jdbcTemplate.queryForObject(sql, int.class, lineID);
	}

	public void setFirstLinestop(Integer id) {
		String sql = "UPDATE Linestops SET is_first = true WHERE linestop_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void unsetFirstLinestop(Integer id) {
		String sql = "UPDATE Linestops SET is_first = false WHERE linestop_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public Integer getFirstAndUnset(Integer id) {
		Integer firstLinestopID = getFirstLinestopID(id);
		unsetFirstLinestop(firstLinestopID);
		return firstLinestopID;
	}

	public List<Integer> findLineID(String startName, String endName) {
		String sql = "SELECT ls1.line_id, ls1.linestop_id AS start_station_id, ls2.linestop_id AS end_station_id" + 
					" FROM Linestops ls1" +
					" JOIN Linestops ls2 ON ls1.line_id = ls2.line_id JOIN Stations s1 ON ls1.station_id = s1.station_id" +
					" JOIN Stations s2 ON ls2.station_id = s2.station_id" +
					" WHERE s1.name = ? AND s2.name = ? AND ls1.linestop_id > ls2.linestop_id" +
					" FETCH FIRST ROW ONLY";

		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, startName, endName);

		if (result.isEmpty()) {
            return null;
        }

        Map<String, Object> row = result.get(0);
        return List.of(
                (Integer) row.get("line_id"),
                (Integer) row.get("start_station_id"),
                (Integer) row.get("end_station_id")
        );
	}
}
