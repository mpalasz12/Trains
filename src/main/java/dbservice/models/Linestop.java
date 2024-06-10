package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Linestop {
	private int id;
	private int nextLinestop;
	private int nextDistance;
	private int stationID;

	public Linestop() {}

	public void setNextStop(int id) {
		this.nextLinestop = id;
	}

	public int getNextstop() {
		return nextLinestop;
	}

	public void setNextDistance(int value) {
		this.nextDistance = value;
	}

	public int getNextDistance() {
		return nextDistance;
	}

	public void setStationID(int id) {
		this.stationID = id;
	}

	public int getStationID() {
		return stationID;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

}
