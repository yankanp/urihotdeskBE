package com.uri.hotdesk.Controllers;


import com.uri.hotdesk.requests.GetAllBookings;
import com.uri.hotdesk.requests.SeatBookingRequests;
import com.uri.hotdesk.responses.GetAllBookingResponse;
import com.uri.hotdesk.services.SeatBookingService;
import com.uri.hotdesk.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SeatBookingController {

	private Logger logger;
	private SeatBookingService seatBookingService;
	
	@Autowired
	public SeatBookingController(SeatBookingService seatBookingService) {
		logger=LoggerFactory.getLogger(this.getClass());
		this.seatBookingService=seatBookingService;
	}
	
	@PostMapping("/booking")
	public Map<String, Object> bookSeat(Principal principal,@RequestBody SeatBookingRequests seatBookingRequests){
		logger.info("SEAT_BOOKING_CONTROLLER {} {}",principal.getName(),seatBookingRequests);
		Map<String, Object> response = new HashMap<>();
		try {
			seatBookingService.bookseat(seatBookingRequests.getClusterId(), 
					seatBookingRequests.getSeatNo(), seatBookingRequests.getEmpId(), seatBookingRequests.getStartDate(), 
					seatBookingRequests.getEndDate(), principal.getName());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.MESSAGE_VALUE, "BOOKING_CREATED");
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/view/seats")
	public Map<String, Object> getAllBooking(Principal principal,@RequestBody GetAllBookings getAllBookings){
		logger.info("VERSION_CONTROLLER {} {}",principal.getName(),getAllBookings);
		Map<String, Object> response = new HashMap<>();
		try {
			GetAllBookingResponse allBookingResponse=seatBookingService.getAllBookings(getAllBookings.getClusterId(), getAllBookings.getBookDate());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE,allBookingResponse );
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, e.getMessage());
		}
		return response;
	}
}
