<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import = "java.text.NumberFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		
			<script src="${contextPath}/resources/js/cart.js"></script>
			<link href="${contextPath}/resources/css/cart.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page ="../common/topMenu.jsp"></jsp:include>
	
	
	
	<div class="container" id="cartListbox"> 
		<div class="ordersub" style="margin-right:300px; position:relative; bottom:0; width:200px;">
			<h2 style="font-size:30px;">장바구니&nbsp;&nbsp;(${fn:length(cartList)})</h2>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0; background-color: #8D999E;">
			<span class="glyphicon glyphicon-shopping-cart" style="font-size:3em;  color:white;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP1</h6>
			<h4>장바구니</h4>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0;">
			<span class="glyphicon glyphicon-list-alt" style="font-size:3em;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP2</h6>
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
				<th style="width:3%;">
				<input type="checkbox" value="0" class="checkBoxId" id="cbx_chkAll" name="cbx_chkAll" >
				<label for="cbx_chkAll"></label>					
				</th>
				<th style="text-align:center;">상품정보</th>
				<th style="width:20%;">수량</th>
				<th style="width:20%;">금액</th>
				<th style="width:20%;">주문하기</th>
			</tr>
			<c:forEach var="cart" items="${cartList}" varStatus="status" >
			<tr style="text-align:center;">
				<td>
				<input type="checkbox" class="checkBoxId" id="${status.count}" name="check" value="${cart.product_price * cart.product_amount}" onClick='checkbox();'>
				<label for="${status.count}"></label>
				</td>
				<td>
					<div style="display:flex">
						<div style="width:100px;">
						<img src="/image/displayImage?name=${cart.product_code}" class="cartImg" style="margin-left:14px;"/>					
						</div>
						<div style="text-align:left; margin-left:40px;">
						<ul>
							<li><input type="hidden" value="${cart.product_code}" id="product_code${status.count}"></li>
							<li><a href="/product/productDetail?product_code=${cart.product_code}">${cart.product_name}</a></li>
							<li><a href="/product/productDetail?product_code=${cart.product_code}">상품종류 &nbsp&nbsp${cart.product_type}</a></li>
							<li><a href="/product/productDetail?product_code=${cart.product_code}">사이즈 &nbsp&nbsp${cart.product_size}</a></li>
							<li><a href="/product/productDetail?product_code=${cart.product_code}">색상 &nbsp&nbsp${cart.product_color}</a></li>
						</ul>
						</div>				
					</div>
				</td>
				<td style="text-align:center;"><input type="button" id="amountbutton" class="amount" onclick='count("minus", ${status.count})' value="-">
												<p class="amount" id="product_amount${status.count}">${cart.product_amount}</p>
												<input type="button" id="amountbutton" class="amount" onclick='count("plus", ${status.count})' value="+">
												<input type="button" id="countbutton" value="변경적용" onclick='countUpdate(${status.count})'>
												<input type="hidden" id="price${status.count}" value="${cart.product_price}"/>
				</td>
				<td style="text-align:center;" id="product_price${status.count}" >
					<fmt:formatNumber value="${cart.product_price * cart.product_amount}" pattern="##,###,###원"/>
				</td>	
				<td>
					<input type="button" id="order${status.count}" class="orderbutton" value="주문하기" onclick="fn_order(${status.count})">
					<input type="hidden" id="cart_num${status.count}" value="${cart.cart_num}" >					
					<input type="button" id="delete${status.count}" class="orderbutton" value="삭제하기" onClick="fn_cartdelete(${status.count})">					
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="container" id="orderCountBox">
		<span style="font-size:30px;">총 결제금액 &nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span id="totalprice" style="font-size:25px;">0원</span>
	</div>
	
	<div class="container" id="orderbtnBox">
		<input type="button" value="전체상품주문" onClick="fn_totalorder()" style="background-color: #69737A; color:white; font-size:20px; border:none;">
		<input type="button" value="선택상품주문" onClick="fn_selectorder()" style="color: #69737A; font-size:20px; border:none;">	
	</div>
	
	<input type="hidden" value="${member1.userID}" id="userID">
	
	
	<jsp:include page ="../common/footer.jsp"></jsp:include>
</body>
</html>