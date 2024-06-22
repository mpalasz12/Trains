package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Wagon {
	Long wagonID;
	int wagonNum;
	int wagonCapacity;
	Long trainID;

	public Wagon(int wagonNum, int wagonCapacity, Long trainID) {
		this.wagonNum = wagonNum;
		this.wagonCapacity = wagonCapacity;
		this.trainID = trainID;
	}

	public Wagon(int wagonNum) {
		this.wagonNum = wagonNum;
	}

	// getters and setters
	public Long getWagonID() {
		return wagonID;
	}

	public void setWagonID(Long wagonID) {
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

	public Long getTrainID() {
		return trainID;
	}

	public void setTrainID(Long trainID) {
		this.trainID = trainID;
	}

}
