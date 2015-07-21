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
import org.lgb.model.File;
import java.util.UUID;

public class FileResourceTest extends JerseyTest {
	
	protected static UUID uuid;
 
    @Override
    protected Application configure() {
        return new org.lgb.Application();
    }
 
    @Test
    public void testAddFile() {
		Form form = new Form().param("name", "test");
		Entity<Form> entity = Entity.form(form);
		GenericType<Response> generic = new GenericType<Response>(Response.class);
        Response response = target("file").request(MediaType.APPLICATION_JSON).post(entity, generic);
		this.uuid = response.readEntity(UUID.class);
        assertEquals(201, response.getStatus());
    }
	
	@Test
    public void testGetFile() {
		GenericType<Response> generic = new GenericType<Response>(Response.class);
        Response response = target("file/"+this.uuid).request(MediaType.APPLICATION_JSON).get(generic);
		File file = response.readEntity(File.class);
        assertEquals(200, response.getStatus());
        assertEquals("test", file.getName());
		assertEquals(0, file.getContents().size());
    }
}
