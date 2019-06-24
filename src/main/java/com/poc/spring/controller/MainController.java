package com.poc.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("/")
	public String getMethod() { 
		return "index"; 
	} 
	@RequestMapping("/2")
	public String getMethod2() { 
		return "index2"; 
	} 
	
	@RequestMapping("/n1ql")
	public void postMethod1() { 
		System.out.println("test3");
	} 
	
	
	@RequestMapping(value="/hey", method=RequestMethod.POST) 
	public String postMethod2(HttpServletRequest request) { 
		return "hey post"; 
	}
	
	@RequestMapping(value="/request", method=RequestMethod.POST) 
	//@ResponseBody
	public String postMethod3(HttpServletRequest request) { 
		return "service"; 
	}
}
