package com.coface.corp.translationView.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coface.corp.framework.management.Environment;
import com.coface.corp.framework.management.Environment.EnvironmentType;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {
	private static Log logger = LogFactory.getLog(CORSFilter.class);

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		// Only for DEV environment
		if (isDevelopmentServer()) {
			response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
			response.getHttpHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
			response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
			response.getHttpHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		}
		return response;
	}

	private boolean isDevelopmentServer() {
		EnvironmentType environmentType = null;
		try {
			environmentType = Environment.getEnvironmentType();
		} catch (Exception e) {
			logger.warn("Cannot check environment type.", e);
		}
		return EnvironmentType.DEV.equals(environmentType);
	}
}