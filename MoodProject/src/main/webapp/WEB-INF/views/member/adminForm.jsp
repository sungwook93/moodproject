<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
	
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
			
			
			<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/admin.js"></script>	
</head>
<body>
	
	<jsp:include page ="../common/topMenu.jsp"></jsp:include>
	
	
	<div class="container" id="adminarea">
		<div id="admintitle"><h4 id="titlesection1">게시글관리</h4><h1 id="titlesection2">상품관리</h1><h4 id="titlesection3">회원관리</h4></div>
		<div id="admintitle2"><h4 id="titlesection4">상품관리</h4><h1 id="titlesection5">회원관리</h1><h4 id="titlesection6">게시글관리</h4></div>
		<div id="admintitle3"><h4 id="titlesection7">회원관리</h4><h1 id="titlesection8">게시글관리</h1><h4 id="titlesection9">상품관리</h4></div>
		
		<div class="container" id="pdtable"> 
		<!-- 상품 관련 테이블 -->
		<table id="admintable" style="text-align:center;">
			<tr class="thead">
				<td style="width:10%;">상품번호</td>
				<td style="width:50%;">상품이름</td>
				<td style="width:10%;">이미지</td>
			</tr>
			<c:forEach var="product" items="${productList}">
			<tr>
				<td>${product.product_code}</td>
				<td><a href="/product/productDetail?product_code=${product.product_code}">${product.product_name}</a></td>
				<td><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></td>
			</tr>
			</c:forEach>
		
		</table>
		<div class="container"  id="productBottom">
		<input type="button" onclick="location.href='/product/productRegisterForm'" value="상품등록" id="productregister">
		<!-- 페이징 처리 -->
			<div class="col-sm-12" align="center">
				<ul class="btn-group pagination">
					<c:if test="${pageMaker.prev}">
						<li>
							<a href='<c:url value="/member/adminForm.do?product_type=${type}?page=${pageMakerf.startPage-1}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
						</li>
					</c:if>
		
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
						<li>
							<a href='<c:url value="/member/adminForm.do?product_type=${type}&page=${pageNum}"/>'><i>${pageNum}</i></a>
						</li>
					</c:forEach>
				
					<c:if test="${pageMaker.next}">
						<li>
							<a href='<c:url value="/member/adminForm.do?product_type=${type}?page=${pageMaker.endPage+1}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- 회원 관련 테이블 -->
		<table id="admintable2">
			<tr class="thead">
				<td style="width:10%;">관리자여부</td>
				<td style="width:50%;">회원아이디</td>
				<td>등록일자</td>
				<td style="width:20%;">회원삭제</td>
			</tr>
			<c:forEach var="member" items="${memberList}" varStatus="status">
			<tr>
				<td>${member.grade}</td>
				<td id="userIDval">${member.userID}</td>
				<td>${member.joinDate}</td>
				<td><button type="button" id="listRemoveBtn" class="btn btn-sm" onclick="fn_removeMember('${member.userID}');" >삭제</button></td>
			</tr>
			</c:forEach>
		</table>
		
		<!-- 게시글 관련 테이블 -->
		<table id="admintable3">
			
			<tr class="thead">
				<td style="width:10%;">게시글번호</td>
				<td style="width:50%;">게시글제목</td>
				<td style="width:20%;">작성일자</td>
			</tr>
			<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.qna_bno}</td>
				<td><a href="/board/boardDetail?qna_bno=${board.qna_bno}">${board.qna_subject}</a></td>
				<td>${board.qna_regDate}</td>
			</tr>
			</c:forEach>
		</table>
		
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	<jsp:include page ="../common/footer.jsp"></jsp:include>
</body>
</html>