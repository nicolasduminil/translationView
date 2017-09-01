package com.coface.i18n.rest.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coface.corp.translationView.dto.TranslationList;
import com.coface.corp.translationView.model.I18nBundle;
import com.coface.corp.translationView.model.I18nLanguage;
import com.coface.corp.translationView.model.Message;
import com.coface.corp.translationView.model.MessageId;
import com.coface.corp.translationView.model.Translation;
import com.coface.corp.translationView.model.TranslationId;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TranslationIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(TranslationIT.class);
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

  @Test
  public void testTranslation1()
  {
    TranslationList languages = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(TranslationList.class);
    assertEquals(languages.getTranslations().size(), 3);
  }

  @Test
  public void testTranslation2()
  {
    I18nLanguage lang = new I18nLanguage("codePk", "label", "1", "1");
    client.resource("http://localhost:7001/translationView/rest/i18nlanguages").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, lang); 
    I18nBundle bundle = new I18nBundle("codePk123", "label123", "1");
    client.resource("http://localhost:7001/translationView/rest/i18nbundles").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, bundle);
    Message msg = new Message (new MessageId ("tagId1", "codePk123"), bundle, "1", "1", "1", "1");
    client.resource("http://localhost:7001/translationView/rest/messages").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, msg);
    Translation translation = new Translation(new TranslationId("tagId1", "codePk123", "en"), lang, msg, new Date(), "status");
    slf4jLogger.info("*** Creating translation");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, translation);
    slf4jLogger.info("*** Getting translation");
    resource.path("tagId1").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    translation.setValue("new value");
    slf4jLogger.info("*** Updating translation");
    resource.path("tagId1").put(ClientResponse.class, translation);
    slf4jLogger.info("*** Deleting translation");
    resource.path("/tagId1/codePk123/en").delete();
    client.resource("http://localhost:7001/translationView/rest/messages").path("tagId1/codePk123").delete();
    client.resource("http://localhost:7001/translationView/rest/i18nbundles").path("codePk123").delete();
    client.resource("http://localhost:7001/translationView/rest/i18nlanguages").path("codePk").delete();
  }
}
