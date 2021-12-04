package com.uri.hotdesk.configurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.DefaultDirObjectFactory;
import org.springframework.ldap.core.support.LdapContextSource;

import javax.naming.directory.SearchControls;

@Configuration
@EnableAutoConfiguration
public class LdapConfiguration {

	 @Bean
	 public LdapContextSource contextSource() {
		 LdapContextSource contextSource = new LdapContextSource();
		 contextSource.setUrl("ldap://172.26.33.241:389/");
	     contextSource.setBase("dc=dialog,dc=dialoggsm,dc=com");
	     contextSource.setUserDn(
	                "CN=POSAppUsr,OU=Application Admin Accounts,OU=Common Accounts,DC=dialog,DC=dialoggsm,DC=com");
	     contextSource.setPassword("Ken#23@78p0$");
	     contextSource.setDirObjectFactory(DefaultDirObjectFactory.class);
	     contextSource.afterPropertiesSet();
	     return contextSource;
	 }

	 @Bean
	 public LdapTemplate ldapTemplate() {
		 LdapTemplate ldapTemplate =  new LdapTemplate(contextSource());
		 ldapTemplate.setDefaultSearchScope(SearchControls.SUBTREE_SCOPE);
	     ldapTemplate.setIgnorePartialResultException(true);
	     return ldapTemplate;
	 }
}
