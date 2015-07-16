package org.lgb.web;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Path("user")
@Api(value = "user")
public class UserResource {

	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	protected static ObjectMapper mapper = new ObjectMapper(); 
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a user by id")
    public User getUser(@PathParam("id") Long id) throws JsonProcessingException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		trans.commit();
        return user;
    }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Create a new User")
    public Response addUser(@FormParam("name") String name) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = new User();
		user.setName(name);
		session.save(user);
		trans.commit();
		return Response.status(201).entity(user.getId()).build();
    }
}
