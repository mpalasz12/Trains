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
		String sql = "INSERT INTO Travelers (firstName, lastName, mailAddress) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, traveler.getFirstName(), traveler.getLastName(), traveler.getMailAddress());
	}

	public void deleteTraveler(int id) {
		String sql = "DELETE FROM Travelers WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void changeMail(String newMail, int id) {
		String sql = "UPDATE Travelers SET mailAddress = ? WHERE id = ?";
		jdbcTemplate.update(sql, newMail, id);
	}
}
