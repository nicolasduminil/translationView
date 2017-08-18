package com.coface.i18n.rest.tests;

import static org.junit.Assert.*;

import java.util.*;

import javax.ws.rs.core.*;

import org.junit.*;
import org.slf4j.*;

import com.coface.corp.translationView.dto.*;
import com.coface.corp.translationView.model.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;

public class UserApplicationIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(UserApplicationIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/userapplications");
  }

  @AfterClass
  public static void afterClass()
  {
    resource = null;
    client.destroy();
    client = null;
  }

  @Test
  public void testUserApplication1()
  {
    UserApplicationList userApplications = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(UserApplicationList.class);
    assertEquals (userApplications.getApplications().size(), 3);
  }

  @Test
  public void testUserApplication2()
  {
    UserApplication userApplication = new UserApplication(new UserApplicationId("userId", "applicationId"));
    slf4jLogger.info("*** Creating User Application");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, userApplication);
    slf4jLogger.info("*** Getting User Application");
    resource.path("userId").path("applicationId").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    slf4jLogger.info("*** Updating User Application");
    resource.path("userId").path("appliacationId").type(MediaType.APPLICATION_JSON).put(ClientResponse.class, userApplication);
    slf4jLogger.info("*** Deleting User Application");
    resource.path("userId").path("applicationId").delete();
  }

}
