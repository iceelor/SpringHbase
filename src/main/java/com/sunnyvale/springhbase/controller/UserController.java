package com.sunnyvale.springhbase.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sunnyvale.springhbase.model.User;
import com.sunnyvale.springhbase.repository.UserRepository;

@Controller
@RequestMapping("/signup")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSignupForm() {
		return new ModelAndView("signup", "user", new User());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String regist(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest request) {
		logger.info("### user info : " + user);
		
		userRepository.save(user.getName(), user.getEmail(), user.getPassword());
			
		return "home";
	}

}
