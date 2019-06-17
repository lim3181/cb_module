package com.poc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping("/")
	public String getMethod() { 
		return "index"; 
	} 
	
	@RequestMapping("/n1ql")
	public String postMethod1() { 
		return "post"; 
	} 
	
	@RequestMapping(value="/hey", method=RequestMethod.POST) 
	public String postMethod2() { 
		return "hey post"; 
	}
}
