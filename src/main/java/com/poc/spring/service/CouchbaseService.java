package com.poc.spring.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public interface CouchbaseService {

	public Map<String, Object> excuteDataN1QL(HttpServletRequest request) throws Exception;

	public Map<String, Object> excuteSdkJob(HttpServletRequest request) throws Exception;

	public Map<String, Object> uploadFile(HttpServletRequest request)throws Exception;

	public Map<String, Object> makeRandomData(HttpServletRequest request)throws Exception;

	public Map<String, Object> connectionData(HttpServletRequest request)throws Exception;


}
