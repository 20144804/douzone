<%-- <!-- utf-8설정 및 페이지 정보 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Date클래스 불러오기 -->  
<%@ page import="java.util.Date" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<%!String greeting = "환영합니다.";%>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<%=greeting%>
			</h1>
		</div>
	</div>	
	<div class="container">
		<div class="text-center">
		
		</div>
		<hr>
	</div>	
	<%@ include file="footer.jsp" %>
</body>
</html> --%>