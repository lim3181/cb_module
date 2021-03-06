package com.poc.spring.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.couchbase.client.deps.com.fasterxml.jackson.core.*;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.JsonArrayDocument;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.StringDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.opencsv.CSVReader;
import com.poc.spring.dto.ConnectDTO;

@Service
public class CouchbaseServiceImpl implements CouchbaseService {
	Cluster cluster;
	Bucket bucket;

	@Override
	public  Map<String, Object> excuteDataN1QL(HttpServletRequest request) throws Exception {

		 return null;
	}

	@Override
	public Map<String, Object> excuteSdkJob(HttpServletRequest request) throws Exception {

		String jobs = request.getParameter("sdkJobType");
		String docId = request.getParameter("sdkJobDocId");
		Map<String, Object> resultMap = new HashMap<String, Object>();

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
		 } else {
			 N1qlQueryResult result = bucket.query(N1qlQuery.simple(request.getParameter("sdkWriInput").toString()));
			 
			 resultMap.put("status", result.status());
			 resultMap.put("allRows", result.allRows().toString());
			 resultMap.put("error", result.errors().toString());
		 }
		 return resultMap;
	}

	@Override
	public Map<String, Object> uploadFile(MultipartHttpServletRequest mRequest) throws Exception {

		String strLocalPath = "C:/upload/"; 			// 로컬 업로드 경로
		File file = new File(strLocalPath);				// 로컬 경로 파일
		String strFilePath = "";						// 파일 경로 + 파일명
		String docID = mRequest.getParameter("docId");	// docID

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if (!file.isDirectory()) { 			// 파일 디렉토리 확인 및 디렉토리 생성
			file.mkdir();
		}
		
		MultipartFile multipartFile = mRequest.getFile("fileName"); 	// fileName Request
		String strOriginFileName = multipartFile.getOriginalFilename(); // Original FileName
		strFilePath = strLocalPath + strOriginFileName; 				// File Path

		int intSubstrDot = strOriginFileName.lastIndexOf("."); 					// 파일 확장자
		String strExtention = strOriginFileName.substring(intSubstrDot + 1); 	// 파일 확장자
		
		String strThreadCount = mRequest.getParameter("threadCount");
		int threadCnt = 0;
		if(!"".equals(strThreadCount)) {
			threadCnt = Integer.parseInt(strThreadCount);	 // thread Count
		}

		if(!multipartFile.isEmpty()) {			//파일이 선택됐을 경우
			multipartFile.transferTo(new File(strFilePath));		//FilePath에 파일 생성
			resultMap.put("suc","OK");

			if(StringUtils.isNotBlank(docID) && threadCnt>0) {			//문서 아이디가 공백이 아니며, 쓰레드 개수가 0 이상일 때
				if (strExtention.equals("csv")) {					//파일 확장자가 csv일 경우
					CSVtoJSON csvToJson = new CSVtoJSON(bucket, multipartFile, strFilePath, docID, threadCnt, strExtention);
					csvToJson.CSVtoJSON();
					resultMap.put("mapFlag","3");
					resultMap.put("csvInsert","csv 파일 \""+strOriginFileName+"\"가 insert 되었습니다.");
			
				} else if (strExtention.equals("json")) {			//파일 확장자가 json일 경우
					CSVtoJSON csvToJson = new CSVtoJSON(bucket, multipartFile, strFilePath, docID, threadCnt, strExtention);
					csvToJson.jsonUpload();
					resultMap.put("mapFlag","4");
					resultMap.put("jsonInsert","json 파일 \""+strOriginFileName+"\"이 insert 되었습니다.");
					
				} else {										//파일 확장자가 csv, json이 아닌 다른 것일 경우
					System.out.println(strOriginFileName);
					resultMap.put("mapFlag","2");
					resultMap.put("ExtentionsCheck","확장자가 csv 및 json인 파일을 선택해주세요.");
				}
			}
			else {
				resultMap.put("mapFlag","1");
				resultMap.put("idThreadCheck","문서 아이디와 쓰레드 개수에 빈칸 없이 입력해주세요.");
				System.out.println("docID or threadCnt is Null");
			}
		}else {
			resultMap.put("fileCheck","파일을 선택해주세요");
			System.out.println("File is not Selected");
		}

		return resultMap;

	}

	@Override
	public Map<String, Object> makeRandomData(HttpServletRequest request) throws Exception {

		int docSize = Integer.parseInt(request.getParameter("docSize"));
		int docIdSize = Integer.parseInt(request.getParameter("docIdSize"));
		int docCount = Integer.parseInt(request.getParameter("docCount"));
		String docType = request.getParameter("docType");
		String loopYN = request.getParameter("loop");
		int threadCount = Integer.parseInt(request.getParameter("threadCount"));
		String docContent;

		Runnable couchTr = new CouchbaseThread(docSize, docCount, docIdSize, bucket);

		for (int i = 0; i < threadCount; i++) {

			Thread t1 = new Thread(couchTr);
			t1.start();
		}

		return null;
	}
	
	@Override
	public Map<String, Object> connectionData(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");

		/** Connection Info **/
		String strHostName = request.getParameter("txtHostName");
		String strUserName = request.getParameter("txtUserName");
		String strPassword = request.getParameter("pwdPassword");
		String strBucketName = request.getParameter("txtBucketName");

		/** Timeout Info **/
		Long lKeyValueTO = Long.parseLong(request.getParameter("txtKeyValueTO"));
		Long lViewTO = Long.parseLong(request.getParameter("txtViewTO"));
		Long lQueryTO = Long.parseLong(request.getParameter("txtQueryTO"));
		Long lConnectTO = Long.parseLong(request.getParameter("txtConnectTO"));
		Long lDisConnectTO = Long.parseLong(request.getParameter("txtDisConnectTO"));
		Long lManagementTO = Long.parseLong(request.getParameter("txtManagementTO"));

		/** Bootstrap Info **/
		boolean isSslEnable = Boolean.parseBoolean(request.getParameter("rdoSslEnable"));
		String strSslKeyLoc = request.getParameter("txtSslKeyLoc");
		String strSslKeyPwd = request.getParameter("pwdSslKeyPwd");
		boolean isHttpEnable = Boolean.parseBoolean(request.getParameter("rdoHttpEnabled"));
		int intHttpDrtPort = Integer.parseInt(request.getParameter("txtHttpDirectPort"));
		int intHttpSslPort = Integer.parseInt(request.getParameter("txtHttpSslPort"));
		boolean isCarrEnable = Boolean.parseBoolean(request.getParameter("rdoCarrierEnable"));
		int intCarrDrtPort = Integer.parseInt(request.getParameter("txtCarrierDirectPort"));
		int intCarrSslPort = Integer.parseInt(request.getParameter("txtCarrierSslPort"));
		boolean isDnsSrvEnable = Boolean.parseBoolean(request.getParameter("rdoDnsSrvEnable"));
		boolean isMutatTknEnable = Boolean.parseBoolean(request.getParameter("rdoMutationTknEnable"));

		/** Reliability Info **/
		Long lMaxReqLifeTime = Long.parseLong(request.getParameter("txtMaxReqLifeTime"));
		Long lKeepAliveInterval = Long.parseLong(request.getParameter("txtKeepAliveInterval"));

		/** Performance Info **/
		int intKvEndpoints = Integer.parseInt(request.getParameter("txtKvEndpoints"));
		int intViewEndpoint = Integer.parseInt(request.getParameter("txtViewEndpoint"));
		int intQueryEndpoint = Integer.parseInt(request.getParameter("txtQueryEndpoint"));
		boolean isTcpNodelayEnable = Boolean.parseBoolean(request.getParameter("rdoTcpNodelayEnable"));

		/** Advanced Info **/
		int intRequestBufferSize = Integer.parseInt(request.getParameter("txtRequestBufferSize"));
		int intResponseBufferSize = Integer.parseInt(request.getParameter("txtResponseBufferSize"));
		boolean isBufferPoolEnab = Boolean.parseBoolean(request.getParameter("rdoBufferPoolEnab"));

		ConnectDTO dto = new ConnectDTO(strHostName, strUserName, strPassword, strBucketName, lKeyValueTO, lViewTO,
				lQueryTO, lConnectTO, lDisConnectTO, lManagementTO, isSslEnable, strSslKeyLoc, strSslKeyPwd,
				isHttpEnable, intHttpDrtPort, intHttpSslPort, isCarrEnable, intCarrDrtPort, intCarrSslPort,
				isDnsSrvEnable, isMutatTknEnable, lMaxReqLifeTime, lKeepAliveInterval, intKvEndpoints, intViewEndpoint,
				intQueryEndpoint, isTcpNodelayEnable, intRequestBufferSize, intResponseBufferSize, isBufferPoolEnab);

		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()

				/** Timeout Info **/
				.kvTimeout(lKeyValueTO).viewTimeout(lViewTO).queryTimeout(lQueryTO).connectTimeout(lConnectTO)
				.disconnectTimeout(lDisConnectTO).managementTimeout(lManagementTO)

				/** Bootstrap Info **/
				.sslEnabled(isSslEnable).sslKeystoreFile(strSslKeyLoc).sslKeystorePassword(strSslKeyPwd)
				.bootstrapHttpEnabled(isHttpEnable).bootstrapHttpDirectPort(intHttpDrtPort)
				.bootstrapHttpSslPort(intHttpSslPort).bootstrapCarrierEnabled(isCarrEnable)
				.bootstrapCarrierDirectPort(intCarrDrtPort).bootstrapCarrierSslPort(intCarrSslPort)
				.dnsSrvEnabled(isDnsSrvEnable).mutationTokensEnabled(isMutatTknEnable)

				/** Advanced Info **/
			  .requestBufferSize(intRequestBufferSize)
			  .responseBufferSize(intResponseBufferSize)		
			  .bufferPoolingEnabled(isBufferPoolEnab)		 
			  
			  .build();
				  
		
		cluster = CouchbaseCluster.create(env,dto.getStrHostName()); 
		cluster.authenticate(dto.getStrUserName(),dto.getStrPassword());
		bucket = cluster.openBucket(dto.getStrBucketName());

		return null;

	}

	@Override
	public Map<String, Object> createBucket(HttpServletRequest request) {
		Cluster createBucketCluster = CouchbaseCluster.create(request.getParameter("hostName")); 
		ClusterManager clusterManager = createBucketCluster.clusterManager(request.getParameter("userName"), request.getParameter("userPassword"));
		if (request.getParameter("newBucketType").equals("Couchbase")) {
			BucketSettings bucketSettings = new DefaultBucketSettings.Builder()
				    .type(BucketType.COUCHBASE)
				    .name(request.getParameter("newBucketName"))
				    .password("")
				    .quota(Integer.parseInt(request.getParameter("bucketMemory"))) // megabytes
				    .replicas(Integer.parseInt(request.getParameter("newBucketReplicas")))
				    .indexReplicas(Boolean.parseBoolean(request.getParameter("indexReplicaEnable")))
				    .enableFlush(Boolean.parseBoolean(request.getParameter("flushEnable")))
				    .build();
			clusterManager.insertBucket(bucketSettings);
				
		} else if (request.getParameter("newBucketType").equals("Ephmeral")) {
			BucketSettings bucketSettings = new DefaultBucketSettings.Builder()
				    .type(BucketType.EPHEMERAL)
				    .name(request.getParameter("newBucketName"))
				    .password("")
				    .quota(Integer.parseInt(request.getParameter("bucketMemory"))) // megabytes
				    .replicas(Integer.parseInt(request.getParameter("newBucketReplicas")))
				    .indexReplicas(Boolean.parseBoolean(request.getParameter("indexReplicaEnable")))
				    .enableFlush(Boolean.parseBoolean(request.getParameter("flushEnable")))
				    .build();
			clusterManager.insertBucket(bucketSettings);
		} else {
			BucketSettings bucketSettings = new DefaultBucketSettings.Builder()
				    .type(BucketType.MEMCACHED)
				    .name(request.getParameter("newBucketName"))
				    .password("")
				    .quota(Integer.parseInt(request.getParameter("bucketMemory"))) // megabytes
				    .replicas(Integer.parseInt(request.getParameter("newBucketReplicas")))
				    .indexReplicas(Boolean.parseBoolean(request.getParameter("indexReplicaEnable")))
				    .enableFlush(Boolean.parseBoolean(request.getParameter("flushEnable")))
				    .build();
			clusterManager.insertBucket(bucketSettings);
		}
		return null;
	}
}
