package com.poc.spring.service;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.StringDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

@Service
public class CouchbaseServiceImpl implements CouchbaseService {

	@Override
	public  Map<String, Object> excuteDataN1QL(HttpServletRequest request) throws Exception {
		 Cluster cluster = CouchbaseCluster.create("localhost"); 
		 cluster.authenticate("Administrator", "dpsxndpa1!");
		 Bucket bucket = cluster.openBucket("travel-sample");
		 N1qlQueryResult result = bucket.query(N1qlQuery.simple(request.getParameter("n1qlInput").toString()));
		 
		 Map<String, Object> resultMap = new HashMap<String,Object>();
		 resultMap.put("status", result.status());
		 resultMap.put("allRows", result.allRows().toString());
		 resultMap.put("error", result.errors().toString());
		 return resultMap;

	}

	@Override
	public Map<String, Object> excuteSdkJob(HttpServletRequest request) throws Exception {
		
		 Cluster cluster = CouchbaseCluster.create("localhost"); 
		 cluster.authenticate("Administrator", "dpsxndpa1!");
		 Bucket bucket = cluster.openBucket("travel-sample");
		 String jobs = request.getParameter("sdkJobType");
		 String docId = request.getParameter("sdkJobDocId");
		 Map<String, Object> resultMap = new HashMap<String,Object>();
		 
		 if (jobs.equals("write")) {
			String content = request.getParameter("sdkWriInput");
			StringDocument result =  bucket.insert(StringDocument.create(docId, content));
			resultMap.put("result", "문서 '"+result.id() + "' 가 정상적으로 등록되었습니다.");
			
		 } else if (jobs.equals("read")) {
			 JsonDocument result = bucket.get(docId);
			 resultMap.put("result", result.content().toString());
		 } else if (jobs.equals("delete")) {
			 JsonDocument result = bucket.remove(docId);
			 resultMap.put("result", "문서 '"+result.id() + "' 가 정상적으로 삭제되었습니다.");
		 }
		 return resultMap;
	}

	@Override
	public Map<String, Object> uploadFile(HttpServletRequest request) throws Exception {
		 Cluster cluster = CouchbaseCluster.create("localhost"); 
		 cluster.authenticate("Administrator", "dpsxndpa1!");
		 Bucket bucket = cluster.openBucket("travel-sample");
		 
		String fileName = request.getParameter("fileName");
       	int rowNum = 0;
        System.out.println("======== JSON FILE INSERT 시작 ========");
        BufferedReader br = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8);
        br.readLine();
        	for (String line = null; (line = br.readLine()) != null; rowNum ++) {
        		StringDocument ddoc = StringDocument.create(request.getParameter("docId"), line);
        		bucket.upsert(ddoc);
       	 }
		return null;
	}

	@Override
	public Map<String, Object> makeRandomData(HttpServletRequest request) throws Exception {
		 Cluster cluster = CouchbaseCluster.create("localhost"); 
		 cluster.authenticate("Administrator", "dpsxndpa1!");
		 Bucket bucket = cluster.openBucket("travel-sample");
		 
		int docSize = Integer.parseInt(request.getParameter("docSize"));
		int docIdSize = Integer.parseInt(request.getParameter("docIdSize"));
		int docCount = Integer.parseInt(request.getParameter("docCount"));
		String docType = request.getParameter("docType");
		String loopYN = request.getParameter("loop");
		int threadCount = Integer.parseInt(request.getParameter("threadCount"));
		String docContent;
		
		if (docType.equals("Json")) {
		    docContent = "{\"a\":\"" + RandomStringUtils.randomAlphanumeric(docSize - 11) + "\"}";
		} else {
			docContent = RandomStringUtils.randomAlphanumeric(docSize);
		}
		Runnable couchTr = new CouchbaseThread(docContent, docCount, docIdSize, bucket);
		
		for(int i=0; i<threadCount; i++) {
			
			Thread t1 = new Thread(couchTr);
			t1.start();
	    }
		
		return null;
	}

}
