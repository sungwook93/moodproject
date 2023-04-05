<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Concept</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">			
			<link href="${contextPath}/resources/css/concept.css" rel="stylesheet" type="text/css">
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page ="../common/topMenu.jsp"></jsp:include>
			
			<div id="mainTitle">
				<hr/>
				<h1 style = "text-align:center">CONCEPT</h1>
			</div>
			
			<!-- 내용-->
			<div class="container">
				<p style = "text-align: center;">
				<a href="/main.do"><img src="${contextPath}../resources/images/logo1.png" style = "width: 40%;"/></a>
				</p>
				<div class="conceptbox">
					<h4>바쁜 일상 속에서 내일을 위한 준비</h4>
					<h4>평범한 일상 속에 숨겨진 우리집의 작은 불편함이 있으세요?</h4>
					<h4>소소하지만, 특별해지고 싶은, 나만의 공간을 원하세요?</h4>
				</div>
				
				<div class="conceptbox">
					<h2><strong>Value</strong></h2>
					<h4>Simple, Modern을 코드로 삼아 편안함을 추구하는 브랜드입니다.</h4>
					<h4>디자인 가구를 통한 소비자, 공간이용자의 오감을 건드리고, 복합적인 경험을 전달하는 장소를 고민하신다면 MOOD가 함께 하겠습니다.</h4>
				</div>
				
				<div class="conceptbox1">
					<div class="philosophy">
					<h2><strong>Philosophy</strong></h2>
					</div>
					<div class="box">
						<h3><strong>Design</strong></h3>
						<h4>어떠한 공간에서도 변함없는 고유의 표정을 가진 가구. 공간의 완성은 가구로부터 시작됩니다.</h4>
					</div>
					<div class="box">
						<h3><strong>Quality</strong></h3>
						<h4>MOOD는 품질을 타협하지 않습니다. 내 가족이 쓸 수 있는 프리미엄 품질의 가구를 고집합니다.</h4>
					</div>
					<div class="box">
						<h3><strong>Life</strong></h3>
						<h4>즐거움이 가득한 공간, 마음까지 편안하게 쉴수 있는 공간. 당신의 일상이 달라집니다.</h4>
					</div>
				</div>
				
			</div>	
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>