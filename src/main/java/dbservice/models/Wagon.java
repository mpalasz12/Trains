package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Wagon {
	Integer wagon_id;
	Integer wagon_num;
	Integer wagon_capacity;
	Integer train_id;

	public Wagon() {
	}

	public Wagon(Integer wagon_num, Integer wagon_capacity, Integer train_id) {
		this.wagon_num = wagon_num;
		this.wagon_capacity = wagon_capacity;
		this.train_id = train_id;
	}

	public Integer getWagon_id() {
		return wagon_id;
	}

	public void setWagon_id(Integer wagon_id) {
		this.wagon_id = wagon_id;
	}

	public Integer getWagon_num() {
		return wagon_num;
	}

	public void setWagon_num(Integer wagon_num) {
		this.wagon_num = wagon_num;
	}

	public Integer getWagon_capacity() {
		return wagon_capacity;
	}

	public void setWagon_capacity(Integer wagon_capacity) {
		this.wagon_capacity = wagon_capacity;
	}

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}
}