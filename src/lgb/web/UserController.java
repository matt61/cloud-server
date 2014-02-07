package lgb.web;

import org.hibernate.Session;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lgb.model.User;
import lgb.util.HibernateUtil;

public class UserController extends MultiActionController {

    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("Add method called");
		User.createAndStoreEvent("test");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List cats = session.createQuery("from User").list();
		session.close();
		return new ModelAndView("user", "msg", "Add");
	}
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("Add method called");
		return new ModelAndView("user", "msg", "Create");
	}

}