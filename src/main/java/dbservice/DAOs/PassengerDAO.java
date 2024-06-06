package dbservice.DAOs;

import dbservice.models.Passenger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDAO {

	private final JdbcTemplate jdbcTemplate;

	public PassengerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addPassenger(Passenger passenger) {
		String sql = "INSERT INTO Passengers (firstName, lastName, mailAddress) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, passenger.getFirstName(), passenger.getLastName(), passenger.getMailAddress());
	}

	public void deletePassenger(int id) {
		String sql = "DELETE FROM Passengers WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void changeMail(String newMail, String id) {
		String sql = "UPDATE Passengers SET mailAddress = ? WHERE id = ?";
		jdbcTemplate.update(sql, newMail, id);
	}

}
