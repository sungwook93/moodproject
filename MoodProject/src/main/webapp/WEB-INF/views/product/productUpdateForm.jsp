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
<title>상품수정</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link href="${contextPath}/resources/css/productcss/productRegisterForm.css" rel="stylesheet" type="text/css">
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
					<input type="file" id="file" onchange="fn_preview(this);" multiple/>
					<img id="preview" src="/image/displayImage?name=${product.product_code}"/>
				</div>
				
			</div>
			<!-- 상품 정보 부분 -->
			<div id="productdetailbox">
				<div>
					<div>
					<br><br>
					<label for="product_name">상품명</label><input type="text" id="product_name" maxlength="200" value="${product.product_name }">
					</div>
					<label for="product_size">규&nbsp&nbsp&nbsp격</label><input type="text" id="product_size" maxlength="20" value="${product.product_size}">
				</div>
				<div id="product_price1">
					<div>
						<label for="product_price">판매가</label><input type="number" id="product_price" maxlength="20" value="${product.product_price}">
					</div>
				</div>
				<div id="product_option">
					<div id="product_amout">
						<div>
							<p>색상&nbsp&nbsp&nbsp</p>
							<input type="radio" id="white" class="product_color" name="color" value="white" <c:if test="${product.product_color == 'white'}">checked</c:if>>
							<label for="white">흰색&nbsp&nbsp</label>
							<input type="radio" id="black" class="product_color" name="color" value="black" <c:if test="${product.product_color == 'black'}">checked</c:if>>
							<label for="black" style="color:black">검정&nbsp&nbsp</label>
							<input type="radio" id="gray" class="product_color" name="color" value="gray" <c:if test="${product.product_color == 'gray'}">checked</c:if>>
							<label for="gray" style="color:gray">회색</label>
						</div>
					</div>
					<div id="product_type1">
						<h4>상품안내</h4>
						<label for="product_type" style="font-size:15px;">카테고리&nbsp>&nbsp</label>
						<select id="product_type">
							<option value="bed" <c:if test="${product.product_type == 'bed'}">selected</c:if>>Bed</option>
							<option value="bath" <c:if test="${product.product_type == 'bath'}">selected</c:if>>Bath</option>
							<option value="living" <c:if test="${product.product_type == 'living'}">selected</c:if>>Living</option>
						</select>
					</div>
				</div>
				
				<!-- 장바구니 주문 버튼 -->
				<div id="buttonbox">
					<div>
						<input type="button" id="productRegister" value="수정하기"onclick="fn_productUpdate()">
					</div>
					<div >
						<input type="button"  id="cancel" value="취소하기" onclick="location.href='/product/productDetail?product_code=' +'${product.product_code}'">
					</div>
						<input type="hidden" id="product_code" value="${product.product_code}">
				</div>
			</div>
		</div>


	<!-- footer -->
	<jsp:include page ="../common/footer.jsp" flush="false"/>
	
</body>
</html>