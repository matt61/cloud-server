package org.lgb.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.model.File;
import org.lgb.util.HibernateUtil;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

@Service("fileService")
@Transactional
public class FileService {

	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public File get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		File file = (File) session.get(File.class, id);
		trans.commit();
		return file;
	}
}