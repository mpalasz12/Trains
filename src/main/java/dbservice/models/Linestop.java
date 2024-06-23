package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Linestop {
	private Integer linestop_id;
	private Integer next_linestop;
	private Integer distance_next;
	private Integer station_id;

	public Linestop() {
	}

	public Linestop(Integer next_linestop, Integer distance_next, Integer station_id) {
		this.next_linestop = next_linestop;
		this.distance_next = distance_next;
		this.station_id = station_id;
	}

	public Linestop(Integer station_id) {
		this.station_id = station_id;
	}

	public Integer getLinestop_id() {
		return linestop_id;
	}

	public void setLinestop_id(Integer linestop_id) {
		this.linestop_id = linestop_id;
	}

	public Integer getNext_linestop() {
		return next_linestop;
	}

	public void setNext_linestop(Integer next_linestop) {
		this.next_linestop = next_linestop;
	}

	public Integer getDistance_next() {
		return distance_next;
	}

	public void setDistance_next(Integer distance_next) {
		this.distance_next = distance_next;
	}

	public Integer getStation_id() {
		return station_id;
	}

	public void setStation_id(Integer station_id) {
		this.station_id = station_id;
	}
}
