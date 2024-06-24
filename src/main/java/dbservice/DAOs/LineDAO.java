package dbservice.DAOs;

import dbservice.models.Line;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LineDAO {

	private final JdbcTemplate jdbcTemplate;

	private Integer acquireID() {
		String sql = "SELECT TOP 1 line_id FROM Lines ORDER BY line_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public LineDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer addLineGetID(Line line) {
		String sql = "INSERT INTO Lines (name) VALUES (?)";
		jdbcTemplate.update(sql, line.getName());
		return acquireID();
	}

	//public Integer getFirstStopID(Integer lineID) {
	//	String sql = "SELECT first_stop_id FROM Lines WHERE line_id = ?";
	//	return jdbcTemplate.queryForObject(sql, Integer.class, lineID);
	//}

	public Line getLineByID(Integer lineID) {
		String sql = "SELECT * FROM Lines WHERE line_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { lineID }, new BeanPropertyRowMapper<>(Line.class));
	}

	//public void updateLine(Line line) {
	//	String sql = "UPDATE Lines SET name = ?, first_stop_id = ? WHERE line_id = ?";
	//	jdbcTemplate.update(sql, line.getName(), line.getFirst_stop_id(), line.getLine_id());
	//}

	public List<Line> getAllLines() {
		String sql = "SELECT * FROM Lines";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Line.class));
	}

	public Line getLineByName(String name) {
		String sql = "SELECT * FROM Lines WHERE name = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<>(Line.class));
	}
}
