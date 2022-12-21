<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp" %>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
			</h1>
		</div>
	</div>	
	<div class="container">
		<div class="login" align="center">
		<div class="col-md-4 col-md-offset-4">
			
			<%
				String error = request.getParameter("error");
				if (error != null) {
					out.println("<div class='alert alert-danger'>");
					out.println("아이디와 비밀번호를 확인해 주세요");
					out.println("</div>");
				}
			%>
				<c:choose>
						<c:when test="${not empty sessionId}">	
						</c:when>
				<c:when test="${empty sessionId}">
				<h3 class="form-signin-heading">로그인</h3>
			<form class="form-signin" id ="loginForm" name ="loginForm" action="/project/member/login.do" method="post">

				<div class="form-group">
					<label for="inputUserName" class="sr-only">User Name</label> <input
						type="text" class="form-control" placeholder="ID" id= 'id'name='id'
						required autofocus>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">Password</label> <input
						type="password" class="form-control" placeholder="Password"
						name='password' id='password' required>
				</div>
				<button class="btn btn btn-lg btn-success btn-block" id = "insertButton" name="insertButton" type="submit">로그인</button>

			</form>
			</c:when>
				</c:choose>
		</div>
	</div>

		</div>
		<hr>
	
	<%@ include file="footer.jsp" %>
	<script type="text/javascript">
/*폼이름 */
let content = document.querySelector("#content");
/*버튼이름  */
/*
let searchButton = document.querySelector("#insertButton");
searchButton.addEventListener("click", e => {
	e.preventDefault();
   //content.action = "/project/login";
   content.submit();
});
*/
</script>
	
</body>
</html>