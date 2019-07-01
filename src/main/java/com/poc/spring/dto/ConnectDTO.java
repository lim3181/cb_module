package com.poc.spring.dto;

public class ConnectDTO{	
		
	
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
	
	
	private Boolean	isSslEnable;
	private String 	strSslKeyLoc;
	private String 	strSslKeyPwd;
	private Boolean	isHttpEnable;
	private int 	intHttpDrtPort;
	private int 	intHttpSslPort;
	private Boolean	isCarrEnable;
	private int 	intCarrDrtPort;
	private int 	intCarrSslPort;
	private Boolean	isDnsSrvEnable;
	private Boolean	isMutatTknEnable;

	
	private Long lMaxReqLifeTime;
	private Long lKeepAliveInterval;

	
	private int intKvEndpoints;
	private int intViewEndpoint;
	private int intQueryEndpoint;
	private boolean isTcpNodelayEnable;

	
	private int intRequestBufferSize;
	private int intResponseBufferSize;
	private boolean isBufferPoolEnab;
	
	
	
	public ConnectDTO() {}
	public ConnectDTO(
			String strHostName, String strUserName, String strPassword, String strBucketName, long lKeyValueTO, 							/**/
			long lViewTO, long lQueryTO, long lConnectTO, long lDisConnectTO, long lManagementTO,
			Boolean isSslEnable, String strSslKeyLoc, String strSslKeyPwd, Boolean isHttpEnable, int intHttpDrtPort, int intHttpSslPort, 	/**/
			Boolean isCarrEnable, int intCarrDrtPort, int intCarrSslPort, Boolean isDnsSrvEnable, Boolean isMutatTknEnable,
			Long lMaxReqLifeTime, Long lKeepAliveInterval,																					/**/
			int intKvEndpointse, int intViewEndpoint, int intQueryEndpoint, boolean isTcpNodelayEnable,										/**/
			int intRequestBufferSize, int intResponseBufferSize, boolean isBufferPoolEnab													/**/
		) {
		
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
		
		this.isSslEnable		= isSslEnable;
		this.strSslKeyLoc 		= strSslKeyLoc;
		this.strSslKeyPwd 		= strSslKeyPwd;
		this.isHttpEnable 		= isHttpEnable;
		this.intHttpDrtPort		= intHttpDrtPort;
		this.intHttpSslPort 	= intHttpSslPort;
		this.isCarrEnable 		= isCarrEnable;
		this.intCarrDrtPort 	= intCarrDrtPort;
		this.intCarrSslPort 	= intCarrSslPort;
		this.isDnsSrvEnable 	= isDnsSrvEnable;
		this.isMutatTknEnable	= isMutatTknEnable;
		
		this.lMaxReqLifeTime	= lMaxReqLifeTime;
		this.lKeepAliveInterval = lKeepAliveInterval;
		
		this.intKvEndpoints		= intKvEndpoints;
		this.intViewEndpoint 	= intViewEndpoint;
		this.intQueryEndpoint 	= intQueryEndpoint;
		this.isTcpNodelayEnable	= isTcpNodelayEnable;
		
		this.intRequestBufferSize	= intRequestBufferSize;
		this.intResponseBufferSize	= intResponseBufferSize;
		this.isBufferPoolEnab 		= isBufferPoolEnab;
		
	}
	public String getStrHostName() {
		return strHostName;
	}
	public void setStrHostName(String strHostName) {
		this.strHostName = strHostName;
	}
	public String getStrUserName() {
		return strUserName;
	}
	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getStrBucketName() {
		return strBucketName;
	}
	public void setStrBucketName(String strBucketName) {
		this.strBucketName = strBucketName;
	}
	public Long getlKeyValueTO() {
		return lKeyValueTO;
	}
	public void setlKeyValueTO(Long lKeyValueTO) {
		this.lKeyValueTO = lKeyValueTO;
	}
	public Long getlViewTO() {
		return lViewTO;
	}
	public void setlViewTO(Long lViewTO) {
		this.lViewTO = lViewTO;
	}
	public Long getlQueryTO() {
		return lQueryTO;
	}
	public void setlQueryTO(Long lQueryTO) {
		this.lQueryTO = lQueryTO;
	}
	public Long getlConnectTO() {
		return lConnectTO;
	}
	public void setlConnectTO(Long lConnectTO) {
		this.lConnectTO = lConnectTO;
	}
	public Long getlDisConnectTO() {
		return lDisConnectTO;
	}
	public void setlDisConnectTO(Long lDisConnectTO) {
		this.lDisConnectTO = lDisConnectTO;
	}
	public Long getlManagementTO() {
		return lManagementTO;
	}
	public void setlManagementTO(Long lManagementTO) {
		this.lManagementTO = lManagementTO;
	}
	public Boolean getIsSslEnable() {
		return isSslEnable;
	}
	public void setIsSslEnable(Boolean isSslEnable) {
		this.isSslEnable = isSslEnable;
	}
	public String getStrSslKeyLoc() {
		return strSslKeyLoc;
	}
	public void setStrSslKeyLoc(String strSslKeyLoc) {
		this.strSslKeyLoc = strSslKeyLoc;
	}
	public String getStrSslKeyPwd() {
		return strSslKeyPwd;
	}
	public void setStrSslKeyPwd(String strSslKeyPwd) {
		this.strSslKeyPwd = strSslKeyPwd;
	}
	public Boolean getIsHttpEnable() {
		return isHttpEnable;
	}
	public void setIsHttpEnable(Boolean isHttpEnable) {
		this.isHttpEnable = isHttpEnable;
	}
	public int getIntHttpDrtPort() {
		return intHttpDrtPort;
	}
	public void setIntHttpDrtPort(int intHttpDrtPort) {
		this.intHttpDrtPort = intHttpDrtPort;
	}
	public int getIntHttpSslPort() {
		return intHttpSslPort;
	}
	public void setIntHttpSslPort(int intHttpSslPort) {
		this.intHttpSslPort = intHttpSslPort;
	}
	public Boolean getIsCarrEnable() {
		return isCarrEnable;
	}
	public void setIsCarrEnable(Boolean isCarrEnable) {
		this.isCarrEnable = isCarrEnable;
	}
	public int getIntCarrDrtPort() {
		return intCarrDrtPort;
	}
	public void setIntCarrDrtPort(int intCarrDrtPort) {
		this.intCarrDrtPort = intCarrDrtPort;
	}
	public int getIntCarrSslPort() {
		return intCarrSslPort;
	}
	public void setIntCarrSslPort(int intCarrSslPort) {
		this.intCarrSslPort = intCarrSslPort;
	}
	public Boolean getIsDnsSrvEnable() {
		return isDnsSrvEnable;
	}
	public void setIsDnsSrvEnable(Boolean isDnsSrvEnable) {
		this.isDnsSrvEnable = isDnsSrvEnable;
	}
	public Boolean getIsMutatTknEnable() {
		return isMutatTknEnable;
	}
	public void setIsMutatTknEnable(Boolean isMutatTknEnable) {
		this.isMutatTknEnable = isMutatTknEnable;
	}
	public Long getlMaxReqLifeTime() {
		return lMaxReqLifeTime;
	}
	public void setlMaxReqLifeTime(Long lMaxReqLifeTime) {
		this.lMaxReqLifeTime = lMaxReqLifeTime;
	}
	public Long getlKeepAliveInterval() {
		return lKeepAliveInterval;
	}
	public void setlKeepAliveInterval(Long lKeepAliveInterval) {
		this.lKeepAliveInterval = lKeepAliveInterval;
	}
	public int getIntKvEndpoints() {
		return intKvEndpoints;
	}
	public void setIntKvEndpoints(int intKvEndpoints) {
		this.intKvEndpoints = intKvEndpoints;
	}
	public int getIntViewEndpoint() {
		return intViewEndpoint;
	}
	public void setIntViewEndpoint(int intViewEndpoint) {
		this.intViewEndpoint = intViewEndpoint;
	}
	public int getIntQueryEndpoint() {
		return intQueryEndpoint;
	}
	public void setIntQueryEndpoint(int intQueryEndpoint) {
		this.intQueryEndpoint = intQueryEndpoint;
	}
	public boolean isTcpNodelayEnable() {
		return isTcpNodelayEnable;
	}
	public void setTcpNodelayEnable(boolean isTcpNodelayEnable) {
		this.isTcpNodelayEnable = isTcpNodelayEnable;
	}
	public int getIntRequestBufferSize() {
		return intRequestBufferSize;
	}
	public void setIntRequestBufferSize(int intRequestBufferSize) {
		this.intRequestBufferSize = intRequestBufferSize;
	}
	public int getIntResponseBufferSize() {
		return intResponseBufferSize;
	}
	public void setIntResponseBufferSize(int intResponseBufferSize) {
		this.intResponseBufferSize = intResponseBufferSize;
	}
	public boolean isBufferPoolEnab() {
		return isBufferPoolEnab;
	}
	public void setBufferPoolEnab(boolean isBufferPoolEnab) {
		this.isBufferPoolEnab = isBufferPoolEnab;
	}
	
}

