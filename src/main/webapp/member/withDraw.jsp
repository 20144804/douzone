<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>

<div class = container>
<header>
<div class="navbar-header">
	<a class="navbar-brand" href="<c:url value="/main.jsp"/>">Home</a>
</div>
</header>
	<main>
	<h1>회원 탈퇴</h1>
	
	<form name="frmLogin" action="/project/delete" encType="UTF-8">
아이디  :<input type="text" id="userid" name="userid"   placeholder="아이디 입력"><br>
   <!--비밀번호:<input type="password" name="pwd" placeholder="비밀번호 입력"><br> -->
       <input type="submit" value="회원탈퇴">
  </form>
	</main>
</div>
</body>
</html>

