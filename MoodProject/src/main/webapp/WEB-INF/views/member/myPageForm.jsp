<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

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
			<link href="${contextPath}/resources/css/member.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/member.js"></script>	

</head>
<body>
	<jsp:include page ="../common/topMenu.jsp"></jsp:include>
	
	<h1 style="text-align:center;">정보 수정 탈퇴</h1>
	<div class="container">
		<form class="form-horizontal" method="post" id="memInfo" name="memInfo" action="${contextPath}/member/memberUpdate.do">
			<div class="form-group">
				<button type="button" id="updatepage"><h4>정보수정하기</h4></button>
			</div>
			<div class="form-group">
				
				<label class="col-sm-5 control-label">아이디</label>
				<div class="col-sm-2">
				<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value="${member1.userID}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				비밀번호
				</label>
				<div class="col-sm-2">
				<input type="text" class="form-control" id="pwd" name="pwd" maxlength="20" value="${member1.pwd}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				이름
				</label>
				<div class="col-sm-2">	
				<input type="text" class="form-control" id="name" name="name" maxlength="20" value="${member1.name}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				우편번호
				</label>
				<div class="col-sm-2">
				<input type="text" class="form-control" id="postnum" name="postnum" maxlength="20" value="${member1.postnum}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				주소
				</label>
				<div class="col-sm-6">
				<input type="text" class="form-control" id="address1" name="address1" maxlength="20" value="${member1.address1}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				상세주소
				</label>
				<div class="col-sm-6">
				<input type="text" class="form-control" id="address2" name="address2" maxlength="20" value="${member1.address2}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				전화번호
				</label>
				<div class="col-sm-3">
				<input type="text" class="form-control" id="phone" name="phone" maxlength="20" value="${member1.phone}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">성별</label>
				<div class="col-sm-2">
				<input type="text" class="form-control" id="gender" name="gender" maxlength="20" value="${member1.gender}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				생일
				</label>
				<div class="col-sm-3">
				<input type="text" class="form-control" id="age" name="age" maxlength="20" value="${member1.age}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">
				이메일
				</label>
				<div class="col-sm-3">
				<input type="text" class="form-control" id="email" name="email" maxlength="20" value="${member1.email}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">가입일자</label>
				<div class="col-sm-3">
				<input type="text" class="form-control" id="joinDate" name="joinDate" maxlength="20" value="${member1.joinDate}" readonly/>
				</div>
			</div>
			<div class="form-group" id="mypagebuttonbox">
				
				<button type="submit" id="updateinfo" style="display:none;">수정하기</button>
				<button type="button">회원탈퇴</button>
			</div>
		</form>
	</div>		
	
	
	<jsp:include page ="../common/footer.jsp"></jsp:include>
</body>
</html>