package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Role;
import com.uri.hotdesk.entities.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Integer> {
	List<RoleAuthority> findAllByRole(Role role);
}