<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
</head>
<body>
<div class="navbar-header">
	<a class="navbar-brand" href="<c:url value="/member/loginForm.do"/>">Home</a>
</div>

아이디 : ${memberBean.uid}<br/> 
비밀번호 : ${memberBean.pwd}<br/> 
이름 : ${memberBean.name}<br/> 
이메일 : ${memberBean.email}<br/>
전화번호 : ${memberBean.phone}<br/>
</body>
</html>