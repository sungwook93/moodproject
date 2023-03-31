<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link href="${contextPath}/resources/css/productcss/productDetail.css" rel="stylesheet" type="text/css">
	<script src="${contextPath}/resources/js/product.js"></script>
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
	
</head>
<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>
	
	<div class="container" style="font-family: 'Sunflower', sans-serif;">
		<div id="topbox">
			<!-- 상품 이미지 부분 -->
			<div id="imagebox">
				<div>
					<img src="/image/displayImage?name=${product.product_code}" id="mainImg"/>
				</div>
				<div>
					<table id="subImg3">
						<tr>
								<c:if test="${imagesList.images01 != null}" ><td class="subImg1">
									<img src="/image/displayImage?name=${imagesList.images01}" id="img1" class="subImg"/>
								</td></c:if>
								<c:if test="${imagesList.images02 != null}" >
								<td class="subImg1">
									<img src="/image/displayImage?name=${imagesList.images02}" id="img2" class="subImg"/>
								</td>
								</c:if>
								<c:if test="${imagesList.images03 != null}" >
								<td class="subImg1">
									<img src="/image/displayImage?name=${imagesList.images03}" id="img3" class="subImg"/>
								</td>
								</c:if>
								<c:if test="${imagesList.images04 != null}" >
								<td class="subImg1">
									<img src="/image/displayImage?name=${imagesList.images04}" id="img4" class="subImg"/>
								</td>
								</c:if>
							</tr>
					</table>
				</div>
			</div>
			<!-- 상품 정보 부분 -->
			<div id="productdetailbox">
				<div id="product_name">
					<p id="name">${product.product_name }</p>
					<span id="size">규격&nbsp>&nbsp${product.product_size }</span>
					<input type="button" value="상품수정" id="productupdate" >
					<input type="button" value="상품삭제" id="productdelete">
				</div>
				<div id="product_price">
					<div>
						<h3 class="pricename">판매가</h3>
					</div>
					<div>
						<h3 class="price" ><fmt:formatNumber value="${product.product_price}" pattern="#,###,###원"/></h3>
					</div>
				</div>
				<div id="product_option">
					<div id="product_amout">
						<p class="amount" >수량</p>
						<input type="button" id="amountbutton" class="amount" onclick='count("minus")' value="-">
						<p class="amount" id="product_amount"><strong>1</strong></p>
						<input type="button" id="amountbutton" class="amount" onclick='count("plus")' value="+">
						<div>
							<p>색상&nbsp&nbsp&nbsp</p>
							<div id="colorbox" style="background-color:${product.product_color};"></div>
						</div>
					</div>
					<div id="product_type">
						<h4>상품안내</h4>
						<h4 style="font-size:15px;">카테고리&nbsp>&nbsp ${product.product_type}</h4>
					</div>
				</div>
				<div id="totalproduct_price">
					<span id="totalprice1">총구매가&nbsp&nbsp&nbsp</span>
					<span id="totalprice2"><fmt:formatNumber value="${product.product_price}" pattern="#,###,###원"/></span>
				</div>
				<!-- 장바구니 주문 버튼 -->
				<div id="buttonbox">
					<div>
						<input type="button" id="cartbutton" value="장바구니">
					</div>
					<div >
						<input type="button"  id="orderbutton" value="주문하기">
					</div>
				</div>
			</div>
		</div>
		
		<h2 style="text-align:center; color:#69737A;">추천상품</h2>
		<table id="recommendlist">
			<tr>
				<c:forEach items="${recommendlist}" var="recommend" begin="0" end ="2">
					<td>
						<div class = "pl">
						<a href="/product/productDetail?product_code=${recommend.product_code}"><img src = "${contextPath }/image/displayImage?name=${recommend.product_code}" /></a><br/><br/>
						<a href="/product/productDetail?product_code=${recommend.product_code}"><strong>상 품 명 : ${recommend.product_name}</strong></a><br/>
						<a href="/product/productDetail?product_code=${recommend.product_code}">가  격 : <fmt:formatNumber value="${recommend.product_price}" pattern="#,###원"/></a><br/>			
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
		
	</div>

	

	<input type="hidden" id="price" value="${product.product_price}">

	
	<jsp:include page ="../common/footer.jsp"></jsp:include>

</body>
</html>