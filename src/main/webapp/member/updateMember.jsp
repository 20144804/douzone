<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="project.servlet.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>

</head>
<body>
	<div id='header_logo'>
		<header>
			<!-- <img src="images/200x200.png" alt="My Image"> -->
		</header>
	</div>

	<h1>회원수정</h1>
	<h2>1 정보입력</h2>


	
		<input type="password" id="pwd" name="pwd"  placeholder="변경할 비밀번호"><br/>
		<input type="text" id="name1" name="name1" placeholder="이름"><br />

		<div class=phone>
			<select name='phone1' id="phone1">
				<option value=''>휴대폰 번호 선택</option>
				<option value='010'>010</option>
				<option value='011'>011</option>
				<option value='0130'>0130</option>
				<option value='016'>016</option>
				<option value='017'>017</option>
				<option value='018'>018</option>
				<option value='019'>019</option>
			</select> <label>-</label> <input type="text" id="phone2" size="4" /> <label>-</label>
			<input type="text" id="phone3" size="4" /><br />
		</div>
		<!-- 
<form name="join">   -->
		<input type='text' name="email1" id="email1" placeholder="이메일">
		<label>@</label> <select name='email2' id="email2">
			<option value=''>선택</option>
			<option value='naver.com'>naver.com</option>
			<option value='hanmail.com'>hanmail.com</option>
			<option value='daum.com'>daum.com</option>
			<option value='nate.com'>nate.com</option>
			<option value='gamil.com'>gmail.com</option>
			<option value='hotmail.com'>hotmail.com</option>
		</select>
		<!-- </form> -->
		<br/>
		 <input type="submit" value=정보수정 id="insertButton">
	

<script type="text/javascript">

let insertButton = document.querySelector("#insertButton");
insertButton.onclick = () => {
	jsInsert();
}
 

function jsInsert () {
			let param = {
				"pwd": pwd.value,
				"name1" : name1.value,
				"phone1" : phone1.value,
				"phone2" : phone2.value,
				"phone3" : phone3.value,
				"email1" : email1.value,
				"email2" : email2.value
			};
			
			fetch('/project/update', {
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
					location.href = "/project/main.jsp";
				}
			});
		}
		


</script>
</body>
</html>