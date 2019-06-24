<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Couchbase</title>
<link rel="stylesheet" type="text/css" href="/static/css/index.css">
</head>
<script>
</script>
<body>
<div id="header">
	<a>
      <img src="/static/image/cb_logo_bug_white_2.svg" alt="Couchbase Server" class="logobug">
    </a>
</div>
<div class="layout-wrap">
	<h1>랜덤 데이터 생성</h1>
	<div class="float-frame">
		<div class="big-area" >
			<form name="example_input" action="request" method="post">
	호스트명 :				<input type="text" name="txtHostName" size="10"/><br /><br />
	유저명 :				<input type="text" name="txtUserName" size="10"/><br /><br />
	패스워드 :				<input type="password" name="pwdPassword" size="10"/><br /><br />
	버켓명 :				<input type="text" name="txtBucketName" size="10"/><br /><br />
	Key-Value 타임아웃 :	<input type="text" name="txtKeyValueTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
	View 타임아웃 :			<input type="text" name="txtViewTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
	Query 타임아웃 :		<input type="text" name="txtQueryTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
	Connect 타임아웃 :		<input type="text" name="txtConnectTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
	DisConnect 타임아웃 :	<input type="text" name="txtDisConnectTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
	Management 타임아웃 :	<input type="text" name="txtManagementTO" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/><br /><br />
		
				<input type="submit" class="n1qlexcute">
				<input type="reset" class="n1qlClear">
			</form>
		</div>
		<div class="big-area" >
			<form name="example_input" action="hey" method="post">
	아이디 사이즈 :	<input type="text" name="docIdSize" size="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>byte<br /><br />
	문서 사이즈 : 	<input type="text" name="docSize" size="15" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>byte<br /><br />
    생성 할 문서의 수 : <input type="text" name="docCount" size="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>건<br /><br />
	문서 종류 : 	<input type="radio" name="docType" value="Json" /> json
				<input type="radio" name="docType" value="Binary" /> binary<br /><br />    
	작업 반복 여부 : <input type="radio" name="loop" value="Ture" /> true
				<input type="radio" name="loop" value="False" /> false<br /><br />
	쓰레드 개수 : 	<input type="text" size="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>개<br /><br />	
				<input type="submit" class="n1qlexcute">
			</form>
		</div>
		<!-- //small-area -->
	</div>
	<!-- //float-frame -->
</div>

<!-- //layout-wrap -->
<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:53%">N1QL 실행창</h1>
		<h1 >N1QL 결과창</h1>
	</div>
	<!-- float-frame -->
	<div class="float-frame">
		<div class="float-unit" style="margin-left: 2%;">			
			<textarea id="n1ql" name="n1ql" rows="5" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; "></textarea>
			<button class="n1qlexcute">실행</button>
		</div>
		<div class="float-unit" style="margin-left: 2%;">
			<textarea id="n1ql" name="n1ql" rows="5" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly>쿼리를 실행해주세요.</textarea>
		</div>
		<div class="clear"> </div>
	</div>
	<!-- //float-frame -->
</div>

<!-- //layout-wrap -->
<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:53%">문서 ID 작업</h1>
		<h1 >작업 결과창</h1>
	</div>
	<!-- float-frame -->
	<div class="float-frame">
		<div class="float-unit" style="margin-left: 2%; height:400px;">
		<textarea id="n1ql" name="n1ql" rows="5" cols="33" style="margin: 0px; width: 90%; height: 85%; margin-right:10%; background-color: #eee; "></textarea>
			
		문서아이디
			<input name="pic" />			
			<button class="n1qlexcute" style="margin-left: 10%;">읽기</button>
			<button class="n1qlexcute">쓰기</button>
			<button class="n1qlexcute">삭제</button>
		</div>
		<div class="float-unit" style="margin-left: 2%; height:40px;">
			<textarea id="n1ql" name="n1ql" rows="2" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly>성공 or 실패</textarea>
		</div>
		<div class="clear"> </div>
	</div>
	<!-- //float-frame -->
</div>

<div class="layout-wrap">
	<div>
		<h1 style="float:left; width:53%">Json 및 txt 파일 Upsert</h1>
		<h1 >N1QL 결과창</h1>
	</div>
	<!-- float-frame -->
	<div class="float-frame">
		<div class="float-unit" style="margin-left: 2%; height:40px;">
			문서아이디 : <input name="pic" />			
			<input type="file" name="pic" style="margin-left: 10%;"/>
			<button class="n1qlexcute">Upsert 실행</button>
		</div>
		<div class="float-unit" style="margin-left: 2%; height:40px;">
			<textarea id="n1ql" name="n1ql" rows="2" cols="33" style="margin: 0px; width: 90%; height: 85%; background-color: #eee; " readonly>성공 or 실패</textarea>
		</div>
		<div class="clear"> </div>
	</div>
	<!-- //float-frame -->
</div>
</body>
</html>