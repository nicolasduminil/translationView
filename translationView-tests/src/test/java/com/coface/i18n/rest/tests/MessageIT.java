package com.coface.i18n.rest.tests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coface.corp.translationView.dto.MessageList;
import com.coface.corp.translationView.model.I18nBundle;
import com.coface.corp.translationView.model.Message;
import com.coface.corp.translationView.model.MessageId;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class MessageIT
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(MessageIT.class);
  private static Client client;
  private static WebResource resource;

  @BeforeClass
  public static void beforeClass()
  {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    client = Client.create(defaultClientConfig);
    resource = client.resource("http://localhost:7001/translationView/rest/messages");
  }

  @AfterClass
  public static void afterClass()
  {
    resource = null;
    client.destroy();
    client = null;
  }

  @Test
  public void testMessage1()
  {
    MessageList messages = resource.queryParam("start", "0").queryParam("max", "3").type(MediaType.APPLICATION_JSON).get(MessageList.class);
    assertEquals (messages.getMessages().size(), 3);
  }

  @Test
  public void testMessage2()
  {
    I18nBundle bundle = new I18nBundle("codePk123", "label", "1");
    client.resource("http://localhost:7001/translationView/rest/i18nbundles").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, bundle);
    Message message = new Message (new MessageId ("tagId1", bundle.getCodePk()), bundle, "1", "1", "1", "1");
    slf4jLogger.info("*** Creating message");
    resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, message);
    slf4jLogger.info("*** Getting message");
    resource.path(message.getId().getTagId()).path(message.getId().getCodeApp()).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    message.setUsedInXml("1");
    slf4jLogger.info("*** Updating message");
    resource.path(message.getId().getTagId()).path(message.getId().getCodeApp()).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, message);
    slf4jLogger.info("*** Deleting message");
    resource.path(message.getId().getTagId()).path(message.getId().getCodeApp()).delete();
    client.resource("http://localhost:7001/translationView/rest/i18nbundles").path("codePk123").delete();
  }
}
