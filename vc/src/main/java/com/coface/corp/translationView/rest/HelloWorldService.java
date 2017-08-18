package com.coface.corp.translationView.rest;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.coface.corp.base.bdt.entitlement.AccountBDT;
import com.coface.corp.framework.configuration.ManagedProperties;
import com.coface.corp.framework.configuration.PropertiesFactory;
import com.coface.corp.framework.sso.SSOClientHelper;
import com.coface.corp.framework.sso.SSOUserContext;
import com.coface.corp.translationView.UserData;

@Path("/hello")
public class HelloWorldService {

	@GET
	@Path("/whoami")
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getTrackInJSON(@Context HttpServletRequest request) {
		SSOUserContext ssoUserContext = SSOClientHelper.getAuthenticatedUserContext(request);
		AccountBDT account = ssoUserContext.getAuthenticationInformation().getAccount();

		return new UserData(account.getAccountId(), ssoUserContext.getQualifiedUserName());
	}

	@GET
	@Path("/config")
	@Produces(MediaType.APPLICATION_JSON)
	public Properties getProperties() {
		ManagedProperties properties = PropertiesFactory.getProperties("translationView.config");
		return properties.getProperties();
	}
	
}