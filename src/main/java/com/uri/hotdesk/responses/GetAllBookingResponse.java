package com.uri.hotdesk.responses;



import com.uri.hotdesk.entities.SeatBooking;
import com.uri.hotdesk.entities.TeamSeats;

import java.util.List;

public class GetAllBookingResponse {

	private List<Integer> seatNos;
	private List<SeatBooking> allBookings;
	private List<TeamSeats> availableSeats;
	private List<TeamSeats> totalSeatsforTeam;
	public List<Integer> getSeatNos() {
		return seatNos;
	}
	public void setSeatNos(List<Integer> seatNos) {
		this.seatNos = seatNos;
	}
	public List<SeatBooking> getAllBookings() {
		return allBookings;
	}
	public void setAllBookings(List<SeatBooking> allBookings) {
		this.allBookings = allBookings;
	}
	public List<TeamSeats> getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(List<TeamSeats> availableSeats) {
		this.availableSeats = availableSeats;
	}
	public List<TeamSeats> getTotalSeatsforTeam() {
		return totalSeatsforTeam;
	}
	public void setTotalSeatsforTeam(List<TeamSeats> totalSeatsforTeam) {
		this.totalSeatsforTeam = totalSeatsforTeam;
	}

	@Override
	public String toString() {
		return "GetAllBookingResponse{" +
				"seatNos=" + seatNos +
				", allBookings=" + allBookings +
				", availableSeats=" + availableSeats +
				", totalSeatsforTeam=" + totalSeatsforTeam +
				'}';
	}
}
