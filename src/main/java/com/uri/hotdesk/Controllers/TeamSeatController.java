package com.uri.hotdesk.Controllers;


import com.uri.hotdesk.exceptions.SeatAlreadyBookedException;
import com.uri.hotdesk.requests.AssignSeatsToTeamRequest;
import com.uri.hotdesk.requests.TeamSeatCreateRequest;
import com.uri.hotdesk.services.TeamSeatService;
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
public class TeamSeatController {

	private Logger logger;
	private TeamSeatService teamSeatService;
	
	@Autowired
	public TeamSeatController(TeamSeatService teamSeatService) {
		logger=LoggerFactory.getLogger(this.getClass());
		this.teamSeatService=teamSeatService;
	}
	
	@PostMapping("/team/seats")
	public Map<String, Object> createTeam(Principal principal,@RequestBody AssignSeatsToTeamRequest assignSeatsToTeamRequest){
		logger.info("ASSIGN_SEAT_TO_TEAMS {} {}",principal.getName(),assignSeatsToTeamRequest);
		Map<String, Object> response = new HashMap<>();
		try {
			for (TeamSeatCreateRequest seatCreateRequest : assignSeatsToTeamRequest.getTeamSeatCreateRequest()) {
				teamSeatService.addSeatsToTeam(seatCreateRequest.getSeatId(), seatCreateRequest.getStartDate(),
						seatCreateRequest.getEndDate(), seatCreateRequest.getType(), seatCreateRequest.getTeamId(),seatCreateRequest.getLocation());
			}
			
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.MESSAGE_VALUE, "TEAM_SEATS_CTEATED");
		} catch (SeatAlreadyBookedException e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, e.getMessage());
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "TEAM_SEATS_CREATION_FAILED");
		}
		return response;
	}
}
