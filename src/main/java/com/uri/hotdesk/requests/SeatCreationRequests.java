package com.uri.hotdesk.requests;

public class SeatCreationRequests {

	private int seatId;
	private String location;
	private String description;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SeatCreationRequests{" +
				"seatId=" + seatId +
				", location='" + location + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
