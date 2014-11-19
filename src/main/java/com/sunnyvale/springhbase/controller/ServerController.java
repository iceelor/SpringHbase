package com.sunnyvale.springhbase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sunnyvale.springhbase.model.Server;
import com.sunnyvale.springhbase.repository.JVMRepository;
import com.sunnyvale.springhbase.repository.ServerRepository;

@Controller
@RequestMapping("/server")
public class ServerController {
	@Autowired
	ServerRepository serverRepository;

	@Autowired
	JVMRepository JVMRepository;

	private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getSignupForm(Model model, HttpServletRequest request) throws IOException {
		
		List<Server> list = serverRepository.getServerList();
		model.addAttribute("serverList", list);
		
		for(Server server : list) {
			model.addAttribute("serverInfo", serverRepository.getServer(server.getHashCode()));
			//JVMRepository.getJVMList(server.getHashCode(), System.currentTimeMillis(), 100000);
			JVMRepository.getCurrentJVM(server.getHashCode());
		}
		
		
		return "server";
	}

}
