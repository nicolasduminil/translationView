package com.coface.i18n.rest.tests;

import static org.junit.Assert.*;

import javax.ws.rs.core.*;

import org.junit.*;
import org.slf4j.*;

import com.coface.corp.translationView.dto.*;
import com.coface.corp.translationView.model.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;

public class UserDataIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(UserDataIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/userdata");
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
    UserDataList userData = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(UserDataList.class);
    assertEquals (userData.getUserData().size(), 3);
  }

  @Test
  public void testUserApplication2()
  {
    UserData userData = new UserData(new UserDataId("login", "groupId"), "password", "firstname", "lastname", "app", "lang", "reflang");
    slf4jLogger.info("*** Creating User Data");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, userData);
    slf4jLogger.info("*** Getting User Data");
    resource.path("login").path("groupId").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    slf4jLogger.info("*** Updating User Data");
    resource.path("login").path("groupId").type(MediaType.APPLICATION_JSON).put(ClientResponse.class, userData);
    slf4jLogger.info("*** Deleting User Data");
    resource.path("login").path("groupId").delete();
  }


}
