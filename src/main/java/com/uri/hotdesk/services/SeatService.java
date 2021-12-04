package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.Location;
import com.uri.hotdesk.entities.Seat;
import com.uri.hotdesk.entities.SeatBooking;
import com.uri.hotdesk.repositories.LocationRepository;
import com.uri.hotdesk.repositories.SeatBookingRepository;
import com.uri.hotdesk.repositories.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

	private Logger logger;
	private SeatRepository seatRepository;
	private LocationRepository locationRepository;
	private SeatBookingRepository seatBookingRepository;
	
	@Autowired
	public SeatService(SeatRepository seatRepository,LocationRepository locationRepository,
			SeatBookingRepository seatBookingRepository) {
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.seatRepository=seatRepository;
		this.locationRepository=locationRepository;
		this.seatBookingRepository=seatBookingRepository;
	}
	
	public void addSeats(String mylocation,String description,int seatId) {
		Optional<Location> location=locationRepository.findById(mylocation);

		Seat seat=new Seat();
		seat.setCreatedDate(new Date());
		seat.setSeatNo(description);
		seat.setLocation(location.get());
		seatRepository.save(seat);
	}
	
	public List<Location> allLocationNames(){
		List<Location> locList= locationRepository.findAll();
		for (Location location:locList
			 ) {
			location.setTotSeatNumber(locationRepository.getSumOfSeatsinLocation(location.getLocationId()));
		}
		return locList;
	}
	
	public List<String> getAllAvailableSeatIds(Date startDate,Date endDate,String location) {
		List<String> seats=new ArrayList<>();
		List<Seat> allSeats=seatRepository.findAllByLocation(locationRepository.findById(location).get());
		List<SeatBooking> overlappingBookings;
//		for (Seat seat : allSeats) {
//			overlappingBookings= seatBookingRepository.findSeatOverLap(startDate, endDate, seat.getSeatId());
//		}
		//TODO Remove overlapping seats
		for (Seat seat : allSeats) {
			seats.add(String.valueOf(seat.getSeatNo()));
		}
		return seats;
	}
	
	public List<Integer> getAllSeatsForLocations(String location){
		List<Seat> allSeats=seatRepository.findAllByLocation(locationRepository.findById(location).get());
		List<Integer> seatNos=new ArrayList<>();
		for (Seat seat : allSeats) {
			seatNos.add(Integer.valueOf(seat.getSeatNo()));
		}
		return seatNos;
	}
}
