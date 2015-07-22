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
import org.lgb.model.File;
import org.lgb.util.Hibernate;
import org.lgb.model.Version;
import org.lgb.model.Snapshot;
import static org.lgb.service.FileService.sessionFactory;

/**
 *
 * @author mward
 */
public class SnapshotService {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
	public static Snapshot addSnapshot(String name){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Snapshot snapshot = new Snapshot();
		snapshot.setName(name);
		session.save(snapshot);
		trans.commit();
		return snapshot;
	}
	
	public static Snapshot getSnapshot(UUID id){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Snapshot snapshot = (Snapshot) session.get(Snapshot.class, id);
		trans.commit();
        return snapshot;
	}
}
