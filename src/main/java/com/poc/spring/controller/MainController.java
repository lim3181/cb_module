package com.poc.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.poc.spring.service.CouchbaseService;

@Controller
public class MainController {
	
	@Autowired
	CouchbaseService couchbaseService;
	
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
	
	@RequestMapping(value="/n1qlExcute", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> n1qlExcute(HttpServletRequest request) throws Exception { 
		return couchbaseService.excuteDataN1QL(request); 
	}
	
	@RequestMapping(value="/sdkJobExcute", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> sdkJobExcute(HttpServletRequest request) throws Exception { 
		return couchbaseService.excuteSdkJob(request); 
	}
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> fileUpload(MultipartHttpServletRequest mRequest) throws Exception { 
		return couchbaseService.uploadFile(mRequest); 
	}
	
	@RequestMapping(value="/randomData", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> randomData(HttpServletRequest request) throws Exception { 
		return couchbaseService.makeRandomData(request); 
	}

	@RequestMapping(value="/conData", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> conData(HttpServletRequest request) throws Exception { 
		return couchbaseService.connectionData(request); 
	}
	
	@RequestMapping(value="/receive", method=RequestMethod.POST) 
	public Map<String, Object> test(HttpServletRequest request) throws Exception { 
		 BufferedReader bufReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
	     String read = "";

	     while((read = bufReader.readLine()) != null){
	             System.out.print(read + "<BR>");
	}

	bufReader.close();
		return couchbaseService.connectionData(request); 
	}
	
	@RequestMapping(value="/createBucket", method=RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> createBucket(HttpServletRequest request) throws Exception { 
		return couchbaseService.createBucket(request); 
	}

}
