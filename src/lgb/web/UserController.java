package lgb.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lgb.model.User;

public class UserController extends MultiActionController {

    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("Add method called");
		User.createAndStoreEvent("test");
		return new ModelAndView("user", "msg", "Add");
	}
    
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("Add method called");
		return new ModelAndView("user", "msg", "Create");
	}

}