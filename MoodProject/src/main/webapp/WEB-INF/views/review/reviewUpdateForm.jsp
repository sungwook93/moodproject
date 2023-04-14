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
			<h2 style = "text-align: center; font-family: 'Sunflower', sans-serif;">Review UpdateForm</h2>
			<br/>
			<br/>
				
				<div class="container">
				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="review_subject" name="review_subject"  value="${reviewDTO.review_subject}"/>
						</div>
						<label class="col-sm-1 control-label">리뷰 별점</label>
						<div class="col-sm-2">
							<select id="review_star" class="form-control">
								<option disabled selected>리뷰 별점</option>
								<option value="1" <c:if test="${reviewDTO.review_star == 1}">selected</c:if>>★</option>
								<option value="2" <c:if test="${reviewDTO.review_star == 2}">selected</c:if>>★★</option>
								<option value="3" <c:if test="${reviewDTO.review_star == 3}">selected</c:if>>★★★</option>
								<option value="4" <c:if test="${reviewDTO.review_star == 4}">selected</c:if>>★★★★</option>
								<option value="5" <c:if test="${reviewDTO.review_star == 5}">selected</c:if>>★★★★★</option>
							</select>
						</div>
					</div>
					<div class="form-group">	
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-2">
						<c:choose>
								<c:when test="${member1.grade == 7}">
									<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
								</c:otherwise>
							</c:choose>
						</div>
						<!-- 상품 관련 -->
						<label class="col-sm-1 control-label" for="productKindU">상품 목록</label>
							<div class="col-sm-3">	
								<select class="form-control" id="product_type" name="product_type" onchange="productKindChange(this)">
									<option disabled selected>상품 Type</option>
									<option value="living" <c:if test="${reviewDTO.product_type == 'living'}">selected</c:if>>Living</option>
									<option value="bed" <c:if test="${reviewDTO.product_type == 'bed'}">selected</c:if>>Bed</option>
									<option value="bath" <c:if test="${reviewDTO.product_type == 'bath'}">selected</c:if>>Bath</option>
								</select>
						</div>
						<label class="col-sm-1 control-label"  for="productKindD">상품 이름</label>
							<div class="col-sm-3">	
								<select class="form-control" id="product_name" name="product_name">
									<option <c:if test="${reviewDTO.product_name == reviewDTO.product_name}">selected</c:if>>${reviewDTO.product_name}</option>
								</select>
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">리뷰 사진</label>	
						<div class="col-sm-2">
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
							</tr>
						</table>
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="review_content" name="review_content" rows="10" cols="160">${reviewDTO.review_content}</textarea>
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
				<div class="form-group">
						<br/>
						<div id="boardUpdateBtn">
							<button type="button" class="btn btn-light" onclick="fn_reviewUpdate();">
								<span> 게시글 수정</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-light" onclick="location.href='/review/reviewList?page=1'">
								<span> 게시글 목록</span>
							</button>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</form>
			</div>
			<div>
				<input type="hidden" id="review_bno" value="${reviewDTO.review_bno}">
				<input type="hidden" id="userID" value="${member1.userID}"/>
			</div>
				<hr/>
				
			
			<jsp:include page="../common/footer.jsp" flush="false"/>
		
		</body>

		<style>
		   * {
		      font-family: 'Sunflower','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체';
		   }   
		</style>	
		<script>
		function maxLengthCheck(object){
		    if (object.value.length > object.maxLength){
		        object.value = object.value.slice(0, object.maxLength);
		    }    
		}
		</script>
	</html>






