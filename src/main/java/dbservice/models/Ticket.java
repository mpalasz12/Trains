package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Ticket {
	private Integer ticket_id;
	private Integer traveler_id;
	private Integer first_stop;
	private Integer last_stop;
	private Integer train_id;
	private int wagon_num;
	private int seat_num;
	private boolean is_expired;

	public Ticket() {
	}

	public Ticket(Integer traveler_id, Integer first_stop, Integer last_stop, Integer train_id, int wagon_num, int seat_num) {
		this.traveler_id = traveler_id;
		this.first_stop = first_stop;
		this.last_stop = last_stop;
		this.train_id = train_id;
		this.wagon_num = wagon_num;
		this.seat_num = seat_num;
		this.is_expired = false;
	}

	// getters and setters
	public Integer getTicketID() {
		return ticket_id;
	}

	public void setTicketID(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Integer getTravelerID() {
		return traveler_id;
	}

	public void setTravelerID(Integer traveler_id) {
		this.traveler_id = traveler_id;
	}

	public Integer getFirstStop() {
		return first_stop;
	}

	public void setFirstStop(Integer first_stop) {
		this.first_stop = first_stop;
	}

	public Integer getLastStop() {
		return last_stop;
	}

	public void setLastStop(Integer last_stop) {
		this.last_stop = last_stop;
	}

	public Integer getTrainID() {
		return train_id;
	}

	public void setTrainID(Integer train_id) {
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
