<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>아아디 찾기</h1>
		<input type="text" id="name1" name="name1" placeholder="이름"><br />
		<input type="password" id="pwd" name="pwd"  placeholder="비밀번호"><br/>
		<input type='text' name="email1" id="email1" placeholder="이메일">
		<label>@</label> <select name='email2' id="email2">
			<option value='naver.com'>naver.com</option>
			<option value='hanmail.com'>hanmail.com</option>
			<option value='daum.com'>daum.com</option>
			<option value='nate.com'>nate.com</option>
			<option value='gamil.com'>gmail.com</option>
			<option value='hotmail.com'>hotmail.com</option>
		</select><br/>
		 <input type="submit" value=아이디찾기 id="findIdButton">
		 
		
<script type="text/javascript">

let insertButton = document.querySelector("#findIdButton");
insertButton.onclick = () => {
	jsInsert();
}
 

function jsInsert () {
			let param = {
				"pwd": pwd.value,
				"name1" : name1.value,
				"email1" : email1.value,
				"email2" : email2.value
			};
			
			fetch('/project/member/findId.do', {
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
				location.href = jsonResult.url;
			});
			
		}
		


</script>
</body>
</html>