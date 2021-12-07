package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.*;
import com.uri.hotdesk.exceptions.SeatAlreadyBookedException;
import com.uri.hotdesk.exceptions.SeatDoesNotBelongToTeamException;
import com.uri.hotdesk.repositories.*;
import com.uri.hotdesk.responses.GetAllBookingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeatBookingService {

	private Logger logger;
	private SeatBookingRepository seatBookingRepository;
	private TeamSeatRepository teamSeatRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private EmployeeTeamRepository employeeTeamRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	public SeatBookingService(SeatBookingRepository seatBookingRepository,
			TeamSeatRepository teamSeatRepository) {
		logger=LoggerFactory.getLogger(this.getClass());
		this.seatBookingRepository=seatBookingRepository;
		this.teamSeatRepository=teamSeatRepository;
	}
	
	public void bookseat(String clusterId, int seatNo,String empId,Date startDate,Date endDate,String username) throws SeatAlreadyBookedException, SeatDoesNotBelongToTeamException {
		logger.info("One");
		Team team=teamRepository.findById(clusterId).get();
		logger.info("TEAM {}",team);
		Location location=locationRepository.findById(team.getLocation().getLocationId()).get();
		logger.info("Location {}",location);
		Seat seat=seatRepository.findByLocationAndSeatNo(location,String.valueOf(seatNo));
		logger.info("Seat {}",seat);
		logger.info("Details {} {} {} {}", Integer.valueOf(seat.getSeatId()),clusterId,startDate,endDate);
		if(teamSeatRepository.findSeatAllocated(startDate, endDate, Integer.valueOf(seat.getSeatId()),clusterId).isEmpty()) {
			throw new SeatDoesNotBelongToTeamException();
		}
		logger.info("two");
		if(!seatBookingRepository.findSeatOverLap(startDate,endDate,seatNo).isEmpty()) {
			throw new SeatAlreadyBookedException();
		}
		logger.info("Three");
		SeatBooking seatBooking=new SeatBooking();
		logger.info("Four");
		//TODO get cluster by team and emp
		seatBooking.setEmployeeTeam(employeeTeamRepository.findByTeam(teamRepository.findById(clusterId).get()).get(0));
		seatBooking.setCreatedDate(new Date());
		logger.info("five");
		logger.info("emp {}",empId);
		logger.info("result {}",employeeRepository.findById(empId.split("_")[0]));
		seatBooking.setEmployee(employeeRepository.findById(empId.split("_")[0]).get());
		seatBooking.setEndDate(endDate);
		seatBooking.setModifiedBy(username);
		logger.info("six");
		seatBooking.setSeat(seatRepository.findById(seat.getSeatId()).get());
		seatBooking.setStartDate(startDate);
		logger.info("seven");
		seatBookingRepository.save(seatBooking);
	}
	
	public GetAllBookingResponse getAllBookings(String teamId, Date bookingDate) throws SeatDoesNotBelongToTeamException{
		logger.info("Two {} {}",teamId,bookingDate);
		List<TeamSeats> allSeatList=teamSeatRepository.findTodatBooking(bookingDate,teamId);
		logger.info("One {}",allSeatList);
		List<Integer> allSeatNos=new ArrayList<>();
		List<TeamSeats> seatsForGivenTeam=new ArrayList<>();
		for (TeamSeats teamSeats : allSeatList) {
			allSeatNos.add(Integer.valueOf(teamSeats.getSeat().getSeatNo()));
			TeamSeats myTeamSeat=new TeamSeats();
			myTeamSeat.setTeamSeatId(teamSeats.getTeamSeatId());
			myTeamSeat.setStartDate(teamSeats.getStartDate());
			myTeamSeat.setEndDate(teamSeats.getEndDate());
			myTeamSeat.setType(teamSeats.getType());
			myTeamSeat.setSeatId(Integer.valueOf(teamSeats.getSeat().getSeatNo()));
			myTeamSeat.setTeamId(teamSeats.getTeam().getTeamId());

			seatsForGivenTeam.add(myTeamSeat);
		}
		
		GetAllBookingResponse allBookingResponse=new GetAllBookingResponse();
		List<SeatBooking> allBookingsForTeam=seatBookingRepository.findTodatBooking(bookingDate, employeeTeamRepository.findByTeam(teamRepository.findById(teamId).get()).get(0).getId());
		List<SeatBooking> allBookingsForTeamResponse=new ArrayList<>();
		for (SeatBooking seatBooking:
			 allBookingsForTeam) {
			SeatBooking mySeatBooking=new SeatBooking();
			mySeatBooking.setSeatBookingId(seatBooking.getSeatBookingId());
			mySeatBooking.setSeatId(Integer.valueOf(seatBooking.getSeat().getSeatNo()));
			mySeatBooking.setClusterId(String.valueOf(seatBooking.getEmployeeTeam().getId()));
			mySeatBooking.setCreatedDate(seatBooking.getCreatedDate());
			mySeatBooking.setEmployeeId(seatBooking.getEmployee().getEmployeeId());
			mySeatBooking.setEndDate(seatBooking.getEndDate());
			mySeatBooking.setModifiedBy(seatBooking.getModifiedBy());
			mySeatBooking.setStartDate(seatBooking.getStartDate());
			allBookingsForTeamResponse.add(mySeatBooking);
		}
		allBookingResponse.setAllBookings(allBookingsForTeamResponse);
		allBookingResponse.setSeatNos(allSeatNos);
		allBookingResponse.setAvailableSeats(seatsForGivenTeam);
		allBookingResponse.setTotalSeatsforTeam(seatsForGivenTeam);
		logger.info("RESPONSE IS1 {}",allBookingResponse);
		return allBookingResponse;
	}
}
