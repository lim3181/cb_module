package com.poc.spring.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.poc.spring.dto.DTO;

public class DAO {

	Cluster cluster = CouchbaseCluster.create("${strHostName}");
	Bucket bucket = cluster.openBucket("${strBucketName}", "${strPassword}");
	
	
}
