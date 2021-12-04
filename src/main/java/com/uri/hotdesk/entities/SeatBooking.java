package com.uri.hotdesk.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seat_bookings")
public class SeatBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seatBookingId;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String modifiedBy;
	private Date createdDate;
	@ManyToOne
	private EmployeeTeam employeeTeam;
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private Seat seat;

	@Transient
	private String clusterId;
	@Transient
	private int seatId;
	@Transient
	private String employeeId;
	public Long getSeatBookingId() {
		return seatBookingId;
	}
	public void setSeatBookingId(Long seatBookingId) {
		this.seatBookingId = seatBookingId;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public EmployeeTeam getEmployeeTeam() {
		return employeeTeam;
	}

	public void setEmployeeTeam(EmployeeTeam employeeTeam) {
		this.employeeTeam = employeeTeam;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "SeatBooking{" +
				"seatBookingId=" + seatBookingId +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", modifiedBy='" + modifiedBy + '\'' +
				", createdDate=" + createdDate +
				", employeeTeam=" + employeeTeam +
				", employee=" + employee +
				", seat=" + seat +
				'}';
	}
}
