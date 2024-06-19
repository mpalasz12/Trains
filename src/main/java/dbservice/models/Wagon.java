package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Wagon {
	int wagonID;
	int wagonNum;
	int wagonCapacity;
	int trainID;

	public Wagon(int wagonNum, int wagonCapacity, int trainID) {
		this.wagonNum = wagonNum;
		this.wagonCapacity = wagonCapacity;
		this.trainID = trainID;
	}

	public Wagon(int wagonNum) {
		this.wagonNum = wagonNum;
	}

	// getters and setters
	public int getWagonID() {
		return wagonID;
	}

	public void setWagonID(int wagonID) {
		this.wagonID = wagonID;
	}

	public int getWagonNum() {
		return wagonNum;
	}

	public void setWagonNum(int wagonNum) {
		this.wagonNum = wagonNum;
	}

	public int getWagonCapacity() {
		return wagonCapacity;
	}

	public void setWagonCapacity(int wagonCapacity) {
		this.wagonCapacity = wagonCapacity;
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

}
