package dbservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Line {
	private Long line_id;
	private String name;
	private Long first_stop_id;

	public Line() {
	}

	public Line(String name, Long first_stop_id) {
		this.name = name;
		this.first_stop_id = first_stop_id;
	}

	public Line(Long id, String name, Long first_stop_id) {
		this.line_id = id;
		this.name = name;
		this.first_stop_id = first_stop_id;
	}

	public void setLine_id(Long id) {
		this.line_id = id;
	}

	public Long getLine_id() {
		return line_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFirst_stop_id(Long id) {
		this.first_stop_id = id;
	}

	public Long getFirst_stop_id() {
		return first_stop_id;
	}
}
