package com.uri.hotdesk.configurations;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

@Component
@Order(value = Integer.MIN_VALUE)
public class OauthFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if(Objects.equals(req.getContentType(), "application/json") && Objects.equals(((HttpServletRequest) req).getServletPath(),"/oauth/token")) {
			InputStream is = req.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			int nRead;
			byte[] data = new byte[16384];
			
			while((nRead = is.read(data,0,data.length)) !=-1) {
				buffer.write(data,0,nRead);
			}
			
			buffer.flush();
			byte[] json = buffer.toByteArray();
			
			@SuppressWarnings("unchecked")
			HashMap<String, String> result = new ObjectMapper().readValue(json, HashMap.class);
			HashMap<String, String[]> r = new HashMap<>();
			
			result.keySet().forEach(key->{
				String[] val = new String[1];
				val[0] = result.get(key);
				r.put(key, val);
			});
	
			String[] val = new String[1];
			val[0] = ((HttpServletRequest) req).getMethod();
			r.put("_method",val);
			HttpServletRequest s = new OauthServletWrapper(((HttpServletRequest) req),r);
			
			chain.doFilter(s, res);
		}
		else {
			chain.doFilter(req, res);
		}		
	}
	
	@Override
	public void destroy() {
		//
	}

}
