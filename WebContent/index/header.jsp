<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>장수하늘소</title>
	<link href="../css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
	<link href="../css/common.css" rel="stylesheet">
	<link href="../css/mystyle.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

  <script>
    $(function(){
    	
      $('.slider').bxSlider({
    	  mode: 'fade',
    	  captions: true
      });
      
      $(".sitemap").click(function() {
			$(".sitewrap").slideDown();
		})
	  $("#close").click(function() {
			$(".sitewrap").slideUp();
		})
		
	  $(".nav > nav > .navi > li").hover(function() {
		  $(this).children(".navi2").stop().slideDown();
	  }, function() {
		  $(this).children(".navi2").stop().slideUp();
	  });
		
    });
  </script>

</head>
<body>
	<div class="header">
		<header>
			<div class="topnav">
				<ul>
					<c:if test="${!empty username }">
						<li>안녕하세요 ${username }님!</li>
						<li><a href="users_logout">로그아웃</a></li>
					</c:if>
					<c:if test="${empty username }">
						<li><a href="users_login">로그인</a></li>
						<li><a href="users_new">회원가입</a></li>
					</c:if>
					<li><a href="javascript:void(0)" class="sitemap">사이트맵</a></li>
				</ul>
			</div>
			<div class="navigation">
				<h1 class="logo"><a href="/index">LOGO</a></h1>
				<div class="nav">
					<nav>
						<ul class="navi">
							<li><a href="about.do">내소개</a>
								<ul class="navi2">
									<li><a href="#">이력서</a></li>
									<li><a href="#">자기소개서</a></li>
									<li><a href="#">자격증</a></li>
								</ul>
							</li>
							<li><a href="gallery.do">갤러리</a>
								<ul class="navi2">
									<li><a href="#">포토샵</a></li>
									<li><a href="#">일러스트</a></li>
									<li><a href="#">개인작품</a></li>
									<li><a href="#">웹갤러리</a></li>
								</ul>
							</li>
							<li><a href="portfolio.do">포트폴리오</a>
								<ul class="navi2">
									<li><a href="#">앱개발</a></li>
									<li><a href="#">웹개발</a></li>
									<li><a href="#">UI디자인</a></li>
								</ul>
							</li>
							<li><a href="qna_list">질문답변</a>
								<ul class="navi2">
									<li><a href="qna_list?category=java">JAVA</a></li>
									<li><a href="qna_list?category=db">DB</a></li>
									<li><a href="qna_list?category=jsp">JSP</a></li>
									<li><a href="qna_list?category=php">PHP</a></li>
								</ul>
							</li>
							<li><a href="notice_list">공지사항</a></li>
							<li><a href="shop.do">쇼핑몰</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
	</div>
	
	<div class="line"></div>
	
	<div class="sitewrap">
		<span class="fa fa-close" id="close" style="cursor:pointer"></span>
		<div class="inner">
			<span class="map">내소개</span>
			<span class="map">갤러리</span>
			<span class="map">포트폴리오</span>
			<span class="map">질문답변</span>
			<span class="map">공지사항</span>
			<span class="map">쇼핑몰</span>
		</div>
	</div>
	
	
	
	
	
	
	
	
	