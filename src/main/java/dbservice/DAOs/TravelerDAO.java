package dbservice.DAOs;

import dbservice.models.Traveler;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TravelerDAO {

	private final JdbcTemplate jdbcTemplate;

	public TravelerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addTraveler(Traveler traveler) {
		String sql = "INSERT INTO Travelers (first_name, last_name, mail_address) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, traveler.getFirst_name(), traveler.getLast_name(), traveler.getMail_address());
	}

	public void deleteTraveler(Integer id) {
		String sql = "DELETE FROM Travelers WHERE traveler_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void changeMail(String newMail, Integer id) {
		String sql = "UPDATE Travelers SET mail_address = ? WHERE traveler_id = ?";
		jdbcTemplate.update(sql, newMail, id);
	}

	public Integer getTravelerIDbyMail(String mail) {
		String sql = "SELECT traveler_id FROM Travelers WHERE mail_address = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, mail);
	}

	public Traveler getTravelerByID(Integer id) {
		String sql = "SELECT * FROM Travelers WHERE traveler_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Traveler.class));
	}

	public Traveler getTravelerByMail(String mail) {
		String sql = "SELECT * FROM Travelers WHERE mail_address = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { mail }, new BeanPropertyRowMapper<>(Traveler.class));
	}

	public List<String> getAllMails() {
		String sql = "SELECT mail_address FROM Travelers";
		return jdbcTemplate.queryForList(sql, String.class);
	}
}
