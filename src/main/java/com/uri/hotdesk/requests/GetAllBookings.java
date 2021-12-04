package com.uri.hotdesk.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GetAllBookings {

	private String clusterId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date bookDate;
	public String getClusterId() {
		return clusterId;
	}
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	@Override
	public String toString() {
		return "GetAllBookings{" +
				"clusterId='" + clusterId + '\'' +
				", bookDate=" + bookDate +
				'}';
	}
}
