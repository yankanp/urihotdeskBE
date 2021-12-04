package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.Employee;
import com.uri.hotdesk.entities.EmployeeTeam;
import com.uri.hotdesk.entities.Team;
import com.uri.hotdesk.repositories.EmployeeRepository;
import com.uri.hotdesk.repositories.EmployeeTeamRepository;
import com.uri.hotdesk.repositories.TeamRepository;
import com.uri.hotdesk.responses.AllTeamsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeamService {

	private Logger logger;
	private TeamRepository teamRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeTeamRepository employeeTeamrepository;
	@Autowired
	public TeamService(TeamRepository teamRepository,EmployeeRepository employeeRepository,
			EmployeeTeamRepository employeeTeamRepository) {
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.teamRepository=teamRepository;
		this.employeeRepository=employeeRepository;
		this.employeeTeamrepository=employeeTeamRepository;
	}
	
	public void createTeam(String teamName,String username,String description) {
		Team teams=new Team();
		teams.setCreatedDate(new Date());
		teams.setDescription(description);
		teams.setModifiedBy(username);
		teams.setTeamId(teamName);
		teams.setTeamName(teamName);
		
		teamRepository.save(teams);
	}
	
	public void addEmployeeToTeam(String teamId,String adName,String role) {
		
		Employee employee=employeeRepository.findByAdminName(adName);
		Team team=teamRepository.findById(teamId).get();
		
		EmployeeTeam employeeTeam=new EmployeeTeam();
		employeeTeam.setCreatedDate(new Date());
		employeeTeam.setEmployee(employee);
		employeeTeam.setRole(role);
		employeeTeam.setTeam(team);
		
		employeeTeamrepository.save(employeeTeam);
	}
	
	public List<AllTeamsResponse> getAllTeams(){
		List<Team> allTeams= teamRepository.findAll();
		List<AllTeamsResponse> teamList=new ArrayList<>();
		for (Team team : allTeams) {
			AllTeamsResponse teamsResponse=new AllTeamsResponse();
			teamsResponse.setCreatedDate(team.getCreatedDate());
			teamsResponse.setDescription(team.getDescription());
			teamsResponse.setModifiedBy(team.getModifiedBy());
			teamsResponse.setTeamId(team.getTeamId());
			teamsResponse.setTeamName(team.getTeamName());
			
			teamList.add(teamsResponse);
		}
		return teamList;
	}
	
	public List<AllEmployeesResponse> getAllEmployeeTeam(){
		List<EmployeeTeam> allEmp=employeeTeamrepository.findAll();
		List<AllEmployeesResponse> allEmpList=new ArrayList<>();
		for (EmployeeTeam employeeTeam : allEmp) {
			AllEmployeesResponse response=new AllEmployeesResponse();
			response.setCreatedDate(employeeTeam.getCreatedDate());
			response.setEmployee(employeeTeam.getEmployee().getAdminName());
			response.setRole(employeeTeam.getRole());
			response.setTeam(employeeTeam.getTeam().getTeamName());
			
			allEmpList.add(response);
		}
		return allEmpList;
	}
}
