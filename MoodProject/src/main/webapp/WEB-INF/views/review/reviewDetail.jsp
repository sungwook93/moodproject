<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 상세 정보</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			
		  <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
		  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		  <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
		  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
			
			<!-- font -->
		   <link rel="preconnect" href="https://fonts.googleapis.com">
		   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		   <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
			
			<link href="${contextPath}/resources/css/review.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/review.js"></script>
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			
			<hr/><br/>
			<h2 style = "text-align: center; font-family: 'Sunflower', sans-serif;">Review Detail</h2>
			<br/>
			<br/>
				
				<div class="container">
				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="review_subject" name="review_subject"  value="${reviewDTO.review_subject}" readonly/>
						</div>
						<label class="col-sm-1 control-label">리뷰 별점</label>
						<div class="col-sm-3">
							<input type="number" class="form-control" id="review_star" name="review_star" min = "1" max="5" value="${reviewDTO.review_star}" style = "width:160px;" readonly/>
						</div>
					</div>
					<div class="form-group">	
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-2">
						<c:choose>
								<c:when test="${member1.grade == 7}">
									<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${reviewDTO.userID }" readonly/>
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${reviewDTO.userID }" readonly/>
								</c:otherwise>
							</c:choose>
						</div>
						
						<label class="col-sm-1 control-label">상품 목록</label>
						<div class="col-sm-3">	
							<input type="text" class="form-control" id="product_type" name="product_type" value = "${reviewDTO.product_type}" readonly/>
						</div>
						<label class="col-sm-1 control-label">상품 이름</label>
						<div class="col-sm-3">	
							<input type="text" class="form-control" id="product_name" name="product_name" value = "${reviewDTO.product_name}" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">리뷰 사진</label>	
						<div class="col-sm-2">
							<div style = "background-color: #EEEEEE; height: 350px; width: 940px; border-radius: 5px;">
							<table>
								<tr>
									<c:if test="${imagesList.images01 != null}" ><td class="subImg1">
										<img src="/image/displayImage1?name=${imagesList.images01}" id="img1" class="subImg" style = "height: 350px; width : 470px;"/>
									</td></c:if>
									<c:if test="${imagesList.images02 != null}" >
									<td class="subImg1">
										<img src="/image/displayImage1?name=${imagesList.images02}" id="img2" class="subImg" style = "height: 350px; width : 470px;"/>
									</td>
									</c:if>
									<!-- 수정해야함 -->
									<c:if test="${imagesList.images01 != null && imagesList.images02 != null}" ><td class="subImg1">
										<img src="/image/displayImage1?name=${imagesList.images01}" id="img1" class="subImg" style = "height: 350px; width : 470px;"/>
									</td></c:if>
								</tr>
							</table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="review_content" name="review_content" rows="10" cols="160"  readonly>${reviewDTO.review_content}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">첨부 파일</label>
						
						<form class = "form-horizontal" action = "${contextPath }/util/upload/uploadForm" method = "post" enctype = "multipart/form-data" target = "iframe1">
							<!-- name = "file"의 변수명과 컨트롤러의 MultipartFile file과 일치해야 한다. -->
							<div class = "form-group">
								<div class = "col-sm-6">
									<input type = "file" id="file" class = "btn btn-default" name = "file" style = "width: 600px;" multiple/>
								</div>
							</div>
							
							<!-- iframe에 업로드한 결과를 출력한다. 
							<div class = "form-group">
								<div class = "col-sm-10">
									<iframe name = "iframe1"></iframe>
								</div>
							</div>
							-->
						</form>
						
					</div>
				<div id="boardDetailBtn">
					<button type="button" class="btn btn-light" onclick="location.href='/review/reviewList?page=1'">
						<span> 전체 목록 보기 </span>
					</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 작성자와 로그인한 사람의 아이디가 같거나 관리자일 때 나오는 부분 -->
					<c:choose>
						<c:when test="${reviewDTO.userID == member1.userID or member1.grade == 7}">
							<button type="button" class="btn btn-light" onclick="location.href = '/review/reviewUpdateForm?review_bno=' + ${reviewDTO.review_bno}" style = "font-family: 'Sunflower', sans-serif;">
								<span> 게시글 수정</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="fn_reviewDelete(${reviewDTO.review_bno})">
								<span> 게시글 삭제</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
					</c:choose>
				</div>
				<div>
				<input type="hidden" id="review_bno" value="${reviewDTO.review_bno}">
				</div>
				<hr/>
				
				<!-- 댓글창 -->
				<table id="boardDetailCommentList">
					<!-- 등록된 댓글 보여주는 부분 -->
					<tr>
						<td class="comment15 commentLabel">
							<label>댓  글  목  록</label>
						</td>
						<!-- 내용 -->
						<!-- ${boardDTO.userID}는 게시글 작성자 아이디 /// ${member1.userID} 현재 회원 아이디 -->
						<td class="comment70">
							<table>
								<c:choose>
									<c:when test="${not empty commentList}">
										<c:forEach items="${commentList}" var="comment" varStatus="status">
											<tr>
												<td>
													<!-- 관리자이면서 자기 글일 경우 삭제 버튼 가능  -->
													<c:if test="${member1.grade == 7 or member1.userID == reviewDTO.userID }">
														${comment.userID}&nbsp; :&nbsp;&nbsp;<input type = "text" id = "${status.count}" value = "${comment.reply_content}" style = "background-color: #c1c1c1; border: none; width: 600px;" readonly/>
														<a id="commentUpdateB${status.count}" style = "display:none; width: 30px;" onclick="fn_updateComment(${status.count},${comment.imsi_bno}, ${comment.review_bno});"> 등록</a>
														<a id="commentUpdateA${status.count}" onclick="fn_updateOpen(${status.count}, ${comment.imsi_bno});"> 수정</a>
														<a> | </a>
														<a id="commentDeleteA" onclick="fn_deleteComment(${status.count}, ${reviewDTO.review_bno}, ${comment.imsi_bno});"> 삭제</a>
													</c:if>
													<input type="hidden" id="imsi_bno" value="${comment.imsi_bno}"/>
													<input type="hidden" id="reply_bno" value="${comment.reply_bno}"/>
													<!-- 글쓴이면 자기글 수정 삭제 가능  -->
													<c:if test="${member1.grade != 7 and member1.userID != reviewDTO.userID}">
														${comment.userID}&nbsp; :&nbsp;&nbsp;<input type = "text" id = "${status.count}" value = "${comment.reply_content}" style = "background-color: #c1c1c1; border: none; width: 600px;" readonly/>
													</c:if>
													<input type="hidden" id="reply_bno" value="${comment.reply_bno}"/>
													<input type="hidden" id="review_bno" value="${comment.review_bno}"/>
													<input type="hidden" id="userID1" value="${member1.userID}"/>
													<input type="hidden" id="statuscount" value="${status.count}"/>
													<input type="hidden" id="imsi_bno" value="${comment.imsi_bno}"/>
												</td>
											</tr>
										</c:forEach>
									</c:when>	
									<c:otherwise>
										<tr>
											<td>등록된 댓글이 없습니다.</td>
										</tr>					
									</c:otherwise>
								</c:choose>
							</table>
						</td>
						<!-- 날짜 -->
						<td class="comment15">
							<table>
								<c:choose>
									<c:when test="${not empty commentList}">
										<c:forEach items="${commentList}" var="comment">
											<tr>
												<td>${comment.reply_regDate}</td>
											</tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</table>
						</td>
					</tr>
					<!-- 댓글 작성 부분(관리자만 오픈됨) -->
					<c:choose>
						<c:when test="${member1.grade == 7  or reviewDTO.userID == member1.userID}">
							<tr>
								<td class="comment15 commentLabel">
									<br/>
									<label>댓  글  작  성</label>
								</td>
								<td class="comment70">
									<br/>
									<img src = "${contextPath }/resources/images/user.png" style = "width: 20px; height: 20px;"/>&nbsp;&nbsp;${member1.userID}
									<br/><br/>
									<textarea rows="3" cols="110" id="reply_content" placeholder="댓글 입력"></textarea>
								</td>
								<td class="commentButton">
									<br/>
									<button style="position:relative; left:25%; background-color: white; color: black; border: none; border-radius:5px; padding:4px; font-size:1.0em;" type="button" class="btn-light" type="button" onclick="fn_commentRegister(${reviewDTO.review_bno});">등 록</button>
								</td>
							</tr>
							<input type="hidden" id="userID1" value="${member1.userID}"/>
						</c:when>
					</c:choose>
				</table>
				<br/>
			</div>
			
			<jsp:include page="../common/footer.jsp" flush="false"/>
		
		</body>

		<style>
		   * {
		      font-family: 'Sunflower','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>	
	</html>






