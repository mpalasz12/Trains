package dbservice.DAOs;

import dbservice.models.Traveler;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TravelerDAO {

	private final JdbcTemplate jdbcTemplate;

	public TravelerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addTraveler(Traveler traveler) {
		String sql = "INSERT INTO Travelers (first_name, last_name, mail_address) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, traveler.getFirstName(), traveler.getLastName(), traveler.getMailAddress());
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
}
