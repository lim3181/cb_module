package com.poc.spring.service;


import org.apache.commons.lang3.RandomStringUtils;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.StringDocument;

public class CouchbaseThread implements Runnable{
      static String docContent;
      static int docCount;
      static int docIdSize;
      static Bucket bucket;
     
    public CouchbaseThread(String docContent, int docCount, int docIdSize, Bucket bucket) {
    	this.docContent = docContent;
    	this.docCount = docCount;
    	this.docIdSize = docIdSize;
    	this.bucket = bucket;
    }
    public void run() {
        try {
        	System.out.println(docCount);
        	while (docCount > 0) {
        		--docCount;
        		System.out.println(docCount);
        		StringDocument ddoc = StringDocument.create(RandomStringUtils.randomAlphanumeric(docIdSize), docContent);
    			bucket.upsert(ddoc);
        	}
        }catch(Exception e) {

        }
    }
}
