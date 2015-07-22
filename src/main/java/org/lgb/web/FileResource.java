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
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.lgb.service.FileService;
import org.lgb.model.Version;
import org.lgb.model.File;

@Path("file")
@Api(value = "file")
public class FileResource {
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a file by id")
    public File getFile(@PathParam("id") UUID id) {
		return FileService.getFile(id);
    }
	
	@POST
	@Path("/{id}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "Upload content to file")
	public Response uploadContent(@PathParam("id") UUID id, @FormDataParam("file") InputStream uploadedInputStream) throws IOException {
		Version version = FileService.uploadContent(id, uploadedInputStream);
		return Response.status(201).entity(version.getId()).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new File")
    public Response addFile(@FormParam("name") String name) throws IOException {
		File file = FileService.addFile(name);
		return Response.status(201).entity(file.getId()).build();
    }
}
