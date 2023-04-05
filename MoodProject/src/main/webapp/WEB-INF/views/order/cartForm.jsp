<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		
			
			<link href="${contextPath}/resources/css/cart.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page ="../common/topMenu.jsp"></jsp:include>
	
	
	<div class="container" id="cartListbox"> 
		<div class="ordersub" style="margin-right:300px; position:relative; bottom:0; width:200px;">
			<h3>장바구니(담긴개수)</h3>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0;">
			<span class="glyphicon glyphicon-shopping-cart" style="font-size:3em;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP1</h6>
			<h4>장바구니</h4>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0;">
			<span class="glyphicon glyphicon-list-alt" style="font-size:3em;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP2</h6s>
			<h4>주문서작성</h4>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0;">
			<span class="glyphicon glyphicon-ok" style="font-size:3em;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP3</h6>
			<h4>결제/주문완료</h4>
		</div>
	</div>
	<div class="container" id="orderListBox">
		<table id="orderListTable">
			<tr style="text-align:center;">
				<td style="width:3%;"><input type="checkbox"></td>
				<td>상품정보</td>
				<td style="width:10%;">수량</td>
				<td style="width:10%;">금액</td>
				<td style="width:10%;">배송정보</td>
				<td style="width:10%;">주문하기</td>
			</tr>
			<c:forEach var="order" items="${orderList}">
			<tr style="text-align:center;">
				<td><input type="checkbox"></td>
				<td style="text-align:left !important;"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="container" id="orderCountBox">
		<table id="orderCountTable">
			<tr>
				<td>빈공간</td>
				<td><h4>결제예정금액</h4></td>
			</tr>
			<tr>
				<td>빈공간</td>
				<td>가격값가져오기</td>
			</tr>
		</table>
	</div>
	<div class="container" id="orderbtnBox">
		<div><button>선택상품주문</button></div>
		<div><button>전체상품주문</button></div>
	</div>
	
	
	<jsp:include page ="../common/footer.jsp"></jsp:include>
</body>
</html>