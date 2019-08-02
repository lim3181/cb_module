package com.poc.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.multipart.MultipartFile;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.StringDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;

public class CsvJsonThread implements Runnable {

	static Bucket bucket;
	static MultipartFile multipartFile;
	static String strFilePath;
	static String docID;
	static MappingIterator<JsonObject> it;
	static String strExtention;
	static int a = 0;

	public CsvJsonThread(Bucket bucket, String docID, MappingIterator<JsonObject> it, String strExtention) {

		this.bucket = bucket;
		this.docID = docID;
		this.it = it;
		this.strExtention = strExtention;

	}

	public void run() throws NoSuchElementException {

		if (strExtention.equals("csv")) { // 파일 확장자가 csv인 경우
			try {

				synchronized (it) { // it synchronized.
					while (it.hasNext()) {
						int temp;
						List<Object> result = new ArrayList<Object>();
						
						result.add(it.next());
						a++;
						temp = a;
						JsonObject jsonObj = JsonObject.create(); // JsonObject
						jsonObj.put("jsonObject", result);

						// insert
						StringDocument doc = StringDocument.create(docID + "_" + temp, jsonObj.getArray("jsonObject").get(0).toString());
						System.out.println(doc);
						bucket.insert(doc);
					}
					a = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (strExtention.equals("json")) {	// 파일 확장자가 json인 경우
			try {
				synchronized (it) { // it synchronized.

					while (it.hasNext()) {
						int temp;
						List<Object> result = new ArrayList<Object>();

						result.add(it.next());
						a++;
						temp = a;

						JsonObject jsonObj = JsonObject.create();	// JsonObject
						jsonObj.put("jsonObject", result);

						// insert
						StringDocument doc = StringDocument.create(docID + "_" + temp, jsonObj.getArray("jsonObject").get(0).toString());
						System.out.println(doc);
						bucket.insert(doc);
					}

					a = 0;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
