<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>
<script>
if('${result}' == 'failed'){
	alert("등록실패");
	history.back();
}
if('${result}' == 'success'){
	alert("등록됨");
	location.href="qna_list";
}
	function send(){
		reply.submit();
	}
</script>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${vo.title }</h2>
		<p class="sub-view-wd">${vo.author } | ${vo.regdate }</p>
	</div>
	<div class="sub-view-contnet">
		${vo.content }
	</div>
	
	<div class="qareply" style="padding-bottom:30px;">
		<form name="reply" method="post" action="qna_reply?idx=${vo.idx }">
		<h2 style="padding-bottom:16px; ">답변</h2>
		<textarea name="answer" style="width:100%; height:300px;">
		</textarea>
		</form>
	</div>
	
	
	
	<div class="sub-view-bottom">
		<a href="javascript:send()" class="btn-modify">답변저장</a>&nbsp;&nbsp;
		<a href="qna_list" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>



<%@ include file="../index/footer.jsp"%>















