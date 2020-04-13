<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../index/header.jsp"%>

<div class="contain">
	<h2 class="shoptitle">[플레이어] 나이키 에어맥스 95,97,98, 베이퍼 맥스 등 핫 아이템 42종!!</h2>
	<div class="shopimg">
		<img src="images/shop1.jpg" alt="핸드백">
	</div>
	<div class="shoptop">
		<span class="shopdan"><strong>37,000</strong>원</span>
		<p>원산지 : 상품상세페이지 참조등 (각 상품별 원산지는 상세설명 참조)</p>
		<select name="bag" class="select">
			<option>선택</option>
			<option value="250">250mm</option>
			<option value="260">260mm</option>
			<option value="270">270mm</option>
		</select>
		<div class="btngroup">
			<button class="btn-cart">장바구니담기</button>
			<a href="" class="btn-buy">구매하기</a>
		</div>
	</div>
	
	<div class="banner">
		<img src="images/banner.jpg" alt="">
	</div>
	
	<div class="shopcont">
		<img src="images/shopcont.jpg" alt="상품상세설명">
	</div>
</div>

<script>
function avent(){
	if(confirm("후회안해? 삭제?") == true) {
		location.href="portfoliodelete.do";
	}else {
		return;
	}
}
</script>


<%@ include file="../index/footer.jsp"%>















