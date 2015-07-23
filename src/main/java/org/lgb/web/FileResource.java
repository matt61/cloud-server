package org.lgb.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.lgb.service.FileService;
import org.lgb.model.Version;
import org.lgb.model.File;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;

@Path("file")
@Api(value = "file")
public class FileResource {
	
	protected static Logger logger = Logger.getLogger("service");
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a file by id")
    public File getFile(@PathParam("id") UUID id) {
		return FileService.getFile(id);
    }
	
	@GET
	@Path("/{id}/content")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@ApiOperation(value = "Download file content")
    public Response downloadContent(@PathParam("id") UUID id) {
		try {
			ContentDisposition contentDisposition = ContentDisposition.type("attachment").fileName("filename.csv").creationDate(new Date()).build();
			return Response.ok(FileService.getContent(id)).header("Content-Disposition", contentDisposition).build();
		} catch (ObjectNotFoundException e){
			return Response.status(500).entity(e).build();
		}
    }
	
	@POST
	@Path("/{id}/content")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "Upload content to file")
	public Response uploadContent(@PathParam("id") UUID id, @FormDataParam("file") InputStream uploadedInputStream) throws IOException {
		try {
			return Response.status(201).entity(FileService.upload(id, uploadedInputStream).getId()).build();
		} catch (ObjectNotFoundException e){
			return Response.status(500).entity(e).build();
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new File")
    public Response addFile(@FormParam("name") String name, @FormParam("path") String path, @FormParam("type") String type) throws IOException {
		return Response.status(201).entity(FileService.addFile(name, path, type).getId()).build();
    }
}
