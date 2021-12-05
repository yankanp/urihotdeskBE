package com.uri.hotdesk.Controllers;


import com.uri.hotdesk.requests.AssignEmployeeRequest;
import com.uri.hotdesk.requests.TeamCreationRequest;
import com.uri.hotdesk.responses.AllTeamsResponse;
import com.uri.hotdesk.services.AllEmployeesResponse;
import com.uri.hotdesk.services.TeamService;
import com.uri.hotdesk.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

	private Logger logger;
	private TeamService teamService;
	
	@Autowired
	public TeamController(TeamService teamService) {
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.teamService=teamService;
	}
	
	@GetMapping("/user")
	public Principal getUser(Principal principal) {
		logger.info("PRINCIPAL {} {}",principal,principal.getName());
		return principal;
	}

	
	@PostMapping("/team")
	public Map<String, Object> createTeam(Principal principal,@RequestBody TeamCreationRequest teamCreationRequest){
		logger.info("VERSION_CONTROLLER {} {}",principal.getName(),teamCreationRequest);
		Map<String, Object> response = new HashMap<>();
		try {
			teamService.createTeam(teamCreationRequest.getTeamId(), principal.getName(), teamCreationRequest.getDescription(),teamCreationRequest.getLocation());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.MESSAGE_VALUE, "TEAM_CREATED");
		} catch (Exception e) {
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "TEAM_CREATION_FAILED");
		}
		return response;
	}
	
	@PostMapping("/assign")
	public Map<String, Object> addUserToTeam(Principal principal,@RequestBody AssignEmployeeRequest assignEmployeeRequest){
		logger.info("VERSION_CONTROLLER {}",principal.getName());
		Map<String, Object> response = new HashMap<>();
		try {
			teamService.addEmployeeToTeam(assignEmployeeRequest.getTeamId(), assignEmployeeRequest.getAdName(), assignEmployeeRequest.getRole());
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.MESSAGE_VALUE, "USER_ASSIGNED");
		} catch (Exception e) {
			logger.error("ASSIGN {}",e.getMessage());
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "USER_ASSIGN_FAILED");
		}
		return response;
	}
	
	@GetMapping("/teams/name")
	public Map<String, Object> getAllTeamsName(){
		logger.info("GET_ALL_TEAMS ");
		Map<String, Object> response = new HashMap<>();
		try {
			List<String> teamNames=new ArrayList<>();
			List<AllTeamsResponse> teamList=teamService.getAllTeams();
			for (AllTeamsResponse team : teamList) {
				teamNames.add(team.getTeamName());
			}
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, teamNames);
		} catch (Exception e) {
			logger.error("ASSIGN {}",e.getMessage());
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "GET_TEAM_FAILED");
		}
		return response;
	}
	
	@GetMapping("/teams")
	public Map<String, Object> getAllteams(){
		logger.info("GET_ALL_TEAMS ");
		Map<String, Object> response = new HashMap<>();
		try {
			List<AllTeamsResponse> teamList=teamService.getAllTeams();
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, teamList);
		} catch (Exception e) {
			logger.error("ASSIGN {}",e.getMessage());
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "GET_TEAM_FAILED");
		}
		return response;
	}
	
	@GetMapping("/teams/employees")
	public Map<String, Object> getAllEmployees(){
		logger.info("GET_ALL_EMPLOYEE ");
		Map<String, Object> response = new HashMap<>();
		try {
			List<AllEmployeesResponse> allEmpTeamList=teamService.getAllEmployeeTeam();
			response.put(Util.STATUS_VALUE, Util.STATUS_SUCCESS);
			response.put(Util.DATA_VALUE, allEmpTeamList);
		} catch (Exception e) {
			logger.error("ASSIGN {}",e.getMessage());
			response.put(Util.STATUS_VALUE, Util.STATUS_FAILED);
			response.put(Util.MESSAGE_VALUE, "GET_TEAM_FAILED");
		}
		return response;
	}
}
