<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>
<script>
function avent(){
	var bool= confirm("후회안해? 삭제?");
	console.log(bool)
	if(bool == true) {
		location.href="/notice_delete?idx="+${vo.idx};
		return;
	}
		return;
}
</script>
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
		<p></p>
		<p class="file"><a href="${vo.filepath}" download="${vo.filename }">${vo.filename }</a></p>
	</div>
	
	<div class="sub-view-bottom">
		<c:if test="${admin == 'y' }">
		<a href="javascript:avent()" class="btn-delete">삭제</a>&nbsp;&nbsp;
		</c:if>
		<a href="notice_list" class="btn-list">목록</a>&nbsp;&nbsp;
	</div>
		
</div>


<%@ include file="../index/footer.jsp"%>















