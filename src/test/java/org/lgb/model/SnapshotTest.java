/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.model;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SnapshotTest {

	@Test
	public void nameShouldBeSet() {
		Snapshot snapshot = new Snapshot();
		snapshot.setName("TEST NAME");
		assertEquals("TEST NAME", snapshot.getName());
	}
	
	@Test
	public void shouldAddFile() throws IOException {
		File file = new File();
		file.addContent(IOUtils.toInputStream("This is a test", "UTF-8"));
		Snapshot snapshot = new Snapshot();
		snapshot.addFile(file);
	}
}
