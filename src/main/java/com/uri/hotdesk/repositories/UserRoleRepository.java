package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUsername(String username);

}