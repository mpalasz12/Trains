package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Train {
	int lineID;
	int locomotiveID;
	int currentLinestop;

	public Train(int lineID, int locomotiveID)	{
		this.lineID = lineID;
		this.locomotiveID = locomotiveID;
	}

	// getters and setters
	public int getLineID() {
		return lineID;
	}

	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	public int getLocomotiveID() {
		return locomotiveID;
	}

	public void setLocomotiveID(int locomotiveID) {
		this.locomotiveID = locomotiveID;
	}

	public int getCurrentLinestop() {
		return currentLinestop;
	}

	public void setCurrentLinestop(int currentLinestop) {
		this.currentLinestop = currentLinestop;
	}
}
