package com.coface.i18n.rest.tests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coface.corp.translationView.dto.I18nBundleList;
import com.coface.corp.translationView.model.I18nBundle;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class I18nBundleIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(I18nBundleIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/i18nbundles");

  }

  @AfterClass
  public static void afterClass()
  {
    resource = null;
    client.destroy();
    client = null;
  }

  @Test
  public void testBundle1()
  {
    I18nBundleList bundles = resource.queryParam("start", "0").queryParam("max", "1").type(MediaType.APPLICATION_JSON).get(I18nBundleList.class);
    assertEquals(bundles.getBundles().size(), 1);
  }

  @Test
  public void testBundle2()
  {
    I18nBundle bundle = new I18nBundle("codePk", "label", "1");
    slf4jLogger.info("*** Creating bundle");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, bundle);
    slf4jLogger.info("*** Getting bundle");
    resource.path("codePk").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    bundle.setLabel("another label");
    slf4jLogger.info("*** Updating bundle");
    resource.path("codePk").type(MediaType.APPLICATION_JSON).put(ClientResponse.class, bundle);
    slf4jLogger.info("*** Deleting bundle");
    resource.path("codePk").delete();
  }
}
