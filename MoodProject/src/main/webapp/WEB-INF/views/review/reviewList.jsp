<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 목록 보기</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/review.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/review.js"></script>
		   
		   <!-- font -->
		   <link rel="preconnect" href="https://fonts.googleapis.com">
		   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		   <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
		</head>
		<body>
			<!-- 로그인 확인용 -->
			<script src="${contextPath}/resources/js/isLogOn.js"></script>
			<div>
				<input type="hidden" id="isLogOnT" value="${isLogOn}"/>
			</div>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<hr/>
			<div id="mainTitle">
				<div id="boardTitle">
					<a href="/board/boardList?page=1">Q & A</a>
					<a href="/review/reviewList?page=1" id="selectedA">Review</a>
				</div>
			</div>
			
			<div class="container">
				<!-- 게시글 작성 버튼 -->
				<div id="registerBtnBox">
					<button type="button" class="btn btn-secondary" onclick="location.href='/review/reviewRegisterForm'" style = "float : right;" ><strong>리뷰작성하기</strong></button>
					<br/>	
				</div>
				<br/>
					<!-- 글 목록 -->
					<table id="boardTable">
						<thead>
							<tr>
								<th class="reviewNo">No</th>
								<th class= "reviewTitle">상품 정보</th>
						    	<th class="reviewTitle">제목</th>
						    	<th class="reviewTitle">내용</th>
						    	<th class="reviewUser">작성자</th>
						    	<th class="reviewDate">작성일자</th>
						    	<th class="reviewStar">평점</th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach var="review" items="${reviewList}">
								<tr>
									<td class="reviewNo">${review.review_bno}</td> 
									<td class="reviewTitle">${review.product_type}&nbsp;&nbsp;/&nbsp;&nbsp;${review.product_name}</td>
									<td>
									<div class = "boardSubjcet" style = "display:-webkit-box; -webkit-line-clamp:1; -webkit-box-orient:vertical; overflow: hidden; text-overflow: ellipsis;" >${review.review_subject}
										<small><b>[&nbsp;${review.reply_count}&nbsp;]</b></small></div>
									</td>
									<td class="reviewTitle"><a href="/review/reviewDetail?review_bno=${review.review_bno}" style = "color: black;">${review.review_content}</a></td>
									<td class="reviewUser">${review.userID}</td>
									<td class="reviewDate"><fmt:formatDate value="${review.review_date}" pattern="yyyy년 MM월 dd일"/></td>
									<td class="reviewStar"><fmt:formatNumber value="${review.review_star}" pattern="#,###"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			

					<!-- 검색종류  -->
					<div id="boardSearchBox">
						<div>
							<select id="searchType" class="form-control">
								<option disabled selected>검색종류</option>
								<option value="s" <c:if test="${searchType == 's'}">selected</c:if>>제목</option>
								<option value="c" <c:if test="${searchType == 'c'}">selected</c:if>>상품명</option>
								<option value="u" <c:if test="${searchType == 'u'}">selected</c:if>>작성자</option>
							</select>
						</div>
						<div id="boardSearchBoxDiv">
							<input type="text" id="searchKeyword" value="${keyword}" style = "background-color: #c1c1c1;"/>
						</div>
							<button id="searchBtn" class="glyphicon glyphicon-search" style = "background-color: #c1c1c1;"></button>
					</div>
					
					<br/>
					<div class="col-sm-12" align="center">
						<ul class="btn-group pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href='<c:url value="/review/reviewList?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
				
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
								<li>
									<a href='<c:url value="/review/reviewList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
								</li>
							</c:forEach>
				
							<c:if test="${pageMaker.next}">
								<li>
									<a href='<c:url value="/review/reviewList?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
								</li>
							</c:if>
						</ul>
					</div>
		
			
			<!-- 스크립트에서 사용할 hidden 요소 -->
			<form id="formList" action="/review/reviewList?page=1" method="get">
				<input type='hidden' name='searchType'	value='${searchType}'/>
				<input type="hidden" name="keyword"		value="${keyword}"/>
			</form>		
		</div>		
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>	
		
		<style>
		   * {
		      font-family: 'Sunflower','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>	
	</html>








