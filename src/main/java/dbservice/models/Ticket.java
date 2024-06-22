package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Ticket {
	private Long ticket_id;
	private Long traveler_id;
	private Long first_stop;
	private Long last_stop;
	private Long train_id;
	private int wagon_num;
	private int seat_num;
	private boolean is_expired;

	public Ticket() {
	}

	public Ticket(Long traveler_id, Long first_stop, Long last_stop, Long train_id, int wagon_num, int seat_num) {
		this.traveler_id = traveler_id;
		this.first_stop = first_stop;
		this.last_stop = last_stop;
		this.train_id = train_id;
		this.wagon_num = wagon_num;
		this.seat_num = seat_num;
		this.is_expired = false;
	}

	// getters and setters
	public Long getTicketID() {
		return ticket_id;
	}

	public void setTicketID(Long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Long getTravelerID() {
		return traveler_id;
	}

	public void setTravelerID(Long traveler_id) {
		this.traveler_id = traveler_id;
	}

	public Long getFirstStop() {
		return first_stop;
	}

	public void setFirstStop(Long first_stop) {
		this.first_stop = first_stop;
	}

	public Long getLastStop() {
		return last_stop;
	}

	public void setLastStop(Long last_stop) {
		this.last_stop = last_stop;
	}

	public Long getTrainID() {
		return train_id;
	}

	public void setTrainID(Long train_id) {
		this.train_id = train_id;
	}

	public int getWagonNum() {
		return wagon_num;
	}

	public void setWagonNum(int wagon_num) {
		this.wagon_num = wagon_num;
	}

	public int getSeatNum() {
		return seat_num;
	}

	public void setSeatNum(int seat_num) {
		this.seat_num = seat_num;
	}

	public boolean getIsExpired() {
		return is_expired;
	}

	public void setIsExpired(boolean is_expired) {
		this.is_expired = is_expired;
	}
}
