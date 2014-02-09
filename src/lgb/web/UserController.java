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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

//    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		System.out.println("Add method called");
//		User.createAndStoreEvent("test3");
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List cats = session.createQuery("from User").list();
//		String name = ((User) cats.get(0)).getFirstName();
//		session.close();
//		return new ModelAndView("user", "msg", name);
//	}
    
	@RequestMapping("/user/view")
	public ModelAndView view(){
		System.out.println("Add method called");
		return new ModelAndView("user/view", "msg", "Create");
	}
    
//    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		System.out.println("Add method called");
//		return new ModelAndView("user", "msg", "Create");
//	}

}