package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Train {
	private int trainID;

	public Train() {
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}
}
