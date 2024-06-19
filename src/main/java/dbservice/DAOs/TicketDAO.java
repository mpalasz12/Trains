package dbservice.DAOs;

import dbservice.models.Ticket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAO {

	private final JdbcTemplate jdbcTemplate;

	public TicketDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private int acquireID() {
		String sql = "SELECT IDENTITY()";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public int addTicketGetID(Ticket ticket) {
		String sql = "INSERT INTO Tickets (traveler_id, first_stop, last_stop, train_id, wagon_num) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, ticket.getTravelerID(), ticket.getFirstStop(), ticket.getLastStop(), ticket.getTrainID(), ticket.getWagonNum());
		return acquireID();
	}

	public void expireTicket(int id) {
		String sql = "UPDATE Tickets SET expired = TRUE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void unexpireTicket(int id) {
		String sql = "UPDATE Tickets SET expired = FALSE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

}
