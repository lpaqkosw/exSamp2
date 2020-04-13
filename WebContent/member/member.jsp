<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>
<script>
if('${result}' == "failed"){
	alert("error");
	history.back();
}
if('${result}' == "success"){
	alert("success");
	alert("please login");
	location.href="users_login";
}
	function send(){
		if(my.userid.value == ""){
			alert("enter id");
			my.userid.focus();
			return;
		}
		if(my.pass1.value == ""){
			alert("enter pass");
			my.pass1.focus();
			return;
		}
		if(my.pass2.value == ""){
			alert("enter pass");
			my.pass2.focus();
			return;
		}
		if(my.pass1.value != my.pass2.value){
			alert("pass not same");
			my.pass2.focus();
			return; 
		}
		if(my.username.value == ""){
			alert("enter name");
			my.username.focus();
			return;
		}
		if(my.email1.value == ""){
			alert("enter email");
			my.email1.focus();
			return;
		}
		if(my.email2.value == ""){
			alert("enter email");
			my.email2.focus();
			return;
		}
		my.submit();
	}
	
	function idchk(){
		if(my.userid.value==""){
			alert("enter id");
			my.userid.focus();
			return;
		}
		window.open("users_idcheck?userid="+my.userid.value,"아이디체크" ,"width=400 height=200");
	}
</script>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">장수하늘소 회원가입</h2>
	</div>
	
	<div class="write-form">
		<table summery="회원가입 글쓰기 테이블 입니다" align="center">
			<caption class="readonly">회원가입 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<!-- <form name="my" method="post" action="memberinsert.do" enctype="multipart/form-data" onsubmit="return formcheck();">    -->
			<form name="my" method="post" action="users_new">
				<fieldset>
					<legend class="readonly">입력폼</legend>
					<tr>
						<th scope="row">
							<label for="id">아이디</label>
						</th>
						<td>
							<input type="text" name="userid" id="id" class="minput"> 
							<a href="javascript:idchk()" class="btn-write">중복확인</a>
						</td>
					</tr>
					<tr>
						<th scope="row"><lavel for="pass1">패스워드</lavel></th>
						<td><input type="passwrod" name="pass1" id="pass1"  class="minput" ></td>
					</tr>
					<tr>
						<th scope="row">패스워드 확인</th>
						<td><input type="passwrod" name="pass2"  class="minput" onkeyup="chkpass()"></td>
					</tr>
					<tr>
						<th scope="row">이름</th>
						<td><input type="text" name="username"  class="minput"></td>
					</tr>
					<tr>
						<th scope="row">이메일</th>
						<td>
							<input type="text" name="email1" class="email"> @
							<input type="text" name="email2"  class="email"> 
						</td>
					</tr>
					<tr>
						<td colspan="2">
							
							<input type="button" value="전송" class="btn-write" onclick="send()">
							<input type="button" value="목록"  class="btn-reset" onclick="javascript:location.href='portfolio.do'">
						</td>
					</tr>
					</fieldset>
				</form>
			</tbody>
		</table>
	</div>
		
</div>

<script>
	function formcheck() {
		if(my.id.value=="") {
			alert("아이디입력하세요");
			my.id.focus();
			return false;
		}
		if(my.pass1.value=="") {
			alert("패스워드입력하세요");
			my.pass1.focus();
			return false;
		}
		if(my.pass1.value != my.pass2.value) {
			alert("패스워드를 확인하세요");
			my.pass1.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="../index/footer.jsp"%>















