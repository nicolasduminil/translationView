package com.coface.corp.translationView.rest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.coface.corp.framework.configuration.ManagedProperties;
import com.coface.corp.framework.configuration.PropertiesFactory;
import com.coface.corp.translationView.UserData;
import com.coface.corp.translationView.UserSession;
import com.coface.corp.translationView.filter.InitSessionFilter;

@Path("/admin")
public class HelloWorldService {
	// TODO Séparer dans plusieurs classes user, conf, trans....
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	/*** USER ***/
	
	@GET
	@Path("/whoami")
	@Produces(MediaType.APPLICATION_JSON)
	public UserData getUserData() {
		UserSession userSession = (UserSession) request.getSession().getAttribute(InitSessionFilter.MY_USER_SESSION);
		return userSession.getUserData();
	}
	
	@POST
	@Path("/logout")
	public void logout() {
		UserSession userSession = (UserSession) request.getSession().getAttribute(InitSessionFilter.MY_USER_SESSION);
		userSession.logout(request, response);
	} 
	
//	@GET
//	@Path("/hasAction/{actionId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public boolean isAccountAllowedForAction(@PathParam("actionId") String actionId){
//		UserSession userSession = (UserSession) request.getSession().getAttribute(InitSessionFilter.MY_USER_SESSION);
//		return userSession.isAccountAllowedForAction(ActionRight.valueOf(actionId));
//	}
	
	/*** Translations ***/
	
@GET
@Path("/translations/{lang}")
@Produces(MediaType.APPLICATION_JSON)
public Map<String, String> getTranslations(@PathParam("lang") String lang) {
	HashMap<String, String> translations = new HashMap<String, String>();
	String bundleName = "translationView";
	ResourceBundle resource = ResourceBundle.getBundle(bundleName, new Locale(lang));
	Enumeration<String> keys = resource.getKeys();
	while (keys.hasMoreElements()) {
		String key = keys.nextElement();
		//If multi bundle set key = bundle.key
		translations.put(key, resource.getString(key));
	}
	return translations;
}
	
	/*** Conf ***/
	
	@GET
	@Path("/config")
	@Produces(MediaType.APPLICATION_JSON)
	public Properties getProperties() {
		ManagedProperties properties = PropertiesFactory.getProperties("translationView.config");
		return properties.getProperties();
	}
	
	
	@GET
	@Path("/config/{propName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProperty(@PathParam("propName") String propName) {
		ManagedProperties properties = PropertiesFactory.getProperties("translationView.config");
		return properties.getProperty(propName);
	}
}