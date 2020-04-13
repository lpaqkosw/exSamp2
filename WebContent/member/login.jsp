<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">장수하늘소 로그인</h2>
	</div>
	
	<div class="write-form" style="width:50%; margin:0 auto; border:1px solid #ccc; padding:20px;">
		<form action="users_login" name="loginform" method="post">
			<fieldset>
				<legend class="readonly">로그인그룹</legend>
				<div style="width:70%; float:left;">
					<label for="id" class="readonly">아이디</label>
					<input type="text" name="userid" id="id" placeholder="아이디">
					<label for="pw" class="readonly">패스워드</label>
					<input type="password" name="userpw" id="pw" placeholder="패스워드">
				</div>
				<div style="width:28%; float:right;padding:15px;
				height:132px; margin-top:3px;">
					<input type="submit" value="로그인" alt="로그인" 
					style="background: #FF4000; color:#fff;
					width:100%; height:100%; border:0 none; cursor:pointer;">
				</div>
				<p style="clear:both;padding-top:20px; text-align:center;">아이디찾기 | 패스워드찾기 | <a href="users_new">회원가입</a></p>
			</fieldset>
		</form>	
	</div>
		
</div>

<script>
	if('${result}' == "success"){
		location.href="/index";
	}
	if('${result}' == "failed"){
		alert("로그인 실패");
		history.back();
	}
	function formcheck() {
		if(my.title.value=="") {
			alert("제목입력하세요");
			my.title.focus();
			return false;
		}
		if(my.content.value=="") {
			alert("내용입력하세요");
			my.contnet.focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="../index/footer.jsp"%>















