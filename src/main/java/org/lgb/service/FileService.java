/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.util.Hibernate;
import org.lgb.model.Version;
import org.lgb.model.File;

/**
 *
 * @author mward
 */
public class FileService {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
	public static Version uploadContent(UUID id, InputStream uploadedInputStream) throws IOException{
		logger.debug("Adding version");
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		File file = (File) session.get(File.class, id);
		Version version = file.addContent(uploadedInputStream);
		file.setModified(new Date());
		session.persist(file);
		session.persist(version);
		trans.commit();
		return version;
	}
	
	public static File addFile(String name){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		File file = new File();
		file.setName(name);
		session.save(file);
		trans.commit();
		return file;
	}
	
	public static File getFile(UUID id){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		File file = (File) session.get(File.class, id);
		trans.commit();
        return file;
	}
}
