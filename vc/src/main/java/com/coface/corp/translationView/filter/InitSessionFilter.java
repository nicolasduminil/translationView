package com.coface.corp.translationView.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coface.corp.framework.sso.SSOClientHelper;
import com.coface.corp.translationView.UserSession;

public class InitSessionFilter implements Filter {

	public static final String MY_USER_SESSION = "MY_USER_SESSION";
	private static Log logger = LogFactory.getLog(InitSessionFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		if (SSOClientHelper.isFilterApplied(request)) {
			UserSession userSession = (UserSession) httpSession.getAttribute("MY_USER_SESSION");
			if (userSession==null){
				userSession = new UserSession();
				userSession.init(httpRequest, httpResponse);
				httpSession.setAttribute(MY_USER_SESSION, userSession);
			} else {
				logger.debug("Session already initialize for User : "+userSession.getAccountId());
			}
		}

		try {
			filterChain.doFilter(request, response);
		} catch (IOException e) {
			logAndThrow(e);
		} catch (ServletException e) {
			logAndThrow(e);
		}
	}

	private void logAndThrow(Exception e) {
		logger.error(e.getMessage());
		throw new RuntimeException(e);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}