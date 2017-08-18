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

public class TranslationTI
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(TranslationTI.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/translations");
  }

  @AfterClass
  public static void afterClass()
  {
    resource = null;
    client.destroy();
    client = null;
  }

  /*@Test
  public void testTranslation1()
  {
    TranslationList languages = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(TranslationList.class);
    assertEquals(languages.getTranslations().size(), 3);
  }*/

  @Test
  public void testTranslation2()
  {
    I18nLanguage lang = new I18nLanguage("codePk", "label", "1", "1");
    I18nBundle bundle = new I18nBundle("codePk123", "label", "1");
    Message msg = new Message(new MessageId ("tagId1", bundle.getCodePk()), bundle, "1", "1", "1", "1");
    Translation translation = new Translation(new TranslationId(msg.getTagId(), msg.getCodeApp(), lang.getCodePk()), lang, msg, new Date(), "status");
    slf4jLogger.info("*** Creating translation");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, translation);
    /*slf4jLogger.info("*** Getting translation");
    resource.path("tagId").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    translation.setValue("new value");
    slf4jLogger.info("*** Updating translation");
    resource.path("tagId").put(ClientResponse.class, translation);
    slf4jLogger.info("*** Deleting translation");
    resource.path("tagId").delete();*/
  }
}
