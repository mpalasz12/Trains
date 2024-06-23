package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Linestop {
	private Integer id;
	private Integer nextLinestop;
	private int nextDistance;
	private Integer stationID;

	public Linestop() {
	}

	public Linestop(Integer nextLinestop, int nextDistance, Integer stationID) {
		this.nextLinestop = nextLinestop;
		this.nextDistance = nextDistance;
		this.stationID = stationID;
	}

	public Linestop(Integer stationID) {
		this.stationID = stationID;
	}

	public void setNextStop(Integer id) {
		this.nextLinestop = id;
	}

	public Integer getNextStop() {
		return nextLinestop;
	}

	public void setNextDistance(int value) {
		this.nextDistance = value;
	}

	public int getNextDistance() {
		return nextDistance;
	}

	public void setStationID(Integer id) {
		this.stationID = id;
	}

	public Integer getStationID() {
		return stationID;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public Integer getID() {
		return id;
	}

}
