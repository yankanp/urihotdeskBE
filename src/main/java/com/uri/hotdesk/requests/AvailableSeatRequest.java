package com.uri.hotdesk.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AvailableSeatRequest {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	private String location;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AvailableSeatRequest{" +
				"startDate=" + startDate +
				", endDate=" + endDate +
				", location='" + location + '\'' +
				'}';
	}
}
