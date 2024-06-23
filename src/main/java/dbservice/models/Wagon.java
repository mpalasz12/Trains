package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Wagon {
	Integer wagonID;
	int wagonNum;
	int wagonCapacity;
	Integer trainID;

	public Wagon(int wagonNum, int wagonCapacity, Integer trainID) {
		this.wagonNum = wagonNum;
		this.wagonCapacity = wagonCapacity;
		this.trainID = trainID;
	}

	public Wagon(int wagonNum) {
		this.wagonNum = wagonNum;
	}

	// getters and setters
	public Integer getWagonID() {
		return wagonID;
	}

	public void setWagonID(Integer wagonID) {
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

	public Integer getTrainID() {
		return trainID;
	}

	public void setTrainID(Integer trainID) {
		this.trainID = trainID;
	}

}
