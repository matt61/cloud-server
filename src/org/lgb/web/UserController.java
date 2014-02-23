package org.lgb.web;

import org.apache.log4j.Logger;

import javax.annotation.Resource;

import org.lgb.model.User;
import org.lgb.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

//http://alekhya07.blogspot.com/2013/08/sample-application-of-spring.html

@Controller
@RequestMapping("/user")
public class UserController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "add")
	public String addUser(@ModelAttribute("user") User user, BindingResult result) {
		userService.add(user);
		return "redirect:" + user.getId() + "/view";
	}

	@RequestMapping("{userId}/view")
	public ModelAndView viewUser(@PathVariable Long userId) {
		User user = userService.get(userId);
		return new ModelAndView("user/view", "msg", user.getFirstName());
	}

	@RequestMapping("index")
	public ModelAndView listUsers() {
		return new ModelAndView("user/list", "users", userService.getAll());
	}

	@RequestMapping("new")
	public ModelAndView newUser() {
		return new ModelAndView("user/edit", "command", new org.lgb.model.User());
	}
	
	@RequestMapping("edit")
	public ModelAndView editUser() {
		User user = new org.lgb.model.User();
		user.setFirstName("Matt");
		return new ModelAndView("user/edit", "command", user);
	}

}