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
		   
		   	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
			
			<script src="https://kit.fontawesome.com/2367b3dda5.js" crossorigin="anonymous"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
		   
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
				<!-- 게시글 작성 버튼 
				<div id="registerBtnBox">
					<button type="button" class="btn btn-secondary" onclick="location.href='/review/reviewRegisterForm'" style = "float : right;" ><strong>리뷰작성하기</strong></button>
					<br/>	
				</div>
				-->
				<br/>
					<!-- 글 목록 -->
					<div id="container">
						<table>
					<tr class="reviewList">
						<c:forEach items="${reviewList}" var="review" begin="0" end ="2">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 400px;">
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><img src="/image/displayImage1?review_bno=${review.review_bno}" style = "width: 350px; height: 350px;"/></a><br/><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.review_subject}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>상 품 정 보 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.product_type}&nbsp;&nbsp;/&nbsp;&nbsp;${review.product_name}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>작&nbsp;&nbsp;&nbsp;성&nbsp;&nbsp;&nbsp; 자 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.userID}</strong></a><br/>	
										<a href="/review/reviewDetail?review_bno=${review.review_bno}">
										<c:choose>
			                              <c:when test="${review.review_star == 0 }">
			                                 <c:forEach begin="1" end="5">
                                                   <span class="fa fa-star-o" style="color: orange;"></span>
                                          	</c:forEach>
			                              </c:when>
			                              <c:otherwise>
			                              별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 &nbsp;&nbsp;:&nbsp;&nbsp; 
			                                 <c:choose>
			                                    <c:when test="${review.review_star / 1 != 0}">
			                                       <c:forEach begin="1" end="${review.review_star/ 1}">
			                                          <span class="fa fa-star checked" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${review.review_star % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked" style="color: orange;"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${review.review_star / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(review.review_star / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>(${review.review_star })</strong>
			                              </c:otherwise>
			                              </c:choose>
										</a><br/>
										</div>
										<br/><br/>
									</td>
						</c:forEach>
					<tr class="reviewList">
						<c:forEach items="${reviewList}" var="review" begin="3" end ="5">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 400px;">
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><img src="/image/displayImage1?review_bno=${review.review_bno}" style = "width: 350px; height: 350px;"/></a><br/><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.review_subject}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>상 품 정 보 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.product_type}&nbsp;&nbsp;/&nbsp;&nbsp;${review.product_name}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>작&nbsp;&nbsp;&nbsp;성&nbsp;&nbsp;&nbsp; 자 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.userID}</strong></a><br/>	
										<a href="/review/reviewDetail?review_bno=${review.review_bno}">
										<c:choose>
			                              <c:when test="${review.review_star == 0 }">
			                                 <c:forEach begin="1" end="5">
                                                   <span class="fa fa-star-o" style="color: orange;"></span>
                                          	</c:forEach>
			                              </c:when>
			                              <c:otherwise>
			                              별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 &nbsp;&nbsp;:&nbsp;&nbsp; 
			                                 <c:choose>
			                                    <c:when test="${review.review_star / 1 != 0}">
			                                       <c:forEach begin="1" end="${review.review_star/ 1}">
			                                          <span class="fa fa-star checked" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${review.review_star % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked" style="color: orange;"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${review.review_star / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(review.review_star / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>(${review.review_star })</strong>
			                              </c:otherwise>
			                              </c:choose>
										</a><br/>
										</div>
										<br/><br/>
									</td>
						</c:forEach>
					</tr>
					<tr class="reviewList">
						<c:forEach items="${reviewList}" var="review" begin="6" end ="8">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 400px;">
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><img src="/image/displayImage1?review_bno=${review.review_bno}" style = "width: 350px; height: 350px;"/></a><br/><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.review_subject}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>상 품 정 보 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.product_type}&nbsp;&nbsp;/&nbsp;&nbsp;${review.product_name}</strong></a><br/>
										<a href="/review/reviewDetail?review_bno=${review.review_bno}"><strong>작&nbsp;&nbsp;&nbsp;성&nbsp;&nbsp;&nbsp; 자 &nbsp;&nbsp;:&nbsp;&nbsp; ${review.userID}</strong></a><br/>	
										<a href="/review/reviewDetail?review_bno=${review.review_bno}">
										<c:choose>
			                              <c:when test="${review.review_star == 0 }">
			                                 <c:forEach begin="1" end="5">
                                                   <span class="fa fa-star-o" style="color: orange;"></span>
                                          	</c:forEach>
			                              </c:when>
			                              <c:otherwise>
			                              별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;점 &nbsp;&nbsp;:&nbsp;&nbsp; 
			                                 <c:choose>
			                                    <c:when test="${review.review_star / 1 != 0}">
			                                       <c:forEach begin="1" end="${review.review_star/ 1}">
			                                          <span class="fa fa-star checked" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${review.review_star % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked" style="color: orange;"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${review.review_star / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(review.review_star / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>(${review.review_star })</strong>
			                              </c:otherwise>
			                              </c:choose>
										</a><br/>
										</div>
										<br/><br/>
									</td>
						</c:forEach>
					</tr>
				</table>
			</div>
			<br/>
			<br/>			
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
					
					<br/><br/>

			<!-- 페이징 처리 -->
			<div class="col-sm-12" align="center">
						<ul class="btn-group pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href='<c:url value="/review/reviewList?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
								<li>
									<a href="<c:url value='/review/reviewList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}'/>"><i>${pageNum}</i></a>
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








