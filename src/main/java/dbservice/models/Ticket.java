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

	public Ticket(Integer ticket_id, Integer traveler_id, Integer first_stop, Integer last_stop, Integer train_id, int wagon_num, int seat_num, boolean is_expired) {
		this.ticket_id = ticket_id;
		this.traveler_id = traveler_id;
		this.first_stop = first_stop;
		this.last_stop = last_stop;
		this.train_id = train_id;
		this.wagon_num = wagon_num;
		this.seat_num = seat_num;
		this.is_expired = is_expired;
	}

	public Integer getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Integer getTraveler_id() {
		return traveler_id;
	}

	public void setTraveler_id(Integer traveler_id) {
		this.traveler_id = traveler_id;
	}

	public Integer getFirst_stop() {
		return first_stop;
	}

	public void setFirst_stop(Integer first_stop) {
		this.first_stop = first_stop;
	}

	public Integer getLast_stop() {
		return last_stop;
	}

	public void setLast_stop(Integer last_stop) {
		this.last_stop = last_stop;
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public int getWagon_num() {
		return wagon_num;
	}

	public void setWagon_num(int wagon_num) {
		this.wagon_num = wagon_num;
	}

	public int getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}

	public boolean isExpired() {
		return is_expired;
	}

	public void setExpired(boolean is_expired) {
		this.is_expired = is_expired;
	}
}
