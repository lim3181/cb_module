package com.poc.spring.dto;

public class DTO{	
		
	private String 	strHostName;
	private String 	strUserName;
	private String 	strPassword;
	private String 	strBucketName;
	private Long 	lKeyValueTO;
	private Long 	lViewTO;
	private Long 	lQueryTO;
	private Long 	lConnectTO;
	private Long 	lDisConnectTO;
	private Long 	lManagementTO;
	
	public DTO() {}
	public DTO(String strHostName, String strUserName, String strPassword, String strBucketName, long lKeyValueTO, 
			long lViewTO, long lQueryTO, long lConnectTO, long lDisConnectTO, long lManagementTO) {
		
		super();
		this.strHostName	= strHostName;
		this.strUserName 	= strUserName;
		this.strPassword 	= strPassword;
		this.strBucketName 	= strBucketName;
		this.lKeyValueTO 	= lKeyValueTO;
		this.lViewTO 		= lViewTO;
		this.lQueryTO 		= lQueryTO;
		this.lConnectTO 	= lConnectTO;
		this.lDisConnectTO 	= lDisConnectTO;
		this.lManagementTO 	= lManagementTO;

		
	}
	
	//get
	public String getHostName() {
		return strHostName;
	}
	public String getUserName() {
		return strUserName;
	}
	public String getPassword() {
		return strPassword;
	}
	public String getBucketName() {
		return strBucketName;
	}
	public long getKeyValueTO() {
		return lKeyValueTO;
	}
	public long getViewTO() {
		return lViewTO;
	}
	public long getQueryTO() {
		return lQueryTO;
	}
	public long getConnectTO() {
		return lConnectTO;
	}
	public long getDisConnectTO() {
		return lDisConnectTO;
	}
	public long getManagementTO() {
		return lManagementTO;
	}

	//set
	public void setHostName (String strHostName) {
		this.strHostName = strHostName;
	}
	public void setUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	public void setPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public void setBucketName(String strBucketName) {
		this.strBucketName = strBucketName;
	}
	public void setKeyValueTO(Long lKeyValueTO) {
        this.lKeyValueTO = lKeyValueTO;
    }
	public void setViewTO(Long lViewTO) {
        this.lViewTO = lViewTO;
    }
	public void setQueryTO(Long lQueryTO) {
        this.lQueryTO = lQueryTO;
    }
	public void setConnectTO(Long lConnectTO) {
        this.lConnectTO = lConnectTO;
    }
	public void setDisConnectTO(Long lDisConnectTO) {
        this.lDisConnectTO = lDisConnectTO;
    }
	public void setManagementTO(Long lManagementTO) {
        this.lManagementTO = lManagementTO;
    }
	
}

