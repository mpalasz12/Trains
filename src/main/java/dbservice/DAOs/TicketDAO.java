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

	private Integer acquireID() {
		String sql = "SELECT TOP 1 ticket_id FROM Tickets ORDER BY ticket_id DESC";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Integer addTicketGetID(Ticket ticket) {
		String sql = "INSERT INTO Tickets (traveler_id, first_stop, last_stop, train_id, wagon_num) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, ticket.getTravelerID(), ticket.getFirstStop(), ticket.getLastStop(),
				ticket.getTrainID(), ticket.getWagonNum());
		return acquireID();
	}

	public void expireTicket(Integer id) {
		String sql = "UPDATE Tickets SET expired = TRUE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public void unexpireTicket(Integer id) {
		String sql = "UPDATE Tickets SET expired = FALSE WHERE ticket_id = ?";
		jdbcTemplate.update(sql, id);
	}

	public List<Ticket> getTicketsByTravelerID(Integer travelerID) {
		String sql = "SELECT * FROM Tickets WHERE traveler_id = ?";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ticket.class), travelerID);
	}
}
