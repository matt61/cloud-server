package lgb.web;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
	@RequestMapping("new")
    public ModelAndView newUser(){
		return new ModelAndView("user/new", "command", new lgb.model.User());
	}

}