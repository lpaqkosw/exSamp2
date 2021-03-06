<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index/header.jsp"%>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">장수하늘소 질문답변</h2>
		<div class="sub-search">
			<form name="my" method="post" action="qna_searchList?category=${category }" onsubmit="return check()">
		
				<select name="genre" class="sel">
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" name="key" class="text">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
	</div>
	
	<div class="content-body">
		<table class="qatable">
			<caption class="readonly">질문답변 표</caption>
			<colgroup>
				<col width="6%">
				<col width="6%">
				<col width="42%">
				<col width="10%">
				<col width="15%">
				<col width="11%">
				<col width="10%">
			</colgroup>
			<tbody>

				<tr>
					<th>번호</th>
					<th>종류</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>답변상태</th>
					<th>날자</th>
					<th>조회수</th>
				</tr>
<c:if test="${!empty list }">
	<c:forEach var="list" items="${list }">
				<tr>
					<td>${listnum }</td>
					<td>${list.category}</td>
					<td class="tleft"><a href="qna_view?idx=${list.idx }">${list.title }</a></td>
					<td>${list.author }</td>
					<c:if test="${list.status eq 'y' }"><td><span class="red">답변완료</td></c:if>
					<c:if test="${list.status ne 'y' }"><td><span class="gray">답변대기</td></c:if>
					<td>${list.regdate }</td>
					<td>${list.readcnt }</td>
				</tr>
				<c:set var="listnum" value="${listnum-1 }"></c:set>
	</c:forEach>
</c:if>
<c:if test="${empty list }">
				<tr>
					<td colspan="7">질답 없음</td>
				</tr>
</c:if>
			</tbody>
		</table>
	</div>
		
		
		<div class="paging">
			<ul>
				${pagenav }
			</ul>
			<a href="qna_write" class="btn-write">글쓰기</a>
		</div>

</div>

<script>
	function check() {
		if(my.cont.value=="") {
			alert("검색단어입력하세요");
			my.cont.focus;
			return false;
		}
		return true;
	}
</script>

<%@ include file="../index/footer.jsp"%>















