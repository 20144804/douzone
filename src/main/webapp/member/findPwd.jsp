<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>비밀번호 찾기</h1>
		<input type="text" id="name1" name="name1" placeholder="이름"><br />
		<input type="text" id="uid" name="uid"  placeholder="아이디"><br/>
		<input type='text' name="email1" id="email1" placeholder="이메일">
		<label>@</label> <select name='email2' id="email2">
			<option value='naver.com'>naver.com</option>
			<option value='hanmail.com'>hanmail.com</option>
			<option value='daum.com'>daum.com</option>
			<option value='nate.com'>nate.com</option>
			<option value='gamil.com'>gmail.com</option>
			<option value='hotmail.com'>hotmail.com</option>
		</select><br/>
		 <input type="submit" value="비밀번호찾기" id="findPwdButton">
		 
		
<script type="text/javascript">

let insertButton = document.querySelector("#findPwdButton");
insertButton.onclick = () => {
	jsInsert();
}
 

function jsInsert () {
			let param = {
				"uid": uid.value,
				"name1" : name1.value,
				"email1" : email1.value,
				"email2" : email2.value
			};
			
			fetch('/project/findPwd', {
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
				/* if (jsonResult.status == true) {
					alert(jsonResult.message);
				}
				else{
					alert(jsonResult.message);
				} */
			});
			
		}
		


</script>
</body>
</html>