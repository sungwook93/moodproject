<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 검색 결과 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link href="${contextPath}/resources/css/product.css" rel="stylesheet" type="text/css">
	<script src="${contextPath}/resources/js/product.js"></script>
</head>
<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>
	
	<h2 align="center">${color}</h2>
	
	<input type="hidden" id="color" value="${color}"/>
	<!-- 상품 정렬 -->
		<div>
			<div id="productlistbox">
				<label><input type="checkbox" name="type" value="bed">침실</label>
				<label><input type="checkbox" name="type" value="living">거실</label>
				<label><input type="checkbox" name="type" value="bath">욕실</label>
				<button onclick='getCheckboxValue()'>확인</button>
			</div>
			<div>
				<select class = "form-control" id="arrayOption">
						<option value = "r" <c:if test="${array_type=='r'}">selected</c:if>>최신순</option>
						<option value = "o" <c:if test="${array_type=='o'}">selected</c:if>>오래된 순</option>
						<option value = "p" <c:if test="${array_type=='p'}">selected</c:if>>인기순</option>
				</select>
			</div>
		</div>
	

	<!-- 상품 출력 -->
			<div class = "container producbox">
				<table>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="0" end ="2">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="#"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="#"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="#">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
										</div>
									</td>
						</c:forEach>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="3" end ="5">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="#"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="#"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="#">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
										</div>
									</td>
						</c:forEach>
					</tr>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" begin="6" end ="8">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="#"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="#"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="#">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>			
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
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${product_type}&page=${pageMaker.startPage-1}'/>"><span class="glyphicon glyphicon-chevron-left"></span></a>
							</li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
							<li>
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${product_type}&page=${pageNum}'/>"><i>${pageNum}</i></a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li>
								<a href="<c:url value='/product/productList?product_color=${color}&product_type=${product_type}&page=${pageMaker.endPage + 1}'/>"><span class="glyphicon glyphicon-chevron-right"></span></a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
</body>
</html>