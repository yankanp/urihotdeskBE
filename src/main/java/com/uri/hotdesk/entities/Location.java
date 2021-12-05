package com.uri.hotdesk.entities;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String locationId;
	private String locationName;
	private String description;
	private String latitude;
	private String longitude;

	@Transient
	private int totSeatNumber;
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getTotSeatNumber() {
		return totSeatNumber;
	}

	public void setTotSeatNumber(int totSeatNumber) {
		this.totSeatNumber = totSeatNumber;
	}

	@Override
	public String toString() {
		return "Location{" +
				"locationId='" + locationId + '\'' +
				", locationName='" + locationName + '\'' +
				", description='" + description + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", totSeatNumber=" + totSeatNumber +
				'}';
	}
}
