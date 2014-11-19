package com.sunnyvale.springhbase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signout")
public class SignoutController {

	@RequestMapping(method = RequestMethod.GET)
	public String signinUser(HttpServletRequest request) {
		
		request.getSession().removeAttribute("loggedInUser");
		return "home";
	}
}
