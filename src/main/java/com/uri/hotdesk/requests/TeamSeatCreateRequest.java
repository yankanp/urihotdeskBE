package com.uri.hotdesk.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TeamSeatCreateRequest {

	private int seatId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	private String teamId;
	private String type;
	private String location;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
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
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "TeamSeatCreateRequest{" +
				"seatId=" + seatId +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", teamId='" + teamId + '\'' +
				", type='" + type + '\'' +
				", locationId='" + location + '\'' +
				'}';
	}
}
