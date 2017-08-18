package com.coface.corp.translationView.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.coface.corp.translationView.dto.MessageList;
import com.coface.corp.translationView.model.Message;
import com.coface.corp.translationView.model.MessageId;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/messages")
public class MessageEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(Message dto)
  {
    ejb.createMessage(dto);
    return Response.created(UriBuilder.fromResource(MessageEndpoint.class).path(dto.getTagId()).path(dto.getI18nBundle().getCodePk()).build()).build();
  }

  @DELETE
  @Path("/{tagId}/{application}")
  public Response deleteById(@PathParam("tagId") String id, @PathParam("application") String app)
  {
    return ejb.deleteMessageById(new MessageId(id, app)) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{tagId}/{application}")
  @Produces("application/json")
  public Response findById(@PathParam("tagId") String id, @PathParam("application") String app)
  {
    Message entity = ejb.findMessageById(new MessageId(id, app));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public MessageList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<Message> messages = ejb.listAllMessages(startPosition, maxResult);
    return new MessageList(messages);
  }

  @PUT
  @Consumes("application/json")
  @Path("/{tagId}/{application}")
  @Produces("application/json")
  public Response update(@PathParam("tagId") String id, @PathParam("application") String app, Message entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null || app == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else
    {
      MessageId messageId = new MessageId(id, app);
      if (!messageId.getTagId().equals(entity.getTagId()))
        resp = Response.status(Status.CONFLICT).entity(entity).build();
      else
        ejb.updateMessage(entity);
    }
    return resp;
  }
}
