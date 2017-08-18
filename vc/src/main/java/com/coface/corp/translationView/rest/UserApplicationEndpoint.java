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

import com.coface.corp.translationView.dto.UserApplicationList;
import com.coface.corp.translationView.model.UserApplication;
import com.coface.corp.translationView.model.UserApplicationId;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/userapplications")
public class UserApplicationEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(UserApplication entity)
  {
    ejb.createUserApplication(entity);
    return Response.created(UriBuilder.fromResource(I18nLanguageEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
  }

  @DELETE
  @Path("/{userId}/{application}")
  public Response deleteById(@PathParam("userId") String id, @PathParam("application") String application)
  {
    return ejb.deleteUserApplicationById(new UserApplicationId(id, application)) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{userId}/{application}")
  @Produces("application/json")
  public Response findById(@PathParam("userId") String id, @PathParam("application") String application)
  {
    UserApplication entity = ejb.findUserApplicationById(new UserApplicationId(id, application));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public UserApplicationList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<UserApplication> applications = ejb.listAllUserApplications(startPosition, maxResult);
    return new UserApplicationList(applications);
  }

  @PUT
  @Path("/{userId}/{application}")
  @Consumes("application/json")
  public Response update(@PathParam("userId") String id, @PathParam("application") String application, UserApplication entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else if (!id.equals(entity.getId().getUserId()))
      resp = Response.status(Status.CONFLICT).entity(entity).build();
    else
      ejb.updateUserApplication(entity);
    return resp;
  }
}
