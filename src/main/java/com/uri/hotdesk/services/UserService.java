package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.HotDeskUser;
import com.uri.hotdesk.entities.Role;
import com.uri.hotdesk.entities.UserRole;
import com.uri.hotdesk.repositories.RoleRepository;
import com.uri.hotdesk.repositories.UserRepository;
import com.uri.hotdesk.repositories.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;
	private Logger logger;
	
	@Autowired
	public UserService(UserRepository userRepository,RoleRepository roleRepository,UserRoleRepository userRoleRepository
			) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.logger=LoggerFactory.getLogger(this.getClass());
	}
	
	@Transactional
	public HotDeskUser saveLdapUser(String mobileNumber, String deviceId) {
		logger.info("User Service: save user");
		Role role = roleRepository.findByRoleName("MOBILE_APP_USER");
		HotDeskUser newUser = new HotDeskUser();
		newUser.setMobileNumber(mobileNumber);
		newUser.setDeviceId(deviceId);
		newUser.setPassword("");
		newUser.setEnabled(true);
		newUser.setDefaultRole("MOBILE_APP_USER");
		newUser.setCreatedDate(new Date());

		HotDeskUser user = userRepository.save(newUser);

		UserRole userRole = new UserRole();
		userRole.setUsername(mobileNumber);
		userRole.setRole(role);
		userRole.setCurrent(true);

		userRoleRepository.save(userRole);

		return user;

	}
}
