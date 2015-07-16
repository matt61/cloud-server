package org.lgb.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.lgb.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.util.Hibernate;

@Path("file")
@Api(value = "file")
@Produces(MediaType.APPLICATION_JSON)
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
	@ApiOperation(value = "Find a file by id")
    public User getIt(@PathParam("id") String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		trans.commit();

        return user;
    }
}
