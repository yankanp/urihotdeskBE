package com.uri.hotdesk.configurations;

import org.springframework.security.web.savedrequest.Enumerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;

public class OauthServletWrapper  extends HttpServletRequestWrapper{

	private final Map<String, String[]> params;
	public OauthServletWrapper(HttpServletRequest request, Map<String, String[]> params) {
		super(request);
		this.params = params;
	}
	
	@Override
	public String getParameter(String name) {
		if(this.params.containsKey(name)) {
			return this.params.get(name)[0];
		}
		else {
			return "";
		}
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return this.params;
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		return new Enumerator<>(params.keySet());
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return params.get(name);
	}

}
