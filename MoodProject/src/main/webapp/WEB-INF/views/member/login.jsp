<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<% request.setCharacterEncoding("UTF-8"); %>

	 <!-- 로그인 여부 체크 -->
	  <c:choose>
		<c:when test = "${result == 'loginIdEmpty' }">
			<script>
			window.onload = function(){
				alert("\n아이디를 입력하셔야 합니다.");
			}
			</script>
		</c:when>
		<c:when test = "${result == 'loginFailed' }">
			<script>
			window.onload = function(){
				alert("\n아이디를 잘못입력하셨습니다. \n\n 다시 로그인을 해주십시오.");
			}
			</script>
		</c:when>
		<c:when test = "${result == 'PasswordFailed' }">
			<script>
				window.onload = function(){
				alert("\n비밀번호를 잘못입력하셨습니다. \n\n 다시 로그인을 해주십시오.");
				}
			</script>
		</c:when>
	  </c:choose>
	  <!-- 로그인 여부 체크 -->
