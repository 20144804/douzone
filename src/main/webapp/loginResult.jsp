<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3"></h1>
		</div>
	</div>
	<div class="container">
		<div class="login" align="center">
			<div class="col-md-4 col-md-offset-4">

				<c:choose>
					<c:when test="${not empty sessionId}">
						<script type="text/javascript">
							alert("환영합니다.");
						</script>
					</c:when>
					<c:when test="${empty sessionId}">
						<script type="text/javascript">
							location.href='/project/main.jsp';
							alert("로그인실패");
						</script>
					</c:when>
				</c:choose>

			</div>
		</div>

	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>