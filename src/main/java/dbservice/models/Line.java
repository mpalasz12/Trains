package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Line {

	private int line_id;
	private String name;
	private int firstStopID;

	public Line() {}

	public Line(String name, int firstStopID) {
		this.name = name;
		this.firstStopID = firstStopID;
	}

	public Line(int id, String name, int firstStopID) {
		this.line_id = id;
		this.name = name;
		this.firstStopID = firstStopID;
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

	public int getId() {return line_id;}

	public void setId(int id) {this.line_id = id;}
}
