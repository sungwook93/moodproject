<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>상품리스트 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<!-- font -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
	
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>	
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link href="${contextPath}/resources/css/productcss/productList.css" rel="stylesheet" type="text/css">
	<script src="${contextPath}/resources/js/product.js"></script>
	
	<style>
	* {
		font-family: 'Sunflower', sans-serif;
	}	
	</style>
</head>
<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>
	
	<c:set var = "color" value = "${color}"/>
	<c:set var = "type" value = "${type}"/>
	
	<!-- 상품 검색 및 상품 정렬 -->
	
		<div id="keywordbox" style="font-family: 'Sunflower', sans-serif;">
			<input type="text" id="keyword" value="${keyword}" placeholder="상품을 입력해주리렵니까?" onclick="fn_checkbox()"><button onclick='getCheckboxValue()' id="keywordbutton">확인</button>
		</div>
		<div id="typebox">
			<div id="colorcheckbox">
				<label id="naming" for="color">색상:</label>
				<label><input type="checkbox" name="color" value="white" <c:if test="${fn:contains(color, 'white')}">checked</c:if>>white</label>
				<label><input type="checkbox" name="color" value="black" <c:if test="${fn:contains(color, 'black')}">checked</c:if>>black</label>
				<label><input type="checkbox" name="color" value="gray" <c:if test="${fn:contains(color, 'gray')}">checked</c:if>>gray</label>
			</div>
			<div id="typecheckbox">
				<label id="naming" for="type">제품:</label>
				<label><input type="checkbox" name="type" value="bed" <c:if test="${fn:contains(type, 'bed')}">checked</c:if>>침실</label>
				<label><input type="checkbox" name="type" value="living" <c:if test="${fn:contains(type, 'living')}">checked</c:if>>거실</label>
				<label><input type="checkbox" name="type" value="bath" <c:if test="${fn:contains(type, 'bath')}">checked</c:if>>욕실</label>
			</div>
			<div id="productradio">
				<label id="naming" for="array">정렬:</label>
				<label><input type="radio" name="array" value="l" <c:if test="${array=='l'}">checked</c:if>>낮은 가격순</label>
				<label><input type="radio" name="array" value="h" <c:if test="${array=='h'}">checked</c:if>>높은 가격순</label>
				<label><input type="radio" name="array" value="r" <c:if test="${array=='r'}">checked</c:if>>최신순</label>
			</div>
		</div>
	
		<h1><c:if test="${totalCount == 0 }">해당 상품이 없습니다.</c:if></h1>


	<!-- 상품 출력 -->
			<div class = "container producbox">
				<table>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="0" end ="2">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
										</div>
									</td>
						</c:forEach>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="3" end ="5">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
										</div>
									</td>
						</c:forEach>
					</tr>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="6" end ="8">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
										</div>
									</td>
						</c:forEach>
					</tr>
				</table>
			</div>
	
			<!-- 페이징 처리 -->
			<div class="container producbox" id="pagingunderbar">
				<div align="center">
					<ul class="btn-group pagination">
						<c:if test="${pageMaker.prev }">
							<li>
								<!-- c:url URL에 자동으로 Context Path 를 붙여주는 테그 -->
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${type}&page=${pageMaker.startPage-1}&array_type=${array}&keyword=${keyword}'/>"><span class="glyphicon glyphicon-chevron-left"></span></a>
							</li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
							<li>
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${type}&page=${pageNum}&array_type=${array}&keyword=${keyword}'/>"><i>${pageNum}</i></a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li>
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${type}&page=${pageMaker.endPage + 1}&array_type=${array}&keyword=${keyword}'/>"><span class="glyphicon glyphicon-chevron-right"></span></a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
</body>
</html>