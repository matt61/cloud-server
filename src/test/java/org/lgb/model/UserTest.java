/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {

	@Test
	public void nameShouldBeSet() {
		User user = new User();
		user.setName("TEST NAME");
		assertEquals("TEST NAME", user.getName());
	}
}
