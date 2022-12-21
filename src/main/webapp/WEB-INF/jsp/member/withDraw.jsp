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
	
	<form id="frmLogin" action="/project/member/delete.do" encType="UTF-8">
아이디  :<input type="text" id="userid" name="userid"   placeholder="아이디 입력"><br>
   <!--비밀번호:<input type="password" name="pwd" placeholder="비밀번호 입력"><br> -->
       <input type="submit" value="회원탈퇴">
  </form>
	</main>
</div>

<script type="text/javascript">
function jsInsert (e) {
	e.preventDefault();
			let param = {
				"userid": userid.value,
			};
			
			fetch('/project/member/delete.do', {
				//option
				method : 'POST',
				headers: {
				    'Content-Type': 'application/json;charset=utf-8'
				},
				body: JSON.stringify(param)//{"uid":"user10","pwd":"123", "name":"홍길동"}			
			})
			.then(response => response.json())
			.then(jsonResult => {	
				//처리후 메시지 출력
				alert(jsonResult.message);
				if (jsonResult.status == true) {
					//성공시 이동할 페이지로 이동한다  
					location.href = jsonResult.url;
				}
			}); 
		}
frmLogin.addEventListener("submit", jsInsert);
</script>
</body>
</html>

