<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>주문 완료</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script src="${contextPath}/resources/js/order.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
			<link href="${contextPath}/resources/css/order.css" rel="stylesheet" type="text/css">
		
		</head>
		<body>
		
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<h1 style="position:relative; left:27%; top:100px;">주문/결제 완료</h1>
			</div>
			
			<div class = "container">
				<div class="row" id="orderDetail">
				<!-- 회원 배송 정보 -->
					<div class="col-sm-8" id="deliveryDetail">
						<table id="deliveryDetailtable">
							<tr>
								<th colspan="3">
									<h4><strong>주문자 정보</strong></h4>
								</th>
							</tr>
							<tr>
								<td>
								</td>
								<td>
									<div>
										<p>주문 번호</p>	
									</div>
								</td>
								<td>
									<div id="detailviewdiv">
										<p style="position:relative; top:1px; left:10px;">${orderDTO.order_num}</p>	
									</div>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div>
										<p>수령인 이름</p>
										<p>연락처</p>
										<p>주소</p>
										<p>&nbsp;</p>
										<p>&nbsp;</p>
										<c:if test="${not empty orderDTO.order_memo}">
											<p>배송 메모</p>
										</c:if>
									</div>
								</td>
								<td>
									<div id="detailviewdiv">
										<p>${orderDTO.order_name}</p>
										<p>${orderDTO.order_phone}</p>
										<p>${orderDTO.postnum}</p>
										<p>${orderDTO.address1}</p>
										<p>${orderDTO.address2}</p>
										<c:if test="${not empty orderDTO.order_memo}">
											<p>${orderDTO.order_memo}</p>
										</c:if>
									</div>
								</td>
							</tr>	
							<tr>
								<td></td>
								<td>
									<div>
										<p>회원 아이디</p>
										<p>결제 수단</p>
										<p>주문 날짜</p>
										<p>회원 이메일</p>
									</div>
								</td>
								<td>
									<div id="userorderinfoview">
										<p>${member1.userID}</p>
										<p>${orderDTO.paymethod}</p>
										<p><fmt:formatDate value="${orderDTO.order_date}" pattern="yyyy.MM.dd  a hh: mm: ss"/></p>
										<p>${member1.email}</p>
									</div>
								</td>
							</tr>					
						</table>
						 
						<br/>
					</div>
					
					<!-- 결제 요약 -->
					<div class="col-sm-4" id="orderSumaryDiv" style="padding:0;">
						<table id="orderListTable">
							<thead>
								<tr>
									<th style="background:#9a9a9a; color:white;" id="orderProductNameTh"><strong>상품 이름</strong></th>
									<th style="background:#9a9a9a; color:white;"><strong>상품 가격</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orderDetailList}" var="list">
									<tr id="cartListTbodyTr">
										<td class="orderProductNameTd">
											<c:choose>
												<c:when test="${list.product_name.length() > 15}">
													<a href="/product/productDetail?product_code=${list.product_code}&reviewPageNum=1">
														${list.product_name.substring(0, 16)} ⋯
													</a>
												</c:when>
												<c:otherwise>
													<a href="/product/productDetail?product_code=${list.product_code}">
														${list.product_name}
													</a>	
												</c:otherwise>
											</c:choose>
											<div>
												<p>옵션 : ${list.product_size}</p>
												<p>수량 : ${list.product_amount}</p>
											</div>
										</td>
										<td><fmt:formatNumber value="${list.product_price}" pattern="#,###원"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<table id="orderSumaryTable">
							<tr class="orderSumaryTr">
								<td>총 상품 금액</td>
								<td class="orderPrice" id="orderSum"><fmt:formatNumber value="${orderSum}" pattern="#,###원"/></td>
							</tr>
							<tr class="orderSumaryTr">
								<td>총 할인 금액</td>
								<td class="orderPrice" id="discount"><fmt:formatNumber value="${orderSum /10}" pattern="#,###원"/></td>
							</tr>
							<tr class="orderSumaryTr">
								<td>총 배송비</td>
								<td class="orderPrice" id="deliveryFee">
									<c:choose>
										<c:when test="${orderSum > 50000}">
											<fmt:formatNumber value="5000" pattern="#,###원"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="3000" pattern="#,###원"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr class="orderSumaryTr" id="totalBill">
								<td>결제 금액</td>
								<td class="orderPrice" id="totalBillTd">
									<c:choose>
										<c:when test="${orderSum > 50000}">
											<fmt:formatNumber value="${orderSum+5000-(orderSum /10)}" pattern="#,###원"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${orderSum+3000-(orderSum /10)}" pattern="#,###원"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
						<table id="AfterCareTable"> 
							<tr>
								<td><a href="/product/productList?product_color=white&product_type=bed,bath,living&page=1&array_type=r"><button type="button" style="color:#9a9a9a; background:white;">상품추가구매</button></a></td>
								<td><button type="button" style="color:white; background:#9a9a9a;" onclick="fn_myorderpage()">마이페이지이동</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<input type="hidden" id = "userID" value="${member1.userID }">
			
			<!-- 하단 메뉴 -->
			<jsp:include page="../common/footer.jsp" flush="false"/>			

	</body>
</html>