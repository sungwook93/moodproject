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
			<title>주문 완료 상세 페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script src="${contextPath}/resources/js/order.js"></script>	
				
			<link href="${contextPath}/resources/css/ordercomplete.css" rel="stylesheet" type="text/css">
		
		</head>
		<body>
		
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div class="container" style="text-align:center;" id="mainTitle">
				<hr/>
				<h1>주문 내역</h1>
				<h4>${orderDTO.order_num}</h4>
			</div>
			<div class="container" style="display:flex;">
			
			<div>
						<!-- 주문자 정보 -->
					<h3><strong>주문자 정보</strong></h3>	
						<table class="orderTable orderTableOrderDTO">
							
							<tr>
								<td class="orderTableOrderDTO20">
									주문자명
								</td>
								<td class='completeDetailOrderDTO30'>
									${member1.name}
								</td>
								<td class="orderTableOrderDTO20">
									이메일
								</td>
								<td class='completeDetailOrderDTO30'>
									${member1.email}
								</td>
							</tr>
							<tr>
								<td class="orderTableOrderDTO20">
									휴대폰번호
								</td>
								<td>
									${member1.phone}
								</td>
								<td class="orderTableOrderDTO20">
									주문 날짜
								</td>
								<td class='orderTableOrderDTO30'>
									<fmt:formatDate value="${orderDTO.order_date}" pattern="yyyy.MM.dd a hh: mm"/>
								</td>
							</tr>
						</table>
					
						<!-- 배송, 결제 정보 -->
					<h3><strong>배송지 정보</strong></h3>
						<table class="orderTable orderTableOrderDTO">
							<tr>
								<td class="orderTableOrderDTO20">
									수령인								
								</td>
								<td class='orderTableOrderDTO30'>
									${orderDTO.order_name}
								</td>
								<td class="orderTableOrderDTO20">
									휴대폰번호
								</td>
								<td class='orderTableOrderDTO30'>
									${orderDTO.order_phone}
								</td>
							</tr>
							<tr>
								<td class="orderTableOrderDTO20">
									주소
								</td>
								<td colspan="3">
									(${orderDTO.postnum}) ${orderDTO.address1} ${orderDTO.address2}
								</td>
							</tr>
							<tr>
								<td class="orderTableOrderDTO20">
									배송메모
								</td>
								<td colspan="3">
									${orderDTO.order_memo }
								</td>
							</tr>
						</table>
						<!-- 최종 결제 금액 정보 -->
							<h3><strong>최종 결제 금액 정보</strong></h3>
						<table class="orderTable" id="orderTableBill">
							<tr>
								<td>
									<table style="width:100% !important;">
										<tr>
											<td style="background:#9a9a9a; color:white;">총 상품 금액</td>
											<td class="orderTableTotalMoney" id="orderSum"><fmt:formatNumber value="${orderSum}" pattern="#,###원"/></td>
										</tr>
									</table>
								</td>
								<td>
									<table style="width:100% !important;">
										<tr>
											<td style="background:#9a9a9a; color:white;">총 할인 금액</td>
											<td class="orderTableTotalMoney" id="discount"><fmt:formatNumber value="${orderSum /10}" pattern="#,###원"/></td>
										</tr>
									</table>
								</td>
								<td>
									<table style="width:100% !important;">
										<tr>
											<td style="background:#9a9a9a; color:white;">총 배송비</td>
											<td class="orderTableTotalMoney" id="deliveryFee">
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
									</table>
									
								</td>
								<td>
									<table style="width:100% !important;">
										<tr>
											<td style="background:#9a9a9a; color:white;">총 주문 금액</td>
											<td class="orderTableTotalMoney" id="totalBillTd">
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
								</td>
							</tr>
						</table>
						
			</div>
			<div style="width:500px; margin-left:100px;">
				<h3><strong>같이 주문한 상품 정보</strong></h3>
						<!-- 주문 상품 정보 -->
						<table class="orderTable" id="orderTableProduct" style="width:500px !important;">
							<thead style="width:500px !important;">
								<tr id="orderTableProductTh" style="width:500px !important;">
									<th colspan="2" id="orderTableProductName" style="width:300px;">상품/옵션 정보</th>
									<th style="width:80px;">상품 가격</th>
									<th style="width:100px;">상품평</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orderDetailList}" var="list">
									<tr>
										<td>
											<img src="/image/displayImage?name=${list.product_code}"/>
										</td>
										<td class="orderTableProductNameTd">
											<a href="/product/productDetail?product_code=${list.product_code}&reviewPageNum=1">${list.product_name}</a>
											<div>
												<p>옵션: ${list.product_size}</p>
												<p>수량: ${list.product_amount}</p>
											</div>
										</td>
										<td>
											<fmt:formatNumber value="${list.product_price}" pattern="#,###원"/>
										</td>
										<td>
											<c:choose>
												<c:when test="${list.review_yn == 'N' and list.userID == member1.userID}">
													<button type="button" class="btn btn-secondary"
													onclick="location.href = '/review/reviewRegisterForm?product_code=${list.product_code}&order_num=${list.order_num}'"
													style = "float : right;" ><strong>리뷰작성하기</strong></button>
												</c:when>
												<c:when test="${list.review_yn == 'Y'}">
													<a href="/review/reviewList">작성된 리뷰 보기</a>
												</c:when>
												<c:otherwise>
													작성된 리뷰 없음
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</div>
			</div>
			
			<!-- 하단 메뉴 -->
			<jsp:include page="../common/footer.jsp" flush="false"/>			

	</body>
</html>