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

import com.coface.corp.translationView.dto.I18nBundleList;
import com.coface.corp.translationView.model.I18nBundle;
import com.coface.corp.translationView.model.UserApplicationId;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/i18nbundles")
public class I18nBundleEndpoint
{
  @EJB
  private I18nSLSB ejb;

  @POST
  @Consumes("application/json")
  public Response create(I18nBundle entity)
  {
    ejb.createBundle(entity);
    return Response.created(UriBuilder.fromResource(I18nBundleEndpoint.class).path(entity.getCodePk()).build()).build();
  }

  @DELETE
  @Path("{id}")
  public Response deleteById(@PathParam("id") String id)
  {
    return ejb.deleteBundleById(id) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public Response findById(@PathParam("id") String id)
  {
    I18nBundle entity = ejb.findBundleById(id);
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Path("{id}/{app}")
  @Produces("application/json")
  public Response findByUserApplicationId(@PathParam("id") String userId, @PathParam("app") String userApp)
  {
    List<I18nBundle> entity = ejb.findBundleByUserApplicationId(new UserApplicationId (userId, userApp));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces("application/json")
  public I18nBundleList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<I18nBundle> bundles = ejb.listAllBundles(startPosition, maxResult);
    return new I18nBundleList(bundles);
  }

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response update(@PathParam("id") String id, I18nBundle entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else if (!id.equals(entity.getCodePk()))
      resp = Response.status(Status.CONFLICT).entity(entity).build();
    else
      ejb.updateBundle(entity);
    return resp;
  }
}
