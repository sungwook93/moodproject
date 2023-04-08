<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세 페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		
			<script src="${contextPath}/resources/js/cart.js"></script>
			<link href="${contextPath}/resources/css/cart.css" rel="stylesheet" type="text/css">
			
			<!-- 다음 API -->
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<!-- api연동 시스템 -->
			<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	
	<!-- 상단 메뉴 -->
	<jsp:include page="../common/topMenu.jsp" flush="false"/>
	
	<div class="container" id="cartListbox"> 
		<div class="ordersub" style="margin-right:300px; position:relative; bottom:0; width:200px;">
			<h3>주문서 작성</h3>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0;">
			<span class="glyphicon glyphicon-shopping-cart" style="font-size:3em;"></span>
		</div>
		<div class="ordersub">
			<h6>STEP1</h6>
			<h4>장바구니</h4>
		</div>
		<div class="ordersub" style="border-radius:50px; padding:15px; margin-bottom:20px; margin-right:0; background-color: #8D999E;">
			<span class="glyphicon glyphicon-list-alt" style="font-size:3em; color: white;"></span>
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
	
	<div class="container">
		<table id="orderListTable">
			<tr style="text-align:center;">
				<th style="width:3%;"></th>
				<th style="text-align:center;">상품정보</th>
				<th style="width:20%;">수량</th>
				<th style="width:20%;">상품 가격</th>
				<th style="width:20%;">상품 총가격</th>
			</tr>
			
		<c:forEach items="${cartList}" var="cart" varStatus="status">
			<tr style="text-align:center;">
				<td>
				</td>
				<td>
					<div style="display:flex">
						<div style="width:100px;">
						<img src="/image/displayImage?name=${cart.product_code}" class="cartImg"/>					
						</div>
						<div style="text-align:left; margin-left:20px;">
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
				<td style="text-align:center;">
												<p class="amount" id="product_amount${status.count}">${cart.product_amount}</p>
												
												
												<input type="hidden" id="price${status.count}" value="${cart.product_price}"/>
				</td>
				<td style="text-align:center;" id="product_price${status.count}" >
					<fmt:formatNumber value="${cart.product_price}" pattern="##,###,###원"/>
				</td>	
				<td style="text-align:center;">
					<fmt:formatNumber value="${cart.product_price * cart.product_amount}" pattern="##,###,###원"/>	
				</td>
			</tr>
		</c:forEach>
			
		</table>
	</div>
	
	<br/><br/>
	<div class="container" id="userOrderArea">
			<!-- 회원 배송 정보 -->
				<div class="col-sm-8" id="userInfoArea">
					<table id="userInfoTable">
						<tr>
							<th colspan="2">
								<h4><strong>주문자 정보</strong></h4>
							</th>
						</tr>
						<tr>
							<td>* 주문자명</td>
							<td><input type="text" class="form-control" id="name" value="${member1.name}"/></td>
						</tr>	
						<tr>
							<td>* 휴대폰 번호</td>
							<td><input type="text" class="form-control" id="phone" value="${member1.phone}"/></td>
						</tr>								
						<tr>
							<td>이메일</td>
							<td><input type="text" class="form-control" id="email" value="${member1.email}"/></td>
						</tr>	
						<tr>
							<th colspan="2">
								
							</th>
						</tr>
						<tr>
							<td>* 수령인</td>
							<td><input type="text" class="form-control"  value="${member1.name}"/></td>
						</tr>	
						<tr>
							<td>배송지 선택</td>
							<td>
								<input type="radio" id="originAdress" name="deliveryAddress" value="originAdress" checked/> 기본 배송지
								<input type="radio" id="newAdress" name="deliveryAddress" value='newAdress' /> 새로운 배송지
							</td>
						</tr>	
						<tr>
							<td>* 휴대폰 번호</td>
							<td><input type="text" class="form-control" id="deliveryPhone" value="${member1.phone}"/></td>
						</tr>							
						<tr>
							<td>* 배송지</td>
								<td>
									<div>
										<div class="col-sm-9" style="padding: 0px;">
											<input type = "text" class="form-control postNumInline" id="deliveryPostnum" value="${member1.postnum}" readonly/>
										
										</div>
										<div class="col-sm-2">
											<input type = "button" class=" form-control postNumInline" id="postBtn" onclick = "daumZipCode()" value = "우편번호 검색" style = "width: 110px;" disabled />
										
										</div>
									</div>
									<input type = "text"  class="form-control" id="deliveryAddress1" value="${member1.address1}"  readonly/>
									<input type="text"  class="form-control" id="deliveryAddress2" value="${member1.address2}"/>
								</td>
							</tr>	
							<tr>
								<td>배송 메모</td>
								<td>
									<select class="form-control" id="orderMemoSelect" onchange="fn_orderMemo();">
										<option disabled selected>배송 메모를 선택해주세요</option>
										<option>배송 전에 미리 연락 바랍니다.</option>
										<option>부재시 경비실에 맡겨주세요.</option>
										<option>부재시 전화나 문자 주세요.</option>
										<option>문 앞에 놔주세요.</option>
										<option value="makeDeliveryMemo">직접 입력</option>
									</select>
									<input type="text" id="orderMemo" style="display: none; width: 100%">
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<th colspan="2">
									<h4><strong>결제 수단</strong></h4>
								</th>
							</tr>
							<tr id="payMethod">
								<td colspan="2">
									<button type="button" class="form-control payMethodBtn" id="payMethodCash" onclick="cashInfo()">가상계좌이체</button>
									<button type="button" class="form-control payMethodBtn" id="payMethodCard">카드결제</button>
									<button type="button" class="form-control payMethodBtn" id="payMethodKakao" onclick="iamporta()">카카오페이</button>
									<!-- 결제 수단 구분을 위한 hidden input -->
									<input type="hidden" id="payMethodInput" value="1"/>
								</td>
							</tr>
							<tr id="payMethodInfo">
								<td>
									<div class="container" id="payMethodInfodiv">
										<p>· 실시간 계좌이체를 이용하기 위해서는 계좌결제 앱이 설치되어 있어야 합니다.</p>
										<p>· 계좌이체는 ATM이나 은행 홈페이지에 접속하지 않고 홈페이지 내에서 즉시 결제, 출금되는 결제 방식 입니다.</p>
										<p>· 현재 약 20여개의 은행이 가능하며 현금영수증 발행은 결제 시 즉시 발급받으실 수 있습니다.</p>
									</div>
								</td>
							</tr>	
						</table>
					</div>
					
					<!-- 결제 요약 -->
					<div class="col-sm-4" id="orderSumaryDiv">
						<table id="orderSumaryTable">
							<tr>
								<td>최종 결제금액</td>
							</tr>
							<tr class="orderSumaryTr">
								<td>총 상품 금액</td>
								<td class="orderPrice" id="orderSum"><fmt:formatNumber value="${orderSum}" pattern="#,###원"/></td>
							</tr>
							<tr class="orderSumaryTr">
								<td>총 할인 금액</td>
								<td class="orderPrice" id="discount"><fmt:formatNumber value="111" pattern="#,###원"/></td>
							</tr>
							<tr class="orderSumaryTr">
								<td>총 배송비</td>
								<td class="orderPrice" id="deliveryFee">
									<c:choose>
										<c:when test="${orderSum > 50000}">
											<fmt:formatNumber value="0" pattern="#,###원"/>
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="3000" pattern="#,###원"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr class="orderSumaryTr" id="totalBill">
								<td>결제 금액</td>
								<td class="orderPrice" id="totalBillTd"></td>
							</tr>
							<tr id="personalInfoCheckTr" style="border:1px solid black;">
								<td colspan="2">
									<p><input type="checkbox" id="personalInfoCheck"/><strong> 상품 구매를 위한 개인정보 수집·이용 동의 (필수) </strong></p>
									<p id="personalInfoCheckP" onclick="fn_personalInfo()"> 보기</p>
									<p id="personalInfoCheckP2" onclick="fn_personalInfo2()" style="display:none;"> 닫기</p>
									
								</td>
							</tr>
							<tr id="personalInfoDetail">
								<td colspan="2">
									<div id="personalInfoDetailDiv">
										<p>1. 수집목적</p>
										<p>쇼핑몰 상품 구매 서비스 제공(상품구매, 물품배송, 청구서 발송), 고객 상담 및 불만처리, 상품 배송을 위한 배송지 확인 등</p>
										<p>2. 수집항목</p>
										<p>[필수] 성명, ID, 휴대전화 번호, 상품 구매정보, 결제정보, 은행계좌 정보</p>
										<p>[선택] 이메일 주소</p>
										<p>3. 보유기간</p>
										<p>개인정보 수집 및 이용목적 달성 시 관련 법령에 따른 기간까지 보관</p>
										<p>[전자상거래법에 따른 보관 기간]</p>
										<p>계약 또는 청약 철회 등에 관한 기록: 5년</p>
										<p>대금결제 및 재화 등의 공급에 관한 기록: 3년</p>
										<p>소비자의 불만 또는 분쟁처리에 관한 기록: 3년</p>
									</div>
								</td>
							</tr>
							<tr id="orderOkDetail">
								<td colspan="2">
									<p><input type="checkbox" id="orderOkCheckbox"/>주문할 상품의 상품명, 상품가격, 배송정보를 확인하였으며,</p>
									<p>구매에 동의(전자상거래법 제8조 제2항)</p>
									<p id="personalInfoCheckP3" onclick="fn_personalInfo3()"> 보기</p>
									<p id="personalInfoCheckP4" onclick="fn_personalInfo4()" style="display:none;"> 닫기</p>
								</td>
							</tr>
							<tr id="deliveryDetailInfo">
								<td colspan="2">
									<div id="deliveryDetailInfodiv">
									<p><strong>배송 지연/품절/파업으로 인한 발송 불가 안내</strong></p>
									<p>① 일부 상품은 상품 준비 기간 중 입고 사정 등으로 인하여 배송이 지연될 수 있습니다.</p>
									<p>② 택배 파업 지역의 경우 발송이 불가합니다.</p>
									<p>③ 결제 완료 후 품절이 발생할 수 있습니다.</p>
									<p>위와 같은 상황 발생 시 알림톡 안내 후 주문취소 및 환불 처리가 진행됩니다.</p>
									</div>
								</td>
							</tr>
							<tr id="orderOkCheck">
								
							</tr>
						</table>
						<table>
							<tr>
								<td colspan="2" id="postOrderBtn">
									<button type="button" class="form-control" onclick="fn_orderComplete()">결제하기</button>
								</td>
							</tr>
							<tr>
								<td colspan="2" id="backward" >
									<button type="button" class="form-control" onclick="location.href='/order/cartList?userID=' + ${member1.userID}">뒤로가기</button>  
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			<!-- 결제 스트립트에서 사용할 hidden 요소 -->
			<div>
				<input type="hidden" id="cartCount" value="${cartCount}"/>
				<input type="hidden" id="firstProduct" value="${cartList[0].product_name}"/>
				<input type="hidden" id="address1" value="${member1.address1}"/>
				<input type="hidden" id="address2" value="${member1.address2}"/>
				<input type="hidden" id="postnum" value="${member1.postnum}"/>
			</div>
			<div style="display:none;">
				<c:forEach items="${cartList}" var="list">
					<input type="checkbox" name="cartNum" value="${list.cart_num}" checked/>
				</c:forEach>
			</div>
			<!-- 배송지 관련 스크립트에서 사용할 hidden input -->
			<div>
				<input type="hidden" id="userID" value="${member1.userID}"/>
				<input type="hidden" id="address1"value="${member1.address1}"/>
				<input type="hidden" id="cartList" value="${cartList}"/>
				<input type="hidden" name="amount" value="${sum}" />
			</div>
						
					
				
				
	<!-- 하단 메뉴바 -->
	<jsp:include page = "../common/footer.jsp" flush = "false"/>
		
	</body>
	<script>
	$('#qna_content').summernote('pasteHTML', data);
	</script>
	
		
	<style>
		* {
		    font-family: 'Sunflower','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		  }   
	</style>	
</html>