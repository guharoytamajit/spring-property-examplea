package com.tamajit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tamajit.bean.Employee;

@Controller
public class MyController {

	@Autowired
	Employee emp1;
	
	@Value("${message}")
	String msg;

	@Value("${bye:default bye}")
	/*After : default value is given,which will be shown if bye neither found in property or in system env*/
	String bye;
	@Autowired
	Environment env;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		System.out.println("message:"+env.getProperty("message"));
		System.out.println("bye:"+env.getProperty("bye"));
		model.addAttribute("employee", emp1);
		model.addAttribute("message", msg);
		model.addAttribute("bye", bye);
		return "home";
	}

}