package com.coface.i18n.rest.tests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coface.corp.translationView.dto.I18nLanguageList;
import com.coface.corp.translationView.model.I18nLanguage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class I18nLanguageIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(I18nLanguageIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/i18nlanguages");
  }

  @AfterClass
  public static void afterClass()
  {
    resource = null;
    client.destroy();
    client = null;
  }

  @Test
  public void testLanguage1()
  {
    I18nLanguageList languages = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(I18nLanguageList.class);
    assertEquals (languages.getLanguages().size(), 3);
  }

  @Test
  public void testLanguage2()
  {
    I18nLanguage language = new I18nLanguage("codePk123", "label", "1", "1");
    slf4jLogger.info("*** Creating language");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, language);
    slf4jLogger.info("*** Getting language");
    resource.path("codePk123").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    language.setLabel("another label");
    slf4jLogger.info("*** Updating bundle");
    resource.path("codePk123").type(MediaType.APPLICATION_JSON).put(ClientResponse.class, language);
    slf4jLogger.info("*** Deleting language");
    resource.path("codePk123").delete();
  }
}
