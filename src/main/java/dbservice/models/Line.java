package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Line {

	private long line_id;
	private String name;
	private long firstStopID;

	public Line() {}

	public Line(String name, int firstStopID) {
		this.name = name;
		this.firstStopID = firstStopID;
	}

	public Line(long id, String name, long firstStopID) {
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

	public long getFirstStopID() {
		return firstStopID;
	}

	public void setFirstStopID(long id) {
		this.firstStopID = id;
	}

	public long getId() {return line_id;}

	public void setId(long id) {this.line_id = id;}
}
