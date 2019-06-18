package com.poc.spring.controller;

import javax.servlet.http.HttpServletRequest;

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
	public void postMethod1() { 
		System.out.println("test2");
	} 
	
	@RequestMapping(value="/hey", method=RequestMethod.POST) 
	public String postMethod2(HttpServletRequest request) { 
		return "hey post"; 
	}
}
