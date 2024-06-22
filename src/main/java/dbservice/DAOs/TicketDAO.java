package dbservice.DAOs;

import dbservice.models.Ticket;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAO {

	private final JdbcTemplate jdbcTemplate;

	public TicketDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private Long acquireID() {
		String sql = "SELECT IDENTITY()";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	public Long addTicketGetID(Ticket ticket) {
		String sql = "INSERT INTO Tickets (traveler_id, first_stop, last_stop, train_id, wagon_num) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, ticket.getTravelerID(), ticket.getFirstStop(), ticket.getLastStop(),
				ticket.getTrainID(), ticket.getWagonNum());
		return acquireID();
	}

	public void expireTicket(Long id) {
		String sql = "UPDATE Tickets SET expired = TRUE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void unexpireTicket(Long id) {
		String sql = "UPDATE Tickets SET expired = FALSE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public List<Ticket> getTicketsByTravelerID(Long travelerID) {
		String sql = "SELECT * FROM Tickets WHERE traveler_id = ?";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ticket.class), travelerID);
	}
}
