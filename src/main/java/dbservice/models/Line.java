package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Line {
	private Integer line_id;
	private String name;
	private Integer first_stop_id;

	public Line() {
	}

	public Line(String name, Integer first_stop_id) {
		this.name = name;
		this.first_stop_id = first_stop_id;
	}

	public Line(Integer id, String name, Integer first_stop_id) {
		this.line_id = id;
		this.name = name;
		this.first_stop_id = first_stop_id;
	}

	public void setLine_id(Integer id) {
		this.line_id = id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFirst_stop_id(Integer id) {
		this.first_stop_id = id;
	}

	public Integer getFirst_stop_id() {
		return first_stop_id;
	}
}
