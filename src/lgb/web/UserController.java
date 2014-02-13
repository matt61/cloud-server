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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/user")
public class UserController {
	
//	@Autowired
//    private User user;
//
	@RequestMapping(method = RequestMethod.POST, value = "add")
    public String addUser(@ModelAttribute("user") User user, BindingResult result){
		System.out.println(user.getFirstName());
		
//		User.createAndStoreEvent("test3");
//		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		List cats = session.createQuery("from User").list();
//		String name = ((User) cats.get(0)).getFirstName();
//		session.close();
		return "Test";
	}
    
	@RequestMapping("view")
	public ModelAndView viewUser(){
		System.out.println("Add method called");
		return new ModelAndView("user/view", "msg", "Create");
	}
    
	@RequestMapping("new")
    public ModelAndView newUser(){
		return new ModelAndView("user/new", "command", new lgb.web.form.User());
	}

}