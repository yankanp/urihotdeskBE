package com.uri.hotdesk.Controllers;


import com.uri.hotdesk.requests.AvailableSeatRequest;
import com.uri.hotdesk.requests.SeatCreationRequests;
import com.uri.hotdesk.requests.SeatsInLocation;
import com.uri.hotdesk.responses.AllSeatNumbersResponse;
import com.uri.hotdesk.services.SeatService;
import com.uri.hotdesk.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SeatController {

	private Logger logger;
	private SeatService seatService;
	
	@Autowired
	public SeatController(SeatService seatService) {
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.seatService=seatService;
	}
	
	@PostMapping("/seat")
	public Map<String, Object> createTeam(Principal principal,@RequestBody SeatCreationRequests seatCreationRequests){
		logger.info("VERSION_CONTROLLER {} {}",principal.getName(),seatCreationRequests);
		Map<String, Object> response = new HashMap<>();
		try {
			seatService.addSeats(seatCreationRequests.getLocation(), seatCreationRequests.getDescription(), seatCreationRequests.getSeatId());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.MESSAGE_VALUE, "SEAT_CREATED");
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "SEAT_CREATION_FAILED");
		}
		return response;
	}
	
	@GetMapping("/locations")
	public Map<String, Object> getAllLocations(){
		logger.info("VERSION_CONTROLLER");
		Map<String, Object> response = new HashMap<>();
		try {
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, seatService.allLocationNames());
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "SEAT_CREATION_FAILED");
		}
		return response;
	}
	
	@PostMapping("/seats/available")
	public Map<String, Object> getAllAvailableSeats(Principal principal,@RequestBody AvailableSeatRequest availableSeatRequest){
		logger.info("GET_ALL_AVAILABLE_SEATS_CONTROLLER {} {}",principal.getName(),availableSeatRequest);
		Map<String, Object> response = new HashMap<>();
		try {
			//List<Integer> seats= seatService.getAllAvailableSeatIds(availableSeatRequest.getStartDate(),availableSeatRequest.getEndDate(),availableSeatRequest.getLocation());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, seatService.getAllAvailableSeatIds(availableSeatRequest.getStartDate(),availableSeatRequest.getEndDate(),availableSeatRequest.getLocation()));
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "SEAT_CREATION_FAILED");
		}
		return response;
	}
	
	@PostMapping("/seat/location")
	public Map<String, Object> seatsAvailableInLocation(Principal principal,@RequestBody SeatsInLocation seatsInLocation){
		logger.info("GET_ALL_AVAILABLE_SEATS_IN_LOCATION_CONTROLLER {} {}",principal.getName(),seatsInLocation);
		Map<String, Object> response = new HashMap<>();
		try {
			//List<Integer> seats= seatService.getAllAvailableSeatIds(availableSeatRequest.getStartDate(),availableSeatRequest.getEndDate(),availableSeatRequest.getLocation());
			AllSeatNumbersResponse seatNumbersResponse=new AllSeatNumbersResponse();
			seatNumbersResponse.setSeatNos(seatService.getAllSeatsForLocations(seatsInLocation.getLocation()));
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, seatNumbersResponse);
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "SEAT_CREATION_FAILED");
		}
		return response;
	}
}
