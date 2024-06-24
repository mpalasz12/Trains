package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Traveler {
	private Integer traveler_id;
	private String first_name;
	private String last_name;
	private String mail_address;

	public Traveler() {
	}

	public Traveler(String first_name, String last_name, String mail_address) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail_address = mail_address;
	}

	public Integer getTraveler_id() {
		return traveler_id;
	}

	public void setTraveler_id(Integer traveler_id) {
		this.traveler_id = traveler_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
}
