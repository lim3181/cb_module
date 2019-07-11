package com.poc.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class CSVtoJSON {

	String strFilePathInput = "";
//	String strFilePathInput = "C:/upload/test.csv";
	private static String strEnc_eucKR = "euc-kr";
	private static String strEnc_utf8 = "utf-8";
	//static String strjson;

	/*
	 * public static void main(String[] args) throws IOException { String
	 * strFilePath =null; new CSVtoJSON().CSVtoJSON(strFilePath); }
	 */

	public List<Object> CSVtoJSON(String strFilePath) {
		List<Object> readAll = new ArrayList<Object>();
		try {
			
			
			/** File Input **/			
			FileInputStream fileInputStream = new FileInputStream(strFilePath);
			InputStreamReader is = new InputStreamReader(fileInputStream, strEnc_eucKR);


			CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
			CsvMapper csvMapper = new CsvMapper();		//csv 매핑

			/** Read data from CSV file **/
			
			readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(is).readAll();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return readAll;

	}


}
