package com.uri.hotdesk.configurations;

import com.uri.hotdesk.entities.*;
import com.uri.hotdesk.exceptions.CurrentRoleNotFoundException;
import com.uri.hotdesk.repositories.*;
import com.uri.hotdesk.services.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class CustomAuthManager implements AuthenticationManager {
	
	private Logger logger;
	private RoleRepository roleRepository;
	private RoleAuthorityRepository roleAuthorityRepository;
	private EmployeeRepository adminRepository;
	private LdapService ldapService;
	private EmployeeTeamRepository employeeTeamrepository;
	private EmployeeRepository employeeRepository;
	private TeamRepository teamRepository;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public CustomAuthManager(RoleRepository roleRepository,
			RoleAuthorityRepository roleAuthorityRepository,EmployeeRepository adminRepository,
			LdapService ldapService,EmployeeTeamRepository employeeTeamRepository,
			EmployeeRepository employeeRepository,TeamRepository teamRepository) {
		this.roleRepository = roleRepository;
		this.roleAuthorityRepository = roleAuthorityRepository;
		this.logger=LoggerFactory.getLogger(this.getClass());
		this.adminRepository=adminRepository;
		this.ldapService=ldapService;
		this.employeeTeamrepository=employeeTeamRepository;
		this.employeeRepository=employeeRepository;
		this.teamRepository=teamRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String authType = request.getParameter("authType");
		validateAuthType(authType);
				
		logger.info("AUTH_TYPE {}",authType);
		if (authType.equals("ldap")) {
			return ldapAuthentication(request);
		}
		if (authType.equals("employee")) {
			return employeeAuthentication(request);
		}

		throw new BadCredentialsException("AUTHENTICATION_FAILED");
	}

	private Authentication ldapAuthentication(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(password==null||password.isEmpty())
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		username=username.toLowerCase();
		//boolean authenticated=ldapService.validateUsername(username, password);
		boolean authenticated=true;
		if(!authenticated)
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		Employee admin = checkAdmin(username);
		
		if(admin!=null) {
			if(!admin.getEnabled()) {
				throw new BadCredentialsException("inactive_user");
			}
			return fromAdminUserDetails(admin, admin.getEmployeeId());
		}
		else {
			throw new BadCredentialsException("AUTHENTICATION_FAILED");
		}
	}
	
	
	private Authentication employeeAuthentication(HttpServletRequest request) {
		String username = request.getParameter("username");
		String team = request.getParameter("team");
		String password = request.getParameter("password");
		if(password==null||password.isEmpty())
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		username=username.toLowerCase();
		//boolean authenticated=ldapService.validateUsername(username, password);
		boolean authenticated=true;
		if(!authenticated)
			throw new BadCredentialsException("INVALID_CREDENTIALS");
		EmployeeTeam employee = checkEmployee(username,team);
		
		if(employee!=null) {
//			if(!admin.getEnabled()) {
//				throw new BadCredentialsException("inactive_user");
//			}
			logger.info("INSIDE EMPLOYEE NOT NULL");
			return fromEmployeeUserDetails(employee, employee.getEmployee().getEmployeeId());
		}
		else {
			throw new BadCredentialsException("AUTHENTICATION_FAILED");
		}
	}
	
	
	private void validateAuthType(String authType) {
		if (authType == null) {
			throw new BadCredentialsException("INVALID_AUTH_TYPE");
		}
	}

	private Employee checkAdmin(String adminName) {
		return adminRepository.findByAdminName(adminName);
	}
	
	
	private EmployeeTeam checkEmployee(String adName,String teamId) {
		logger.info("AD_NAME {} TEAMID {}",adName,teamId);
		Employee employee=employeeRepository.findByAdminName(adName);
		logger.info("EMPLOYEE ID {}",employee.getEmployeeId());
		Team team=teamRepository.findById(teamId).get();
		logger.info("TEAM NAME {}",team.getTeamName());
		return employeeTeamrepository.findByEmployeeAndTeam(employee, team);
	}
	
	private UsernamePasswordAuthenticationToken fromAdminUserDetails(Employee admin, String userId) {
		String defaultRole = admin.getDefaultRole();
		if (defaultRole != null) {
			Role role = roleRepository.findByRoleName(defaultRole);
			if (role != null) {
				List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findAllByRole(role);
				Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
				for (RoleAuthority roleAuthority : roleAuthorities) {
					grantedAuthoritySet.add(new SimpleGrantedAuthority(roleAuthority.getAuthority()));
				}
				if(userId!=null){
					grantedAuthoritySet.add(new SimpleGrantedAuthority("USER_ID"+userId));
				}
				return new UsernamePasswordAuthenticationToken(admin.getEmployeeId(), admin.getEmail() ,grantedAuthoritySet);
			} else {
				throw new CurrentRoleNotFoundException();
			}
		} else {
			throw new CurrentRoleNotFoundException();
		}
	}

	private UsernamePasswordAuthenticationToken fromEmployeeUserDetails(EmployeeTeam admin, String userId) {
		String defaultRole = admin.getRole();
		if (defaultRole != null) {
			Role role = roleRepository.findByRoleName(defaultRole);
			if (role != null) {
				logger.info("ROLE NOT NULL");
				List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findAllByRole(role);
				Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
				for (RoleAuthority roleAuthority : roleAuthorities) {
					grantedAuthoritySet.add(new SimpleGrantedAuthority(roleAuthority.getAuthority()));
				}
				if(userId!=null){
					grantedAuthoritySet.add(new SimpleGrantedAuthority("USER_ID"+userId));
				}
				return new UsernamePasswordAuthenticationToken(admin.getEmployee().getEmployeeId().concat("_").concat(admin.getTeam().getTeamId()), admin.getTeam().getTeamId() ,grantedAuthoritySet);
			} else {
				throw new CurrentRoleNotFoundException();
			}
		} else {
			throw new CurrentRoleNotFoundException();
		}
	}
}

