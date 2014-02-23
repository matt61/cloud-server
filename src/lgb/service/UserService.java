package lgb.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import lgb.model.User;
import lgb.util.HibernateUtil;
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
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		trans.commit();
		return user;
	}

	public void edit(User user) {
		logger.debug("Editing existing person");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person via id
		User existingUser = (User) session.get(User.class, user.getId());

		// Assign updated values to this person
		existingUser.setFirstName(user.getFirstName());

		// Save updates
		session.save(existingUser);
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

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM User");

		// Retrieve all
		return query.list();
	}

}