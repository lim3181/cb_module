package com.poc.spring.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface CouchbaseService {

	public Map<String, Object> excuteDataN1QL(HttpServletRequest request) throws Exception;

	public Map<String, Object> excuteSdkJob(HttpServletRequest request) throws Exception;

	public Map<String, Object> uploadFile(MultipartHttpServletRequest mRequest) throws Exception;

	public Map<String, Object> makeRandomData(HttpServletRequest request)throws Exception;

	public Map<String, Object> connectionData(HttpServletRequest request)throws Exception;

	public Map<String, Object> createBucket(HttpServletRequest request);


}
