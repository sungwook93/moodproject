<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 상세 정보 수정</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			
			<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>	
            
			<!-- font -->
		   <link rel="preconnect" href="https://fonts.googleapis.com">
		   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		   <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">
				
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/board.js"></script>	
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<hr/><br/>
			<h2 style = "text-align: center; font-family: 'Sunflower', sans-serif;">Q & A UpdateForm</h2>
			<br/>
			<br/>
			
			<div class="container">
				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="qna_subject" name="qna_subject" maxlength="60" placeholder="제목 입력" value="${boardDTO.qna_subject}"/>
						</div>
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="qna_content" name="qna_content" rows="10" cols="160" maxlength="1000" placeholder="내용 입력">${boardDTO.qna_content}</textarea>
						</div>
					</div>
					<div class="form-group">
						<br/>
						<div id="boardUpdateBtn">
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="fn_boardUpdate();">
								<span> 게시글 수정</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="location.href='/board/boardList'">
								<span> 게시글 목록</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</form>
			</div>
			<!-- 스크립트에서 사용할 hidden 요소 -->
			<div>
				<input type="hidden" id="qna_bno" value="${boardDTO.qna_bno}"/>
				<input type="hidden" id="userID" value="${member1.userID}"/>
			</div>
			<br/>
			<jsp:include page="../common/footer.jsp" flush="false"/>
		</body>
		
		<style>
		   * {
		      font-family: 'Sunflower','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>
	</html>






