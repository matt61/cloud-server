package org.lgb.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.UUID;
import org.lgb.service.FileService;
import org.lgb.service.SnapshotService;
import org.lgb.model.Snapshot;
import org.lgb.model.File;

@Path("snapshot")
@Api(value = "snapshot")
public class SnapshotResource {
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a snapshot by id")
    public Snapshot getSnapshot(@PathParam("id") UUID id) {
		return SnapshotService.get(id);
    }
	
	@POST
	@Path("/{id}/file")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Upload content to file")
	public Response addFile(@PathParam("id") UUID id, @FormParam("file") UUID fileId) throws IOException {
		Snapshot snapshot = SnapshotService.get(id);
		File file = FileService.getFile(fileId);
		snapshot.addFile(file);
		return Response.status(201).entity(snapshot.getVersions().size()).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new Snapshot")
    public Response addSnapshot(@FormParam("name") String name) throws IOException {
		return Response.status(201).entity(SnapshotService.add(name).getId()).build();
    }
}
