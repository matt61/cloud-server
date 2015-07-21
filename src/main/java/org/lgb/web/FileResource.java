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
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.util.Hibernate;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Path("file")
@Api(value = "file")
public class FileResource {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a file by id")
    public org.lgb.model.File getFile(@PathParam("id") UUID id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		org.lgb.model.File file = (org.lgb.model.File) session.get(org.lgb.model.File.class, id);
		trans.commit();
        return file;
    }
	
	@POST
	@Path("/{id}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadContent(@PathParam("id") UUID id, @FormDataParam("file") InputStream uploadedInputStream) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		org.lgb.model.File file = (org.lgb.model.File) session.get(org.lgb.model.File.class, id);
		org.lgb.model.Content content = file.addContent(uploadedInputStream);
		session.persist(content);
		trans.commit();
		return Response.status(201).entity(content.getId()).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new File")
    public Response addFile(@FormParam("name") String name) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		org.lgb.model.File file = new org.lgb.model.File();
		file.setName(name);
		session.save(file);
		trans.commit();
		return Response.status(201).entity(file.getId()).build();
    }
}
