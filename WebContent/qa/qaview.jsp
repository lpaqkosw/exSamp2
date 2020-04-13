<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index/header.jsp"%>

<style>
	.file{
		font-size: 11px;
		padding-top: 100px;
	}
</style>
<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-view-title">${vo.title }</h2>
		<p class="sub-view-wd">${vo.author } | ${vo.regdate }</p>
	</div>
	<div class="sub-view-contnet">
		${vo.content }
		<p class="file"><a href="${vo.filepath}" download="${vo.filename }">${vo.filename }</a></p>
		<c:if test="${!empty vo.answer }">
			<p style="font-weight:bold; padding-top: 20px;">답변</p>
			<p>${vo.answer }</p>
		</c:if>
	</div>
	
	<div class="sub-view-bottom">
	<c:if test="${admin == 'y' }">
		<a href="qna_reply?idx=${vo.idx }" class="btn-modify">답변</a>&nbsp;&nbsp;
		</c:if>
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		<a href="qna_list" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>
<script>
function avent(){
	if(confirm("후회안해? 삭제?") == true) {
		location.href="qna_delete?idx="+${vo.idx};
	}else {
		return;
	}
}
</script>


<%@ include file="../index/footer.jsp"%>















