<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<!-- 제이쿼리 ui css -->
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <!-- 제이쿼리  style css -->
            <link rel="stylesheet" href="/resources/demos/style.css">
            <!-- 제이쿼리 js -->
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <!-- 제이쿼리 ui js-->
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			<!-- 다음 API -->
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			
			<link href="${contextPath}/resources/css/member.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<jsp:include page ="../common/topMenu.jsp"></jsp:include>
	
	
	<div class="container" id="adminarea">
		<h1>상품관리</h1>
		<table id="adminproducttable">
			<tr>
				<td>상품번호</td>
				<td>상품이름</td>
				<td>이미지</td>
			</tr>
			<c:forEach var="board" items="${boardList}">
				
			</c:forEach>
			<tr>
				<td>상품번호</td>
				<td>상품이름</td>
				<td>이미지</td>
			</tr>
		</table>
		<h1>게시글관리</h1>
		<h1>회원관리</h1>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	<jsp:include page ="../common/footer.jsp"></jsp:include>
</body>
</html>