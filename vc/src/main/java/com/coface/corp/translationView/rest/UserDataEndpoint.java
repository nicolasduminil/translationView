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

import com.coface.corp.translationView.dto.UserDataList;
import com.coface.corp.translationView.model.UserData;
import com.coface.corp.translationView.model.UserDataId;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/userdata")
public class UserDataEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(UserData entity)
  {
    ejb.createUserData(entity);
    return Response.created(UriBuilder.fromResource(I18nLanguageEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
  }

  @DELETE
  @Path("/{login}/{groupId}")
  public Response deleteById(@PathParam("login") String login, @PathParam("groupId") String groupId)
  {
    return ejb.deleteUserDataById(new UserDataId(login, groupId)) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Produces("application/json")
  @Path("/{login}/{groupId}")
  public Response findById(@PathParam("login") String login, @PathParam("groupId") String groupId)
  {
    UserData entity = ejb.findUserDataById(new UserDataId(login, groupId));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public UserDataList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<UserData> userData = ejb.listAllUserData(startPosition, maxResult);
    return new UserDataList(userData);
  }

  @PUT
  @Consumes("application/json")
  @Path("/{login}/{groupId}")
  public Response update(@PathParam("login") String login, @PathParam("groupId") String groupId, UserData entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || login == null || groupId == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else
    {
      UserDataId userDataId = new UserDataId(login, groupId);
      if (!userDataId.equals(entity.getId()))
        resp = Response.status(Status.CONFLICT).entity(entity).build();
      else
        ejb.updateUserData(entity);
    }
    return resp;
  }
}
