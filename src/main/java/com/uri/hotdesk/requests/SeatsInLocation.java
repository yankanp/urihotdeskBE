package com.uri.hotdesk.requests;

public class SeatsInLocation {

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "SeatsInLocation{" +
				"location='" + location + '\'' +
				'}';
	}
}
