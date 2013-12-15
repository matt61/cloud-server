package com.lgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hi")
public class FirstController {
   @RequestMapping(method = RequestMethod.GET)
   public String printWelcome(ModelMap model) {
       model.addAttribute("message", "Spring 3 MVC Hello LAM");
       return "hello";
   }
}