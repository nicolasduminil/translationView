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

import com.coface.corp.translationView.dto.UserLanguageList;
import com.coface.corp.translationView.model.UserLanguage;
import com.coface.corp.translationView.model.UserLanguageId;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/userlanguages")
public class UserLanguageEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(UserLanguage entity)
  {
    ejb.createUserLanguage(entity);
    return Response.created(UriBuilder.fromResource(I18nLanguageEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
  }

  @DELETE
  @Path("/{userId}/{lang}")
  public Response deleteById(@PathParam("userId") String id, @PathParam("lang") String lang)
  {
    return ejb.deleteUserLanguageById(new UserLanguageId(id, lang)) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Produces("application/json")
  @Path("/{userId}/{lang}")
  public Response findById(@PathParam("userId") String id, @PathParam("lang") String lang)
  {
    UserLanguage entity = ejb.findUserLanguageById(new UserLanguageId(id, lang));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public UserLanguageList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<UserLanguage> userLanguages = ejb.listAllUserLanguages(startPosition, maxResult);
    return new UserLanguageList(userLanguages);
  }

  @PUT
  @Consumes("application/json")
  @Path("/{userId}/{lang}")
  public Response update(@PathParam("userId") String id, @PathParam("lang") String lang, UserLanguage entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null || lang == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else
    {
      UserLanguageId userLanguageId = new UserLanguageId(id, lang);
      if (!userLanguageId.equals(entity.getId()))
        resp = Response.status(Status.CONFLICT).entity(entity).build();
      else
        ejb.updateUserLanguage(entity);
    }
    return resp;
  }
}
