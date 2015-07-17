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
import org.lgb.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.util.Hibernate;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Path("file")
@Api(value = "file")
public class FileResource {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a file by id")
    public User getFile(@PathParam("id") String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		trans.commit();

        return user;
    }
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		//String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();
 
		// save it
		//writeToFile(uploadedInputStream, uploadedFileLocation);
 
		//String output = "File uploaded to : " + uploadedFileLocation;
 
		return Response.status(200).entity(1).build();
 
	}
}
