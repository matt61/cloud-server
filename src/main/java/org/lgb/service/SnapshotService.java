/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lgb.service;

import java.util.Set;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.model.File;
import org.lgb.util.Hibernate;
import org.lgb.model.Snapshot;

/**
 *
 * @author mward
 */
public class SnapshotService {
	
	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = Hibernate.getSessionFactory();
	
	public static Snapshot add(String name){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Snapshot snapshot = new Snapshot();
		snapshot.setName(name);
		session.save(snapshot);
		trans.commit();
		return snapshot;
	}
	
	public static Snapshot addFiles(UUID id, Set<UUID> fileIds){
		Snapshot snapshot = SnapshotService.get(id);
		File file = FileService.getFile(fileId);
		snapshot.addFile(file);
	}
	
	public static Snapshot get(UUID id){
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Snapshot snapshot = (Snapshot) session.get(Snapshot.class, id);
		trans.commit();
        return snapshot;
	}
}
