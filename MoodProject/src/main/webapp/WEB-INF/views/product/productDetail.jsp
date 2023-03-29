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
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link href="${contextPath}/resources/css/productcss/productDetail.css" rel="stylesheet" type="text/css">
	<script src="${contextPath}/resources/js/product.js"></script>
</head>
<body>

	<!-- 상단 메뉴 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>
	
	<div class="container">
		<div id="topbox">
			<!-- 상품 이미지 부분 -->
			<div id="imagebox">
				<div>
					<img src="/image/displayImage?name=${productDTO.product_code}" id="mainImg"/>
				</div>
				<div>
					<table>
						<tr>
							<c:forEach items="${imagesList}" var="images">
								<td>
									<img src="/image/displayImage?name=${images}" id="subImg"/>
								</td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
			<!-- 상품 정보 부분 -->
			<div id="productdetailbox">
				
			</div>
		</div>
	</div>


	
	<jsp:include page ="../common/footer.jsp"></jsp:include>

</body>
</html>