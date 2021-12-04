package com.uri.hotdesk.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seats")
public class Seat {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seats_seq_gen")
	@SequenceGenerator(name="seats_seq_gen", sequenceName="seats_seq_gen")
	private int seatId;
	private String seatNo;
	private Date createdDate;
	@ManyToOne
	private Location location;
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
