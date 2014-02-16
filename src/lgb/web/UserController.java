package lgb.web;

import org.hibernate.Session;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import lgb.model.User;
import lgb.util.HibernateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(method = RequestMethod.POST, value = "add")
    public String addUser(@ModelAttribute("user") User user, BindingResult result){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.close();		
		return "redirect:"+user.getId()+"/view";
	}
    
	@RequestMapping("{userId}/view")
	public ModelAndView viewUser(@PathVariable String userId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.createQuery("from User where id = "+userId).uniqueResult();
		session.close();
		return new ModelAndView("user/view", "msg", user.getFirstName());
	}
	
	@RequestMapping("index")
    public ModelAndView listUsers(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List users = session.createQuery("from User").list();
		session.close();
		return new ModelAndView("user/list", "users", users);
	}
    
	@RequestMapping("new")
    public ModelAndView newUser(){
		return new ModelAndView("user/new", "command", new lgb.model.User());
	}

}