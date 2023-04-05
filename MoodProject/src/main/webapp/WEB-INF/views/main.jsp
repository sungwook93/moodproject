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

			<jsp:include page ="./common/topMenu.jsp"></jsp:include>
			<br/>
			<!-- 슬라이더 -->
			<div class ="container-fluid">
				<div id = "myCarousel" class ="carousel slide" data-ride = "carousel">
					<!-- indicators -->
					
					<div class ="carousel-inner">
						<div class ="item active">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main01.jpg" style = "width:1500px; height: 500px;"/>
		
						</div>
						<div class ="item">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main02.jpg" style = "width:1500px; height: 500px;"/>
		
						</div>
						<div class ="item">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main03.jpg" style = "width:1500px; height: 500px;"/>
		
						</div>
					</div>
				
					<a class = "left carousel-control" href = "#myCarousel" data-slide = "prev" style = "background-image: none">
						<span class = "glyphicon glyphicon-chevron-left"></span>
					</a>
					
					<a class = "right carousel-control" href = "#myCarousel" data-slide = "next" style = "background-image: none">
						<span class = "glyphicon glyphicon-chevron-right"></span>
					</a> 
					
				</div>
			</div>
			
			<table style="width:1500px; height:450px; margin-left:auto; margin-right:auto;">
				<tr>
					<td><a href="/product/productList?product_color=white,black,gray&product_type=living&page=1&array_type=r"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main3.png"/></a></td>
					<td><a href="/product/productList?product_color=white,black,gray&product_type=bed&page=1&array_type=r"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main4.png"/></a></td>
					<td><a href="/product/productList?product_color=white,black,gray&product_type=bath&page=1&array_type=r"><img style="width:500px; height:450px;" src="${contextPath}../resources/images/main5.png"/></a></td>
				</tr>
			</table>
			
			<br/>
			
			
			
			<jsp:include page ="./common/footer.jsp"></jsp:include>
			
			
			
			
		</body>
	</html>