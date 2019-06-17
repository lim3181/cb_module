<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Couchbase</title>
<style>
#header {
	width: 100%;
    height: 100px;
    background-color: #4287d6;
    display: flex;
  	justify-content: center;
    
}

#header a img{
	width: 80px;
    height: 96px;
}

body{
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
	padding: 0;
}
h1 {
	font-size: 1.4em;
	margin: 0;
	font-weight: 100;
	color: #333;
	margin-bottom: 8px;
}
.layout-wrap{
	margin:10px 10px 20px;
	border: 1px dashed #666;
	padding: 10px;
}
.float-frame{
	background: lightgray;
	border: 1px solid #ddd;
	padding: 10px;
	overflow: hidden;
	zoom:1;
}
.float-unit {
	color: #fff;
	font-size: 18px;
	font-weight: bold;
	text-align: center;
	float: left;
	width: 47%;
	height: 400px;
	padding: 15px 0;
	margin: 0 1px;
}
.big-area {
	float: left;
	width: 100px;
	margin: 0 10px 0 0;
	padding: 10px;
	background: #999;
}
.small-unit {
	float: left;
	width: 48px;
	height: 48px;
	color: #fff;
	background: #333;
	margin: 1px;
}
.n1qlexcute{
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 12px;
	background-color: #4287d6;
	transition-duration: 0.4s;
}
.n1qlexcute:hover {
  background-color: #555555; /* Green */
  color: white;
}
textarea{resize:none;}
</style>
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
	<h1>문서 ID 작업</h1>
	<div class="float-frame">
		<div class="big-area">
			<div class="small-unit"><button>읽기</button></div>
			<div class="small-unit">2</div>
			<div class="small-unit">3</div>
			<div class="small-unit">4</div>
		</div>
		<div class="big-area">
			<div class="small-unit">A</div>
			<div class="small-unit">B</div>
			<div class="small-unit">C</div>
			<div class="small-unit">D</div>
		</div>
		<!-- //small-area -->
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
		<div class="float-unit" style="margin-left: 2%; height:40px;">문서아이디
			<input name="pic" />			
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