package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Line {
	private String name;
	private int firstStopID;

	public Line(String name, int id) {
		this.name = name;
		this.firstStopID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFirstStopID() {
		return firstStopID;
	}

	public void setFirstStopID(int id) {
		this.firstStopID = id;
	}
}
