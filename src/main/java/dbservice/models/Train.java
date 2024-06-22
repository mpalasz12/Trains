package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Train {
	Integer train_id;
	Integer line_id;
	Integer locomotive_id;
	Integer curr_linestop;

	public Train() {
	}

	public Train(Integer line_id, Integer locomotive_id) {
		this.line_id = line_id;
		this.locomotive_id = locomotive_id;
	}

	public Train(Integer train_id, Integer line_id, Integer locomotive_id, Integer curr_linestop) {
		this.train_id = train_id;
		this.line_id = line_id;
		this.locomotive_id = locomotive_id;
		this.curr_linestop = curr_linestop;
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public Integer getLocomotive_id() {
		return locomotive_id;
	}

	public void setLocomotive_id(Integer locomotive_id) {
		this.locomotive_id = locomotive_id;
	}

	public Integer getCurr_linestop() {
		return curr_linestop;
	}

	public void setCurr_linestop(Integer curr_linestop) {
		this.curr_linestop = curr_linestop;
	}
}
