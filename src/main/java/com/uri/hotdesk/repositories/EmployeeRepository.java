package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	Employee findByAdminName(String adminName);
}
