package com.uri.hotdesk.repositories;

import com.uri.hotdesk.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(String roleName);

}