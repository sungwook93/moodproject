<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>리뷰 작성페이지 </title>
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
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<hr/>
			<h2 style = "text-align: center; font-family: Sunflower;">Review RegisterForm</h2>
			<br/>
			<br/>
			
			<div class="container">
				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="review_subject" name="review_subject" maxlength="200" placeholder="제목 입력"/>
						</div>
						<label class="col-sm-1 control-label">리뷰 별점</label>
						<div class="col-sm-2">
							<select id="review_star" class="form-control">
								<option disabled selected>리뷰 별점</option>
								<option value="1">★</option>
								<option value="2">★★</option>
								<option value="3">★★★</option>
								<option value="4">★★★★</option>
								<option value="5">★★★★★</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
						</div>
						<!-- 상품 관련 -->
						<label class="col-sm-1 control-label">상품 목록</label>
						<div class="col-sm-3">	
							<select class="form-control" id="product_type" onchange="fn_typename()">
								<option disabled selected>선택해주세요.</option>
								<option value="living" <c:if test="${produt_type == 'living'}">selected</c:if>>Living</option>
								<option value="bed" <c:if test="${produt_type == 'bed'}">selected</c:if>>Bed</option>
								<option value="bath" <c:if test="${produt_type == 'bath'}">selected</c:if>>Bath</option>
							</select>
						</div>

						<label class="col-sm-1 control-label">상품 이름</label>
						<div class="col-sm-3">	
							<select class="form-control" id="product_name">
								<option disabled selected>선택해주세요.</option>
								<c:forEach items="${productList}" var="product">
								<option id="product_name">${product.product_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="review_content" name="review_content" rows="10" cols="160"  placeholder="내용 입력"></textarea>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-1 control-label">리뷰 사진</label>
						
						<form class = "form-horizontal" action = "${contextPath }/util/upload/uploadForm" method = "post" enctype = "multipart/form-data" target = "iframe1">
							<!-- name = "file"의 변수명과 컨트롤러의 MultipartFile file과 일치해야 한다. -->
							<div class = "form-group">
								<div class = "col-sm-6">
									<input type = "file" id="file" class = "btn btn-default" name = "file" style = "width: 600px;" multiple/>
								</div>
								<div class = "col-sm-2">
									<input type = "submit" class = "btn btn-default" value = "파일 올리기"/>
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
					<br/>
					<div class="form-group">
						<div id="registerFormBtnDiv">
							<button type="button" class="btn btn-light" onclick="location.href='/review/reviewList'" style = "width: 80px;"><strong>Back</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset"  class="btn btn-light"><strong>다시 입력</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="fn_reviewRegister();"><strong>리뷰 등록</strong></button>
						</div>
					</div>
				</form>
			</div>
			
			 <!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
			
</body>
		<style>
		   * {
		      font-family: 'Arial','Sunflower', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>

</html>