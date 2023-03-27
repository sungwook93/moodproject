<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>

<link href="${contextPath}/resources/css/topmenu.css" rel="stylesheet" type="text/css">	
			
<!-- 로그인 확인용 -->
<script src="${contextPath}/resources/js/isLogOn.js"></script>
<script src="${contextPath}/resources/js/login.js"></script>

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-0NRHYLJTPK"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-0NRHYLJTPK');
</script>


<div>
	<input type="hidden" id="isLogOnT" value="${isLogOn}"/>
	<input type="hidden" id="userIDT" value="${member1.userID}"/>
</div>

<div class="navbar" id="myNav">
	<!-- 로고 네브바  -->
	<div class="container-fluid" id="nav1">
		<br/>
		<p style = "text-align: center;">
		<a href="/main.do"><img src="${contextPath}../resources/images/logo1.png" style = "width: 15%; height: 15%;"/></a>
		</p>
	</div>	
	<div id="nav2">
		<li><a style="width:160px;" id="loginbtn">login</a><a style="width:160px;"href="${contextPath}/member/memberForm.do" >join</a></li>
	</div>
	<br/>
	
	<div id="nav3">
		<ul class="menu">
      	<li>
        <a href="#">Mood</a>
        <ul class="submenu">
           <li><a href="#">Concept</a></li>
        </ul>
      </li>
      <li>
        <a href="#">Gift</a>
        <ul class="submenu">
          <li><a href="/product/productList?product_color=white&product_type=bed,bath,living&page=1">white</a></li>
          <li><a href="/product/productList?product_color=black&product_type=bed,bath,living&page=1">black</a></li>
          <li><a href="/product/productList?product_color=gray&product_type=bed,bath,living&page=1">gray</a></li>
        </ul>
      </li>
      <li>
        <a href="#">Board</a>
        <ul class="submenu">
          <li><a href="#">Q & A</a></li>
          <li><a href="#">Review</a></li>
        </ul>
      </li>
      <li><a href="#"><span class="glyphicon glyphicon-search" style = "width: 160px;"></span></a></li>
       <li><a href="#"><span class="glyphicon glyphicon-shopping-cart" style = "width: 160px;"></span></a></li>
    </ul>
	</div>
</div>

    <!-- -->
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>Login</h2>
            </div>
            <div class="close-area">X</div>
            <div style="padding-top:20px;"><h4>ID</h4></div>
            <div class="content"><input type = "text" class = "form-control" id = "loginUserID" name ="userID" maxlength="20" placeholder="아이디"/></div>
            <div><h4>PASSWORD</h4></div>
            <div class="content"><input type = "password" class = "form-control" id = "pwd" name ="pwd" maxlength="20" placeholder="비밀번호"/></div>
            <div class="content" style="padding-top:80px;">
            	<button type = "reset" class = "btn btn-light">Re-enter</button>
				<button type = "submit" class = "btn btn-light" id = "submit">Login</button>
				<button type = "button" class = "btn btn-light" onclick="location.href='/main.do'">Join</button>
            </div>
        </div>
    </div>
