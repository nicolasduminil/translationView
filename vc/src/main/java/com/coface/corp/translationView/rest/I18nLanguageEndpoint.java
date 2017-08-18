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

import com.coface.corp.translationView.dto.I18nLanguageList;
import com.coface.corp.translationView.model.I18nLanguage;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/i18nlanguages")
public class I18nLanguageEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(I18nLanguage entity)
  {
    ejb.createLanguage(entity);
    return Response.created(UriBuilder.fromResource(I18nLanguageEndpoint.class).path(String.valueOf(entity.getCodePk())).build()).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteById(@PathParam("id") String id)
  {
    return ejb.deleteLanguageById(id) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Response findById(@PathParam("id") String id)
  {
    I18nLanguage entity = ejb.findLanguageById(id);
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public I18nLanguageList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<I18nLanguage> languages = ejb.listAllLanguages(startPosition, maxResult);
    return new I18nLanguageList(languages);
  }

  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  public Response update(@PathParam("id") String id, I18nLanguage entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else if (!id.equals(entity.getCodePk()))
      resp = Response.status(Status.CONFLICT).entity(entity).build();
    else
      ejb.updateLanguage(entity);
    return resp;
  }
}
