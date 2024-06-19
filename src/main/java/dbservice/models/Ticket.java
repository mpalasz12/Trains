package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Ticket {
	private int ticket_id;
	private int traveler_id;
	private int first_stop;
	private int last_stop;
	private int train_id;
	private int wagon_num;
	private int seat_num;
	private boolean is_expired;

	public Ticket(int traveler_id, int first_stop, int last_stop, int train_id, int wagon_num, int seat_num) {
		this.traveler_id = traveler_id;
		this.first_stop = first_stop;
		this.last_stop = last_stop;
		this.train_id = train_id;
		this.wagon_num = wagon_num;
		this.seat_num = seat_num;
		this.is_expired = false;
	}

	// getters and setters
	public int getTicketID() {
		return ticket_id;
	}

	public void setTicketID(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getTravelerID() {
		return traveler_id;
	}

	public void setTravelerID(int traveler_id) {
		this.traveler_id = traveler_id;
	}

	public int getFirstStop() {
		return first_stop;
	}

	public void setFirstStop(int first_stop) {
		this.first_stop = first_stop;
	}

	public int getLastStop() {
		return last_stop;
	}

	public void setLastStop(int last_stop) {
		this.last_stop = last_stop;
	}

	public int getTrainID() {
		return train_id;
	}

	public void setTrainID(int train_id) {
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
