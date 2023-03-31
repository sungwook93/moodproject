<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="now" value="<%=new java.util.Date()%>" /><!-- 현재시간 -->
<c:set var="sysYear"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 목록 보기</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/board.js"></script>
		   
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
					<a href="/board/boardList?page=1" id="selectedA">Q & A</a>
					<a href="/other/boardListFqa">Review</a>
				</div>
			</div>
			
			<div class="container">
				<!-- 게시글 작성 버튼 -->
				<div id="registerBtnBox">
					<button type="button" class="btn btn-secondary" onclick="location.href='/board/boardRegisterForm'" style = "float : right;" ><strong>QnA 작성하기</strong></button>
					<br/>	
				</div>
				<br/>
				<div>
					<!-- 글 목록 -->
					<table id="boardTable">
						<thead>
							<tr>
								<th class="boardNo">No</th>
						    	<th class="boardTitle">제목</th>
						    	<th class="boardUser">작성자</th>
						    	<th class="boardDate">작성일자</th>
						    	<th class="boardNo">조회수</th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach var="board" items="${boardList}">
								<tr>
									<td class="boardNo">${board.qna_bno}</td>  
									
									<td class="boardTitle">
									<c:choose>
									<c:when test="${(sysYear == board.qna_regDate)}">
											<div class = "boardSubjcet" style = "display:-webkit-box; -webkit-line-clamp:1; -webkit-box-orient:vertical; overflow: hidden; text-overflow: ellipsis;" >${board.qna_subject}&nbsp;&nbsp;<img src="../resources/images/new.png" height= "13px"width="25px" alt="new" /></div>
									</c:when>
									<c:otherwise>
										<div class = "boardSubjcet" style = "display:-webkit-box; -webkit-line-clamp:1; -webkit-box-orient:vertical; overflow: hidden; text-overflow: ellipsis;" >${board.qna_subject}</div>
									</c:otherwise>
									</c:choose>	
									</td>
									<td class="boardUser">${board.userID}</td>
									<td class="boardDate"><fmt:formatDate value="${board.qna_regDate}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/></td>
									<td class="boardNo"><fmt:formatNumber value="${board.qna_readCount}" pattern="#,###"/></td>
								</tr>
								<tr style = "display:none;">
									<td colspan="5" style = "padding: 10px 40px;"><a href="/board/boardDetail?qna_bno=${board.qna_bno}" style = "color: black;">${board.qna_content}</a></td>
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
								<option value="c" <c:if test="${searchType == 'c'}">selected</c:if>>내용</option>
								<option value="u" <c:if test="${searchType == 'u'}">selected</c:if>>글쓴이</option>
							</select>
						</div>
						<div id="boardSearchBoxDiv">
							<input type="text" id="searchKeyword" value="${keyword}" style = "background-color: #d4c6bb;"/>
						</div>
							<button id="searchBtn" class="glyphicon glyphicon-search" style = "background-color: #d4c6bb;"></button>
					</div>
					
					<br/>
					<div class="col-sm-12" align="center">
						<ul class="btn-group pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href='<c:url value="/board/boardList?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
				
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
								<li>
									<a href='<c:url value="/board/boardList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
								</li>
							</c:forEach>
				
							<c:if test="${pageMaker.next}">
								<li>
									<a href='<c:url value="/board/boardList?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 스크립트에서 사용할 hidden 요소 -->
			<form id="formList" action="/board/boardList?page=1" method="get">
				<input type='hidden' name='searchType'	value='${searchType}'/>
				<input type="hidden" name="keyword"		value="${keyword}"/>
			</form>
		
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
		<script>
		$('#qna_content').summernote('pasteHTML', data);
		</script>
		
	</html>









