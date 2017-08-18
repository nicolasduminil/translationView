package com.coface.i18n.rest.tests;

import static org.junit.Assert.*;

import javax.ws.rs.core.*;

import org.junit.*;
import org.slf4j.*;

import com.coface.corp.translationView.dto.*;
import com.coface.corp.translationView.model.*;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;

public class UserLanguageIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(UserLanguageIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/userlanguages");
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
    UserLanguageList userLanguages = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(UserLanguageList.class);
    assertEquals (userLanguages.getUserLanguages().size(), 3);
  }

  @Test
  public void testUserApplication2()
  {
    UserLanguage userLanguage = new UserLanguage(new UserLanguageId("userId", "lang"));
    slf4jLogger.info("*** Creating User Language");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, userLanguage);
    slf4jLogger.info("*** Getting User Language");
    resource.path("userId").path("lang").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    slf4jLogger.info("*** Updating User Language");
    resource.path("userId").path("lang").put(ClientResponse.class, userLanguage);
    slf4jLogger.info("*** Deleting User Language");
    resource.path("userId").path("lang").delete();
  }



}
