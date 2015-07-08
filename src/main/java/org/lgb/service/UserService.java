package org.lgb.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.lgb.model.User;
import org.lgb.util.HibernateUtil;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

@Service("userService")
@Transactional
public class UserService {

	protected static Logger logger = Logger.getLogger("service");
	protected static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void add(User user) {
		logger.debug("Adding new person");
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(user);
		trans.commit();
	}

	public User get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		trans.commit();
		return user;
	}

	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User existingUser = (User) session.get(User.class, user.getId());
		existingUser.setFirstName(user.getFirstName());
		session.save(existingUser);
		trans.commit();
	}

	public void delete(Integer id) {
		logger.debug("Deleting existing person");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person first
		User user = (User) session.get(User.class, id);

		// Delete
		session.delete(user);
	}

	public List<User> getAll() {
		logger.debug("Retrieving all persons");
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM User");
		List<User> users = query.list();
		trans.commit();
		return users;
	}

}