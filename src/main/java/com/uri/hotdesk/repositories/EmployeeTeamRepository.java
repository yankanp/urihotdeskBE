package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Employee;
import com.uri.hotdesk.entities.EmployeeTeam;
import com.uri.hotdesk.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTeamRepository extends JpaRepository<EmployeeTeam, Long>{

	EmployeeTeam findByEmployeeAndTeam(Employee employee, Team team);
	List<EmployeeTeam> findByTeam(Team team);
}
