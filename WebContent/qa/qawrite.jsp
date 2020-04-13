<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>

<script>
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
	
	if('${result}' == 'failed'){
		alert("등록실패");
		history.back();
	}
	if('${result}' == 'success'){
		alert("등록됨");
		location.href="qna_list";
	}
</script>

<div class="contain">
	<div class="sub-topcontent">
		<h2 class="sub-title">장수하늘소 질문답변</h2>
	</div>
	
	<div class="write-form">
		<table summery="질문답변 글쓰기 테이블 입니다">
			<caption class="readonly">포트폴리오 입력폼</caption>			
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tbody>
			<form name="my" method="post" action="qna_write" enctype="multipart/form-data" onsubmit="return formcheck();">
					<tr>
						<th>선택</th>
						<td><input type="checkbox" name="chk" value="chkok"> 공지등록</td>
					</tr>
					<tr>
						<th>질문종류</th>
						<td>
							<input type="radio" name="category" value="java" checked> 자바
							<input type="radio" name="category" value="jsp"> JSP
							<input type="radio" name="category" value="db"> 오라클
							<input type="radio" name="category" value="php"> PHP
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" placeholder="제목입력하세요"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content"></textarea></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="author"></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<select name="hp1" class="hp1">
								<option>선택</option>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="019">019</option>
							</select> - 
							<input type="text" name="hp2" class="hp2"> - 
							<input type="text" name="hp3" class="hp3">
						</td>
					</tr>
					<tr>
						<th>첨부</th>
						<td><input type="file" name="filename"></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="전송" class="btn-write">
							<input type="button" value="목록"  class="btn-reset" onclick="javascript:location.href='qa.do'">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
		
</div>


<%@ include file="../index/footer.jsp"%>















