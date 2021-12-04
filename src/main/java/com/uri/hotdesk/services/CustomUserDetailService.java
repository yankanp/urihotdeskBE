package com.uri.hotdesk.services;


import com.uri.hotdesk.entities.HotDeskUser;
import com.uri.hotdesk.entities.Role;
import com.uri.hotdesk.entities.RoleAuthority;
import com.uri.hotdesk.exceptions.CurrentRoleNotFoundException;
import com.uri.hotdesk.repositories.RoleAuthorityRepository;
import com.uri.hotdesk.repositories.RoleRepository;
import com.uri.hotdesk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleAuthorityRepository roleAuthorityRepository;

	@Override
	public UserDetails loadUserByUsername(String mobileNumber) {
		HotDeskUser user = userRepository.findBymobileNumber(mobileNumber);
		return new User(mobileNumber, "", getGrantedAuthoritiesForUser(user));
	}

	
	private Set<GrantedAuthority> getGrantedAuthoritiesForUser(HotDeskUser user) {
		Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
		String defaultRole = user.getDefaultRole();
		if (defaultRole != null) {
			Role role = roleRepository.findByRoleName(defaultRole);
			if (role != null) {
				List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findAllByRole(role);
				for (RoleAuthority roleAuthority : roleAuthorities) {
					grantedAuthoritySet.add(new SimpleGrantedAuthority(roleAuthority.getAuthority()));
				}
				return grantedAuthoritySet;

			} else {
				throw new CurrentRoleNotFoundException();
			}

		} else {
			throw new CurrentRoleNotFoundException();
		}

	}
}

