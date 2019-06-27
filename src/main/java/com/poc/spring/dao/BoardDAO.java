package com.poc.spring.dao;

import javax.swing.JOptionPane;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.poc.spring.dto.ConnectDTO;

public class BoardDAO {

	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}		
	public static BoardDAO getInstance() {		
		if (instance == null) {					
			instance = new BoardDAO();
		}
		return instance;	
	}
	public void BoardDAO(ConnectDTO dto) {

		try {
			String getHostName = dto.getStrHostName();
			String getUserName = dto.getStrUserName();
			String getPassword = dto.getStrPassword();
			String getBucketName = dto.getStrBucketName();
			
			Cluster cluster = CouchbaseCluster.create(getHostName); 
			cluster.authenticate(getUserName,getPassword);
			Bucket bucket = cluster.openBucket(getBucketName);
			
			AsyncBucket asyncBucket = bucket.async();
			
			
		
		}
		catch(Exception e) {
			e.printStackTrace();		
		}
	}
}
