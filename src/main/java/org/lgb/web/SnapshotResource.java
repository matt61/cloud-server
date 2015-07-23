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
import java.util.Set;
import org.hibernate.ObjectNotFoundException;
import org.lgb.service.SnapshotService;
import org.lgb.model.Snapshot;

@Path("snapshot")
@Api(value = "snapshot")
public class SnapshotResource {
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a snapshot by id")
    public Snapshot get(@PathParam("id") UUID id) {
		return SnapshotService.get(id);
    }
	
	@POST
	@Path("/{id}/file")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Upload content to file")
	public Response addFile(@PathParam("id") UUID id, @FormParam("fileIds") final Set<UUID> fileIds) throws IOException {
		try {
			return Response.status(201).entity(SnapshotService.addFiles(id, fileIds).getVersions().size()).build();
		} catch (ObjectNotFoundException e){
			return Response.status(500).entity(e).build();
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new Snapshot")
    public Response add(@FormParam("name") String name) throws IOException {
		return Response.status(201).entity(SnapshotService.add(name).getId()).build();
    }
}
