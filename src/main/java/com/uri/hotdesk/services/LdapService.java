package com.uri.hotdesk.services;

import com.uri.hotdesk.responses.AdminUserRetrieveResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LdapService {

	private LdapTemplate ldapTemplate;
	private Logger logger;

	@Autowired
	public LdapService(LdapTemplate ldapTemplate, LdapContextSource contextSource) {
		this.ldapTemplate=ldapTemplate;
		this.logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public AdminUserRetrieveResponse searchByUsername(String username) {
		logger.info("SEARCHING USER IN LDAP {}", username);
		Optional<AdminUserRetrieveResponse> optionalUser = ldapTemplate.search("", "samaccountname=" + username, (AttributesMapper<AdminUserRetrieveResponse>) attributes -> {
			AdminUserRetrieveResponse adminUser = new AdminUserRetrieveResponse();
			adminUser.setFullName((String) attributes.get("cn").get());
			adminUser.setEmail((String) attributes.get("mail").get());
			return adminUser;
		}).stream().findFirst();

		if(optionalUser.isPresent())
			return optionalUser.get();
		else
			throw new UsernameNotFoundException("USERNAME_NOT_FOUND");
	}
	
	public boolean validateUsername(String username, String password) {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("samaccountname", username));

		return ldapTemplate.authenticate("", filter.toString(), password);
	}
}
