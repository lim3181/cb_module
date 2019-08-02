package com.poc.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.StringDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVtoJSON {

	static Bucket bucket;
	static MultipartFile multipartFile;
	static String strFilePath;
	static String docID;
	static int threadCnt;
	static String strExtention;

	public static String strEnc_eucKR = "euc-kr";
	public static String strEnc_utf8 = "utf-8";
	

	public CSVtoJSON(Bucket bucket, MultipartFile multipartFile, String strFilePath, String docID, int threadCnt, String strExtention) {

		this.bucket = bucket;
		this.multipartFile = multipartFile;
		this.strFilePath = strFilePath;
		this.docID = docID;
		this.threadCnt = threadCnt;
		this.strExtention = strExtention;

	}

	public List<Object> CSVtoJSON() throws IOException {

		/** File Input **/
		FileInputStream fileInputStream = new FileInputStream(strFilePath);
		InputStreamReader is = new InputStreamReader(fileInputStream, strEnc_eucKR);
		
		try {

			CsvMapper csvMapper = new CsvMapper(); 			// csv 매핑
			CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
			csvMapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);

			MappingIterator<JsonObject> it = csvMapper.readerFor(Map.class).with(csvSchema).readValues(is);		//매핑된 것을 it 객체에 삽입

			Runnable csvJsonThread = new CsvJsonThread(bucket, docID, it, strExtention);		//쓰레드

			for (int i = 0; i < threadCnt; i++) {		//쓰레드
				Thread thread = new Thread(csvJsonThread);
				thread.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return null;

	}
	public void jsonUpload() throws IOException {
		
		/** File Input **/
		FileInputStream fileInputStream = new FileInputStream(strFilePath);
		InputStreamReader is = new InputStreamReader(fileInputStream, strEnc_eucKR);
		
		try {
			
			ObjectMapper objMapper = new ObjectMapper();		//object 매핑
			ObjectReader objReader = objMapper.reader(Map.class);
			objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

			MappingIterator<JsonObject> it = objReader.readValues(is);		//매핑된 것을 it 객체에 삽입

			Runnable csvJsonThread = new CsvJsonThread(bucket, docID, it, strExtention);
			
			for (int i = 0; i < threadCnt; i++) {
				Thread thread = new Thread(csvJsonThread);
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
