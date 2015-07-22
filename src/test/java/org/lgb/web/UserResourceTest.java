/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.web;

import org.glassfish.jersey.test.JerseyTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import org.lgb.model.User;

public class UserResourceTest extends JerseyTest {
 
    @Override
    protected Application configure() {
        return new org.lgb.Application();
    }
 
    @Test
    public void testAddUser() {
		Form form = new Form().param("name", "test");
		Entity<Form> entity = Entity.form(form);
        Response response = target("user").request(MediaType.APPLICATION_JSON).post(entity);
        assertEquals(201, response.getStatus());
        assertEquals("1", response.readEntity(String.class));
    }
	
	@Test
    public void testGetUser() {
        Response response = target("user/1").request(MediaType.APPLICATION_JSON).get();
		User user = response.readEntity(User.class);
        assertEquals(200, response.getStatus());
        assertEquals("test", user.getName());
    }
}
