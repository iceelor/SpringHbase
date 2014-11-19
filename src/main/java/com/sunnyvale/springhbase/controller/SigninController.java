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
@RequestMapping("/signin")
public class SigninController {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSigninForm() {
		return new ModelAndView("signin", "loginUser", new User());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signinUser(@ModelAttribute("loginUser") User user, BindingResult bindingResult, Model model, HttpServletRequest request) {
		logger.info("### user info : " + user);

		User loginUser = userRepository.findUser(user.getName(), user.getPassword());
		
		if (loginUser != null) {
			loginUser.setPassword(null);
			request.getSession().setAttribute("loggedInUser", loginUser);
			// TODO mode 에 따른 분기 추가 
			return "redirect:/";
		}

		model.addAttribute("error", true);
		return "signin";
	}
}
