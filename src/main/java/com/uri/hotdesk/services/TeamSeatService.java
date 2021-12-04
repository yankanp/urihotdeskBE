package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.Seat;
import com.uri.hotdesk.entities.Team;
import com.uri.hotdesk.entities.TeamSeats;
import com.uri.hotdesk.exceptions.SeatAlreadyBookedException;
import com.uri.hotdesk.repositories.LocationRepository;
import com.uri.hotdesk.repositories.SeatRepository;
import com.uri.hotdesk.repositories.TeamRepository;
import com.uri.hotdesk.repositories.TeamSeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TeamSeatService {

	private Logger logger;
	private TeamSeatRepository teamSeatRepository;
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	public TeamSeatService(TeamSeatRepository teamSeatRepository) {
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.teamSeatRepository=teamSeatRepository;
	}
	
	public void addSeatsToTeam(int seatId, Date bookingStartDate,Date bookingEndDate,String type,String teamId,String locationId) throws SeatAlreadyBookedException {
		
//		if(!teamSeatRepository.findSeatOverLap(bookingStartDate, bookingEndDate, seatId).isEmpty()) {
//			throw new SeatAlreadyBookedException();
//		}
		logger.info("seatID {} bookinStartDate {} endDate {} type {} teamId {}",seatId,bookingStartDate,bookingEndDate,type,teamId);
		TeamSeats teamSeats=new TeamSeats();
		Optional<Team> team=teamRepository.findById(teamId);
		Seat seat=seatRepository.findByLocationAndSeatNo(locationRepository.findById(locationId).get(),String.valueOf(seatId));

		teamSeats.setEndDate(bookingEndDate);
		teamSeats.setStartDate(bookingStartDate);
		teamSeats.setTeam(team.get());
		teamSeats.setType(type);
		teamSeats.setSeat(seat);
		
		teamSeatRepository.save(teamSeats);
	}
}
