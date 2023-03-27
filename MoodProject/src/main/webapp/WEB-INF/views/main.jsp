<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>메인페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>			

			<style>
				img {
					object-fit: cover;
					padding:0;
					margin:0;
				}
				table {
					margin : 30px 30px;
					width: auto;
					height:auto;
					
				}
				body {
					background-color: #d4c6bb;
				}
				
			</style>
		</head>
		<body>
<!-- 연습 -->
			<jsp:include page ="./common/topMenu.jsp"></jsp:include>
			
			<table style="width:1500px; height:900px; margin-left:auto; margin-right:auto;">
				<tr>
					<td colspan="2"><a href="/main.do"><img style="width:1000px; height:450px;" src="${contextPath}../resources/images/main1.png"/></a></td>
					<td><a href="/main.do"><img style="width:500px;  height:450px;" src="${contextPath}../resources/images/main2.png"/></a></td>
				</tr>
				<tr>
					<td><a href="/main.do"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main3.png"/></a></td>
					<td><a href="/main.do"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main4.png"/></a></td>
					<td><a href="/main.do"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main5.png"/></a></td>
				</tr>
			</table>
			
			<br/>
			
			
			
			<jsp:include page ="./common/footer.jsp"></jsp:include>
			
			
			
		</body>
	</html>