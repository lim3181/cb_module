<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Couchbase</title>
<link rel="stylesheet" type="text/css" href="/static/css/index.css">
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
function n1qlExcute(){
    var queryString = jQuery("#n1qlForm").serializeArray();

    $.ajax({
        type : "post",
        url : "/n1qlExcute",
        data : queryString,
        dataType : 'json',
        error: function(xhr, status, error){
            alert(error);
        },
        success : function(data){
	        var obj = eval(data);
			if (obj.status == "success"){
				$('#n1qlOutput').val(obj.allRows)
			}
			else {
				$('#n1qlOutput').val(obj.error)
			}
        }
    });

}

function sdkJobExcute(){
    var data = jQuery("#sdkJobsForm").serializeArray();

    $.ajax({
        type : "post",
        url : "/sdkJobExcute",
        data : data,
        error: function(xhr, status, error){
        	$('#sdkJobOutput').val("에러가 발생하였습니다. 문서의 ID가 중복 혹은 존재하지 않습니다.")
        },
        success : function(data){
        	
	        var obj = eval(data);
			if (obj.status == "success"){
				$('#sdkJobOutput').val(obj.allRows)
			    var ugly = document.getElementById('sdkJobOutput').value;
			    var obj = JSON.parse(ugly);
			    var pretty = JSON.stringify(obj, undefined, 4);
			    document.getElementById('sdkJobOutput').value = pretty;
			}
			else if (obj.status != null){
				$('#sdkJobOutput').val(obj.error)
			} else {
				$('#sdkJobOutput').val(obj.result)
			    var ugly = document.getElementById('sdkJobOutput').value;
			    var obj = JSON.parse(ugly);
			    var pretty = JSON.stringify(obj, undefined, 4);
			    document.getElementById('sdkJobOutput').value = pretty;
			}
		}
     });
}

function test(){
    var data = jQuery("#fileUpload").serializeArray();

    $.ajax({
        type : "POST",
        enctype : "multipart/form-data",
        url : "/fileUpload",
        data : data,
        processData : false,
        contentType : false,
        error: function(xhr, status, error){
        	$('#n1ql').val("해당 문서의 아이디가 없습니다.")
        },
        success : function(data){
	        var obj = eval(data);
			if (obj.status == "success"){
	        	alert("SUCCESS");
				$('#n1ql').val(obj.allRows)
			}
			else {
	        	alert("ETC ERROR");
				$('#n1ql').val(obj.error)
			}
        
        }
        
    });
}

function createNewBucket(){
    var data = jQuery("#createBucket").serializeArray();

     $.ajax({
        type : "post",
        url : "/createBucket",
        data : data,
        error: function(xhr, status, error){
        },
        success : function(data){
			alert("버킷이 생성되었습니다.");
        }
    }); 
}

function randomData(){
    var data = jQuery("#randomDataForm").serializeArray();

    $.ajax({
        type : "post",
        url : "/randomData",
        data : data,
        error: function(xhr, status, error){
        },
        success : function(data){
        }
    });
}


function connectionData(){
    var data = jQuery("#conDataForm").serializeArray();
    
    $.ajax({
        type : "post",
        url : "/conData",
        data : data,
        error: function(xhr, status, error){
        },
        success : function(data){
        }
    });
	
	
}

function jobChange(obj){
	var job = obj.value;
	if (job == "n1ql"){
		document.getElementById('sdkWriInput').style.visibility = 'visible'
		document.getElementById('docIdTextLine').style.visibility = 'hidden'
	} else if (job == "write") {
		document.getElementById('sdkWriInput').style.visibility = 'visible'
		document.getElementById('docIdTextLine').style.visibility = 'visible'	
	} 
	else {
		document.getElementById('sdkWriInput').style.visibility = 'hidden'
		document.getElementById('docIdTextLine').style.visibility = 'visible'
	}
}
function uploadFile(){
    var data = new FormData(document.getElementById('fileUpload'));
     $.ajax({
        type : "post",
        url : "/fileUpload",
        enctype: "multipart/form-data",
        data : data,
        processData : false,
        contentType : false,
        error : function(xhr, status, error) {   
        	$('#uploadOut').val("업로드 처리 오류");
        	console.log(error);
        	console.log(error.status);
        },
        //어떤 오류 자세하게

        success : function(data){
			var obj = eval(data);
			alert(obj.status);
			if (obj.status == "success"){
				$('#uploadOut').val(obj.allRows)
			}
			
        }
        
    }); 
}


</script>
<body>
<div id="header">
	<a>
      <img src="/static/image/cb_logo_bug_white_2.svg" alt="Couchbase Server" class="logobug">
    </a>
</div>

<!-- 서버 연결 및 환경 구성 -->
<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:70%">&nbsp&nbsp&nbsp&nbsp서버 연결 및 환경 구성</h1>
		<h1>랜덤 데이터 생성</h1>
		<h1>버킷 생성</h1>
	</div>
	<div class="float-frame">
	<form id="conDataForm" name="conDataForm" >
		<div class="float-division">
			<div class="big-area" style="height:93%; width:320px" >
				<div style="margin-left:20px; font-weight:bold">Connection
				</div><br />
			
				<div class="fieldlabel">*호스트명</div>	
				<div class="formfield"><input type="text" name="txtHostName" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*유 저 명</div>	
				<div class="formfield"><input type="text" name="txtUserName" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*패스워드</div>	
				<div class="formfield">	<input type="password" name="pwdPassword" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*버 킷 명</div>	
				<div class="formfield"><input type="text" name="txtBucketName" size="10"/><br /><br /></div>
				<br />
				
				
				<div style="margin-left:20px; font-weight:bold"></br>Time Out Option
				</div><br />
				
				<div class="fieldlabelto">Key-Value 타임아웃 </div>		
				<div class="formfield"><input type="text" name="txtKeyValueTO" size="10" value=2500 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelto">View 타임아웃 </div>				
				<div class="formfield"><input type="text" name="txtViewTO" size="10" value=75000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelto">Query 타임아웃 </div>			
				<div class="formfield"><input type="text" name="txtQueryTO" size="10" value=75000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelto">Connect 타임아웃 </div>			
				<div class="formfield"><input type="text" name="txtConnectTO" size="10" value=5000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelto">Disconnect 타임아웃 </div>		
				<div class="formfield"><input type="text" name="txtDisConnectTO" size="10" value=25000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
					
				<div class="fieldlabelto">Management 타임아웃 </div>		
				<div class="formfield"><input type="text" name="txtManagementTO" size="10" value=75000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
			
			</div>
			
	
			
			<div class="big-area" style="height:93%; width:320px" >
				<div style="margin-left:20px; font-weight:bold">Bootstrap Option
				</div><br />
				
				<div class="fieldlabelbs">암호화 사용</div>
				<div class="formfield">	<input type="radio" name="rdoSslEnable" value="true"/>true 
										<input type="radio" name="rdoSslEnable" value="false" checked/>false<br /><br /></div>
										
				<div class="fieldlabelbs">SSL 키 저장소 위치</div>
				<div class="formfield"><input type="text" name="txtSslKeyLoc" size="10" value=""/><br /><br /></div>
				
				<div class="fieldlabelbs">SSL 키 저장소 비밀번호 </div>
				<div class="formfield"><input type="password" name="pwdSslKeyPwd" size="10" value=""/><br /><br /></div>
				
				<div class="fieldlabelbs">HTTP를 통한 config 로드</div>
				<div class="formfield">	<input type="radio" name="rdoHttpEnabled" value="true" checked/>true
										<input type="radio" name="rdoHttpEnabled" value="false"/>false<br /><br /></div>
										
				<div class="fieldlabelbs">HTTP 비암호화 포트 config </div>
				<div class="formfield"><input type="text" name="txtHttpDirectPort" size="10" value=8091 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelbs">HTTP 암호화 포트 config </div>
				<div class="formfield"><input type="text" name="txtHttpSslPort" size="10" value=18091 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelbs">Carrier Publication을 통해 config 로드</div>
				<div class="formfield">	<input type="radio" name="rdoCarrierEnable" value="true" checked/>true
										<input type="radio" name="rdoCarrierEnable" value="false"/>false<br /><br /><br /></div>
										
				<div class="fieldlabelbs">Carrier 비암호화 포트 config </div>
				<div class="formfield"><input type="text" name="txtCarrierDirectPort" size="10" value=11210 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelbs">Carrier 암호화 포트 config</div>
				<div class="formfield"><input type="text" name="txtCarrierSslPort" size="10" value=11207 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelbs">DNS SRV 사용 </div>
				<div class="formfield">	<input type="radio" name="rdoDnsSrvEnable" value="true"/>true
										<input type="radio" name="rdoDnsSrvEnable" value="false" checked/>false<br /><br /></div>
										
				<div class="fieldlabelbs">사용 가능한 변조 토큰 </div>
				<div class="formfield">	<input type="radio" name="rdoMutationTknEnable" value="true"/>true
										<input type="radio" name="rdoMutationTknEnable" value="false" checked/>false<br /><br /></div>
										
										<br /><br />    
			</div>
			
			
			<div class="big-area" style="height:93%; width:320px" >
				<div style="margin-left:20px; font-weight:bold">Reliability option
				</div><br />
				
				<div class="fieldlabelrb">최대 요청 Lifetime</div>
				<div class="formfield"><input type="text" name="txtMaxReqLifeTime" size="10" value=75000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelrb">소켓 유지 시간</div>	
				<div class="formfield"><input type="text" name="txtKeepAliveInterval" size="10" value=30000 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
	 	
				<br />
				<div style="margin-left:20px; font-weight:bold">Performance option
				</div><br />
				
				<div class="fieldlabelpm">노드당 key/value Endpoint</div>	
				<div class="formfield"><input type="text" name="txtKvEndpoints" size="10" value=1 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelpm">노드당 View Endpoint</div> 
				<div class="formfield"><input type="text" name="txtViewEndpoint" size="10" value=1 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelpm">노드당 Query Endpoint </div>	
				<div class="formfield"><input type="text" name="txtQueryEndpoint" size="10" value=1 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
				
				<div class="fieldlabelpm">TCP Nodelay</div> 	
				<div class="formfield">	<input type="radio" name="rdoTcpNodelayEnable" value="true" checked/>true
										<input type="radio" name="rdoTcpNodelayEnable" value="false"/>false<br /><br /></div>
			
			
				<br />
				<div style="margin-left:20px; font-weight:bold">Advanced option
				</div><br />
				
				<div class="fieldlabelad">Ring 버퍼 사이즈 요청 </div>	
				<div class="formfield"><input type="text" name="txtRequestBufferSize" size="10" value=16384 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
					
				<div class="fieldlabelad">Ring 버퍼 사이즈 응답 </div>	
				<div class="formfield"><input type="text" name="txtResponseBufferSize" size="10" value=16384 onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br /></div>
					
				<div class="fieldlabelad">버퍼 풀 활성화</div>	
				<div class="formfield">	<input type="radio" name="rdoBufferPoolEnab" value="true" checked>true
										<input type="radio" name="rdoBufferPoolEnab" value="false">false<br /><br /></div>	
			
				<div align="right">
					<button class="n1qlexcute" onclick="connectionData();">저장</button></div>
			</div>
		</div>
	</form>
		
		
	<form id="randomDataForm" name="randomDataForm" >	
		<div class="float-division" >
		
			<div class="big-area" style="height:93%; width:320px" >
				<div style="margin-left:20px; font-weight:bold">Create Random Data
				</div><br />
				<div class="fieldlabelrd">*아이디 사이즈 </div>	
				<div class="formfield"><input type="text" id ="docIdSize" name="docIdSize" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>byte<br /><br /></div>
				
				<div class="fieldlabelrd">*문서 사이즈 </div>	
				<div class="formfield"><input type="text" id ="docSize" name="docSize" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>byte<br /><br /></div>
				
   				<div class="fieldlabelrd">*생성할 문서의 수 </div>
   				<div class="formfield"><input type="text" name="docCount" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>건<br /><br /></div>
   				
				<div class="fieldlabelrd">*문서 종류</div>
				<div class="formfield">	<input type="radio" name="docType" value="Json" /> json
										<input type="radio" name="docType" value="Binary" /> binary<br /><br />    </div>
									
				<!--작업 반복 여부 : 	<input type="radio" name="loop" value="Ture" /> true
										<input type="radio" name="loop" value="False" /> false<br /><br /> -->
										
				<div class="fieldlabelrd">*쓰레드 개수</div>
				<div class="formfield"><input type="text" name="threadCount" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>개<br /><br />	</div>
				
				<div class="relative" align="right">
					<button class="n1qlexcute" onclick="randomData();">실행</button>
				</div>
			</div>
		</div>
	
	</form>
		
	<form id="createBucket" name="createBucket" >	
		<div class="float-division" >
		
			<div class="big-area" style="height:93%; width:320px" >
				<div style="margin-left:20px; font-weight:bold">createBucket
				</div><br />
				<div class="fieldlabel">*호스트명</div>	
				<div class="formfield"><input type="text" name="hostName" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*유 저 명</div>	
				<div class="formfield"><input type="text" name="userName" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*패스워드</div>	
				<div class="formfield">	<input type="password" name="userPassword" size="10"/><br /><br /></div>
				<div class="fieldlabel">*버킷명</div>	
				<div class="formfield"><input type="text" id ="newBucketName" name="newBucketName" size="10"/><br /><br /></div>
				
				<div class="fieldlabel">*버킷타입 </div>	
				<div class="formfield">
					<select name="newBucketType"> <option selected  value="Couchbase">Couchbase</option> <option value="Ephmeral">Ephmeral</option> <option value="Memcached">Memcached</option> </select>
				<br /><br /></div>
				
   				<div class="fieldlabel">*메모리 할당</div>
   				<div class="formfield"><input type="text" name="bucketMemory" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>MB<br /><br /></div>
   				
				<div class="fieldlabel">*복제본 수</div>
				<div class="formfield">	
					<select name="newBucketReplicas"> <option selected  value="1">1</option> <option value="2">2</option> <option value="3">3</option> </select><br /><br />
				<div class="fieldlabel">*Index 복제 여부</div>
				<div class="formfield"><input type="radio" name="indexReplicaEnable" value="true" checked>true
										<input type="radio" name="indexReplicaEnable" value="false">false<br /><br /></div>	
				<div class="fieldlabel">*Flush 기능 활성 여부</div>
   				<div class="formfield"><input type="radio" name="flushEnable" value="true" checked>true
										<input type="radio" name="flushEnable" value="false">false<br /><br /></div>	
				<div align="right">
					<button class="n1qlexcute" onclick="createNewBucket();">저장</button></div>
			</div>
			</div>
		</div>
	
	</form>	
		
	</div>
<!-- //float-frame -->
</div>

<!--  
<div class="layout-wrap">
N1QL 실행창
<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:53%">N1QL 실행창</h1>
		<h1 >N1QL 결과창</h1>
	</div>
	<div class="float-frame">
		<div class="float-unit" style="margin-left: 2%;">	
			<form id="n1qlForm" name="n1qlForm">		
				<textarea id="n1qlInput" name="n1qlInput" placeholder="쿼리를 입력하고 실행 버튼을 누르세요." rows="5" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; "></textarea>
			</form>
				<button class="n1qlexcute" onclick="n1qlExcute();">실행</button>

		</div>
		<div class="float-unit" style="margin-left: 2%;">
			<textarea id="n1qlOutput" name="n1qlOutput" placeholder="쿼리를 실행해주세요." rows="5" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly></textarea>
		</div>
		<div class="clear"> </div>
	</div>
</div> -->

<!-- 문서 ID 작업 -->
<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:53%">문서 작업</h1>
		<h1 >작업 결과 창</h1>
	</div>
	<!-- float-frame -->
	<div class="float-frame" style="height:400px">
		<div class="float-unit" style="margin-left: 2%; height:400px;">
		<form id="sdkJobsForm" name="sdkJobsForm">	
작업 종류 : 	
			<input type="radio" name="sdkJobType" value="n1ql" onclick="jobChange(this)" /> N1QL
			<input type="radio" name="sdkJobType" value="read" onclick="jobChange(this)"/> 읽기
			<input type="radio" name="sdkJobType" value="write" onclick="jobChange(this)"/> 쓰기
			<input type="radio" name="sdkJobType" value="delete" onclick="jobChange(this)"/> 삭제<br /><br />  
			<div id ="docIdTextLine" name="docIdTextLine" style="visibility:hidden;">
								문서 아이디
			<input id ="sdkJobDocId" name=sdkJobDocId /><br /><br />  
			</div>  
	
			<textarea id="sdkWriInput" name="sdkWriInput" placeholder="쓰기 작업 수행 시 json 문서를, N1QL 작성 시 쿼리문을 작성해주세요."  rows="5" cols="33" style="margin: 0px; width: 90%; height: 180px;visibility:hidden; background-color: #eee; "></textarea>
		</form>
		<button class="n1qlexcute" onclick="sdkJobExcute();">실행</button>
			
		</div>
		<div class="float-unit" style="margin-left: 2%;">
			<textarea id="sdkJobOutput" name="sdkJobOutput"rows="5" placeholder="작업을 실행해주세요."cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly></textarea>
		</div>
		<div class="clear"> </div>
	</div>
</div>


<!-- Json 및 txt 파일 Upsert -->
<div class="layout-wrap">
	<div>
		<h1 >Json 및 txt 파일 Upsert</h1>
	</div>
	<div class="float-frame">
		<div class="float-unit" style="margin-left: 2%; height:80px;">
		<form id="fileUpload" name="fileUpload" enctype="multipart/form-data">
			문서 아이디 : <input  id ="docId" name="docId" required="required"/><br />	
			쓰레드 개수 : <input type="text" name="threadCount" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>개<br /><br />
			파일 경로 : <input id ="fileName" name="fileName" type=file accept=".csv, .json" required="required">
		</form>	
			<button type="submit" class="n1qlexcute" onclick="uploadFile();">파일 Upsert 실행</button>
		
		</div>
		<div class="float-unit" style="margin-left: 2%; height:80px;">
			<textarea id="uploadOut" name="uploadOut" rows="2" placeholder="성공 or 실패" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly></textarea>
		</div>
		<div class="clear"> </div>
	</div>
</div> 
</body>
</html>