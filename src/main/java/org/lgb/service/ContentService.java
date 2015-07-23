/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.service;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.util.Hibernate;
import org.lgb.model.Content;


/**
 *
 * @author mward
 */
public class ContentService {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
	public static Content store(InputStream uploadedInputStream) throws IOException {
		byte[] bytes = IOUtils.toByteArray(uploadedInputStream);
		String md5 = DigestUtils.md5Hex(bytes);
		Content content = get(md5);
		if (content == null){
			Session session = sessionFactory.getCurrentSession();
			Transaction trans = session.beginTransaction();
			content = new Content();
			content.setId(md5);
			content.setFile(bytes);
			trans.commit();
		}
		
		return content;
	}
	
	public static Content get(String md5){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Content content = (Content) session.get(Content.class, md5);
		trans.commit();
        return content;
	}
}
