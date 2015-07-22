/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.web;

import java.io.IOException;
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
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import java.io.InputStream;
import java.util.UUID;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.client.ClientConfig;
import org.apache.commons.io.IOUtils;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SnapshotResourceTest extends JerseyTest {
	
	protected static UUID snapshotId;
	protected static UUID fileId;
	protected static UUID contentId;
 
    @Override
    protected Application configure() {
        return new org.lgb.Application();
    }
	
	@Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
    }
 
    @Test
    public void test0_AddFile() {
		Form form = new Form().param("name", "test");
		Entity<Form> entity = Entity.form(form);
        Response response = target("file").request(MediaType.APPLICATION_JSON).post(entity);
		this.fileId = response.readEntity(UUID.class);
        assertEquals(201, response.getStatus());
    }
	
	@Test
    public void test1_UploadContent() throws IOException {
		FormDataMultiPart form = new FormDataMultiPart();
		InputStream data = IOUtils.toInputStream("This is a test", "UTF-8");
		StreamDataBodyPart fdp = new StreamDataBodyPart("file", data);
		form.bodyPart(fdp);
		Response response = target("file/"+this.fileId+"/upload").request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.MULTIPART_FORM_DATA), Response.class);
		this.contentId = response.readEntity(UUID.class);
		assertEquals(201, response.getStatus());
    }
			
	@Test
    public void test2_AddSnapshot() throws IOException {
		Form form = new Form().param("name", "test");
		Entity<Form> entity = Entity.form(form);
        Response response = target("snapshot").request(MediaType.APPLICATION_JSON).post(entity);
		this.snapshotId = response.readEntity(UUID.class);
        assertEquals(201, response.getStatus());
    }
	
	@Test
    public void test3_AddFileToSnapshot() throws IOException {
		Form form = new Form().param("file", this.fileId.toString());
		Entity<Form> entity = Entity.form(form);
        Response response = target("snapshot/"+this.snapshotId+"/file").request().post(entity);
        assertEquals(201, response.getStatus());
		assertEquals("1", response.readEntity(String.class));
    }
}
