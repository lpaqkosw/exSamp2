<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp"%>
	
	<div class="slider">
	    <div><img src="images/a1.jpg" alt="" title="배움의 즐거움이 있는곳"></div>
	    <div><img src="images/a2.jpg" alt="" title="나에게 주는 만큼"></div>
	    <div><img src="images/a3.jpg" alt="" title="나에게도 많은 축복이 있습니다"></div>
  	</div>

	
	<div class="indexgallery">
			<div class="indextitle">
				<h2>장수하늘소<br>갤러리</h2>
				<span><a href="gallery.do">MORE</a></span>
			</div>
			<ul>
				<li><a href=""><img src="images/shop1.jpg"></a></li>
				<li><a href=""><img src="images/shop2.jpg"></a></li>
				<li><a href=""><img src="images/shop3.jpg"></a></li>
			</ul>
	</div>
	
	
	<div class="bbs_wrap">
		<div class="bbs_left">
			<h2 class="title">공지시항</h2>
			<ul>
				<li><a href="#">화면구현은 어떻게 하는가</a></li>
				<li><a href="#">홈페이지 혼자 만들어보기</a></li>
				<li><a href="#">지구여행 얼마 남았는가</a> </li>
				<li><a href="#">일본취업 나도 해보자</a></li>
				<li><a href="#">하다보면 할수 있다</a></li>
			</ul>
			<a href="notice.do"><span class="fa fa-plus plus"></span></a>
		</div>
		<div class="bbs_right">
			<h2 class="title">${vo.username }</h2>
			<ul>
				<li><a href="#">이클립스 다운로드</a></li>
				<li><a href="#">JSP강좌 PDF</a></li>
				<li><a href="#">JAVA 환경설정 파일 설명서</a> </li>
				<li><a href="#">동영상 강좌 자료실</a></li>
				<li><a href="#">스프링 강좌 자료 다운</a></li>
			</ul>
			<a href="qa.do"><span class="fa fa-plus plus"></span></a>
		</div>
	</div>
	
<%@ include file="footer.jsp" %>













