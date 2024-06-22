package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Linestop {
	private Long id;
	private Long nextLinestop;
	private int nextDistance;
	private Long stationID;

	public Linestop() {
	}

	public Linestop(Long nextLinestop, int nextDistance, Long stationID) {
		this.nextLinestop = nextLinestop;
		this.nextDistance = nextDistance;
		this.stationID = stationID;
	}

	public Linestop(Long stationID) {
		this.stationID = stationID;
	}

	public void setNextStop(Long id) {
		this.nextLinestop = id;
	}

	public Long getNextStop() {
		return nextLinestop;
	}

	public void setNextDistance(int value) {
		this.nextDistance = value;
	}

	public int getNextDistance() {
		return nextDistance;
	}

	public void setStationID(Long id) {
		this.stationID = id;
	}

	public Long getStationID() {
		return stationID;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public Long getID() {
		return id;
	}

}
