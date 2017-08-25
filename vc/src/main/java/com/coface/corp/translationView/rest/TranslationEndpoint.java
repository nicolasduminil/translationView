package com.coface.corp.translationView.rest;

import java.io.IOException;
import java.io.OutputStream;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coface.corp.translationView.dto.BundleLanguageIdArray;
import com.coface.corp.translationView.dto.TranslationList;
import com.coface.corp.translationView.model.Translation;
import com.coface.corp.translationView.model.TranslationId;
import com.coface.corp.translationView.service.export.ExportContext;
import com.coface.corp.translationView.session.I18nSLSB;

@Path("/translations")
public class TranslationEndpoint
{
  private static Logger slf4jLogger = LoggerFactory.getLogger(TranslationEndpoint.class);
  
  @EJB
  private I18nSLSB ejb;
 
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Translation entity)
  {
    ejb.createTranslation(entity);
    return Response.created(UriBuilder.fromResource(I18nLanguageEndpoint.class).path(String.valueOf(entity.getAtagId())).build()).build();
  }

  @DELETE
  @Path("/{tagId}/{codeApp}/{lang}")
  public Response deleteById(@PathParam("tagId") String id, @PathParam("codeApp") String app, @PathParam("lang") String lang)
  {
    return ejb.deleteTranslationById(new TranslationId(id, app, lang)) ? Response.noContent().build() : Response.status(Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{tagId}/{codeApp}/{lang}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("tagId") String id, @PathParam("codeApp") String app, @PathParam("lang") String lang)
  {
    Translation entity = ejb.findTranslationById(new TranslationId(id, app, lang));
    return entity == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(entity).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TranslationList listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
  {
    List<Translation> translations = ejb.listAllTranslations(startPosition, maxResult);
    return new TranslationList(translations);
  }

  @PUT
  @Path("/{tagId}/{codeApp}/{lang}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("tagId") String id, @PathParam("codeApp") String app, @PathParam("lang") String lang, Translation entity)
  {
    Response resp = Response.noContent().build();
    if (entity == null || id == null || app == null || lang == null)
      resp = Response.status(Status.BAD_REQUEST).build();
    else
    {
      TranslationId translationId = new TranslationId();
      if (!translationId.getAtagId().equals(entity.getAtagId()))
        resp = Response.status(Status.CONFLICT).entity(entity).build();
      else
        ejb.updateTranslation(entity);
    }
    return resp;
  }

  @POST
  @Path("export")
  @Produces({MediaType.APPLICATION_OCTET_STREAM})
  @Consumes(MediaType.APPLICATION_JSON)
  public Response downloadExcelFile(BundleLanguageIdArray idArray) throws Exception
  {
    String[] bundleIds = idArray.getBundleIds();
    String[] languageIds = idArray.getLangIds();
    slf4jLogger.info("*** downloadExcelFile "+ bundleIds + " " + languageIds);
    final ExportContext exportContext = ejb.generateXlsFile(bundleIds, languageIds);
    StreamingOutput fileStream = new StreamingOutput()
    {
      @Override
      public void write(OutputStream output) throws IOException, WebApplicationException
      {
        try
        {
          byte[] data = FileUtils.readFileToByteArray(exportContext.getResultFile());
          output.write(data);
          output.flush();
        }
        catch (Exception e)
        {
          throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
      }
    };
    return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).header("content-disposition", "attachment; filename = " + exportContext.getResultFile().getName()).build();
  }
}
