package dbservice.models;

import jakarta.persistence.Entity;

@Entity
public class Traveler {
	private Long traveler_id;
	private String firstName;
	private String lastName;
	private String mailAddress;

	public Traveler() {
	}

	public Traveler(String firstName, String lastName, String mail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mail;
	}

	public Long getTravelerID() {
		return traveler_id;
	}

	public void setTravelerID(Long traveler_id) {
		this.traveler_id = traveler_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}
