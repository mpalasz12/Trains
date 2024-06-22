package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Line {

	private Long line_id;
	private String name;
	private Long firstStopID;

	public Line() {
	}

	public Line(String name, Long firstStopID) {
		this.name = name;
		this.firstStopID = firstStopID;
	}

	public Line(Long id, String name, Long firstStopID) {
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

	public Long getFirstStopID() {
		return firstStopID;
	}

	public void setFirstStopID(Long id) {
		this.firstStopID = id;
	}

	public Long getID() {
		return line_id;
	}

	public void setID(Long id) {
		this.line_id = id;
	}
}
