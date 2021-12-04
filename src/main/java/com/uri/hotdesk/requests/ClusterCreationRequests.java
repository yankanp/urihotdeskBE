package com.uri.hotdesk.requests;

public class ClusterCreationRequests {

	private String clusterName;
	private int noOfTables;
	private int noOfHotSeats;
	private int noOfDedicatedSeats;
	private String location;
	private String hotSeatNumbers;
	private String dedicatedSeatNumbers;
	private String tableNumbers;
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public int getNoOfTables() {
		return noOfTables;
	}
	public void setNoOfTables(int noOfTables) {
		this.noOfTables = noOfTables;
	}
	public int getNoOfHotSeats() {
		return noOfHotSeats;
	}
	public void setNoOfHotSeats(int noOfHotSeats) {
		this.noOfHotSeats = noOfHotSeats;
	}
	public int getNoOfDedicatedSeats() {
		return noOfDedicatedSeats;
	}
	public void setNoOfDedicatedSeats(int noOfDedicatedSeats) {
		this.noOfDedicatedSeats = noOfDedicatedSeats;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHotSeatNumbers() {
		return hotSeatNumbers;
	}
	public void setHotSeatNumbers(String hotSeatNumbers) {
		this.hotSeatNumbers = hotSeatNumbers;
	}
	public String getDedicatedSeatNumbers() {
		return dedicatedSeatNumbers;
	}
	public void setDedicatedSeatNumbers(String dedicatedSeatNumbers) {
		this.dedicatedSeatNumbers = dedicatedSeatNumbers;
	}
	public String getTableNumbers() {
		return tableNumbers;
	}
	public void setTableNumbers(String tableNumbers) {
		this.tableNumbers = tableNumbers;
	}
}
