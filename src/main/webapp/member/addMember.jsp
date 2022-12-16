<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="project.servlet.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function joinform_check() {
	  //변수에 담아주기
	  var uid = document.getElementById("uid");
	  var pwd = document.getElementById("pwd");
	  var pwd2 = document.getElementById("pwd2");
	  var name1 = document.getElementById("name1");
	  var phone1 =document.getElementById("phone1");
	  var phone2 =document.getElementById("phone2");
	  var phone3 =document.getElementById("phone3");

	  var email1 = document.getElementById("email1");
	  var row1 = document.getElementById("row1");
	  var row2 = document.getElementById("row2");
	  var row3 = document.getElementById("row3");
	  
	  if (uid.value == "") { //해당 입력값이 없을 경우 같은말: if(!uid.value)
	    alert("아이디를 입력하세요.");
	    uid.focus(); //focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
	    return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
	  };

	  if (pwd.value == "") {
	    alert("비밀번호를 입력하세요.");
	    pwd.focus();
	    return false;
	  };

	  //비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
	  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

	  if (!pwdCheck.test(pwd.value)) {
	    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
	    pwd.focus();
	    return false;
	  };

	  if (pwd2.value !== pwd.value) {
	    alert("비밀번호가 일치하지 않습니다..");
	    pwd2.focus();
	    return false;
	  };

	  if (name1.value == "") {
	    alert("이름을 입력하세요.");
	    name1.focus();
	    return false;
	  };

/* 
	  var reg = /[0-9]+/g; //숫자만 입력하는 정규식

	  if (reg.test(phone1.value)) {
	    alert("전화번호 숫자만 입력할 수 있습니다.1");
	    phone1.focus();
	    return false;
	  } 
	  
	  if (!reg.test(phone2.value)) {
		    alert("전화번호는 숫자만 입력할 수 있습니다.2");
		    phone2.focus();
		    return false;
		  }
	
	  if (!reg.test(phone3.value)) {
		    alert("전화번호는 숫자만 입력할 수 있습니다.3");
		    phone3.focus();
		    return false;
		  } */
	  
		  if (phone1.value == "") {
			    alert("전화번호 숫자만 입력할 수 있습니다.1");
			    phone1.focus();
			    return false;
			  } 
			  
			  if (phone2.value == "") {
				    alert("전화번호는 숫자만 입력할 수 있습니다.2");
				    phone2.focus();
				    return false;
				  }
			
			  if (phone3.value == "") {
				    alert("전화번호는 숫자만 입력할 수 있습니다.3");
				    phone3.focus();
				    return false;
				  } 	  
	 
		  
	  if (email1.value == "") {
	    alert("이메일 주소를 입력하세요.");
	    email1.focus();
	    return false;
	  }

	  if (!row1.checked) { //체크박스 미체크시
	    alert("약관1 동의를 체크하세요.");
	    row1.focus();
	    return false;
	  }
	  
	  if (!row2.checked) { //체크박스 미체크시
		    alert("약관2 동의를 체크하세요.");
		    row2.focus();
		    return false;
		  }
	  
	  if (!row3.checked) { //체크박스 미체크시
		    alert("약관3 동의를 체크하세요.");
		    row3.focus();
		    return false;
		  }
	  
	  //입력 값 전송
	  document.join.submit(); //유효성 검사의 포인트  
	  return true;
	}


</script>
<title>회원목록</title>

</head>
<body>
<div id='header_logo'>
<header>
  <!-- <img src="images/200x200.png" alt="My Image"> -->
</header>
</div>
<form name="join">  
<h1>회원가입</h1>
<h2>1 정보입력</h2>      
<input type="text" id="uid" name="uid" placeholder="아이디 6~11자" required> <input type="button" value="중복확인" id="dupUidCheckButton"><br/>
<div id="uid_valid_msg"></div>

<input type="password" id="pwd" name="pwd"  placeholder="비밀번호" required><br/>

<input type="password" id="pwd2" name="pwd2"  placeholder="비밀번호 확인" required><br/>

<input type="text" id="name1" name="name1" placeholder="이름" required><br/>

<div class =phone>
<select name='phone1' id="phone1">
	<option value='none'>휴대폰 번호 선택</option>
	<option value='010'>010</option>
	<option value='011'>011</option>
	<option value='0130'>0130</option>
	<option value='016'>016</option>
	<option value='017'>017</option>
	<option value='018'>018</option>
	<option value='019'>019</option>
</select>
<label>-</label>
<input type="number" id="phone2" size="4"  />
<label>-</label>
<input type="number" id="phone3" size="4" /><br/>
</div>


<input type='text' name="email1" id="email1" placeholder="이메일">
<label>@</label>

<select name='email2' id="email2">
	<option value='naver.com' selected>naver.com</option>
	<option value='hanmail.com'>hanmail.com</option>
	<option value='daum.com'>daum.com</option>
	<option value='nate.com'>nate.com</option>
	<option value='gamil.com'>gmail.com</option>
	<option value='hotmail.com'>hotmail.com</option>
</select>

<h2>
<div scope="row">2 약관동의</div>
</h2>
<div> 이용약관
<input type="checkbox"  name="row1" id="row1" value="yes">
<span><label for="row1">동의</label></span>
</div>

<div> 전자금융거래 이용약관
<input type="checkbox"  name="row2" id="row2" value="yes">
<span><label for="row2">동의</label></span>
</div>

<div> 개인정보 수집 및 이용
<input type="checkbox"  name="row3" id="row3" value="yes">
<span><label for="row3">동의</label></span>
</div>

<div> 개인정보 제3자(판매자) 제공(선택)
<input type="checkbox"  name="row4" id="row4" value="yes">
<span><label for="row4">동의</label></span>
</div>

<div> 개인정보 수집 및 이용(선택)
<input type="checkbox"  name="row5" id="row5" value="yes">
<span><label for="row1">동의</label></span>
</div>
</form>


<input type="button" value="회원가입" id="insertButton">

<script type="text/javascript">
let dupUidCheckButton = document.querySelector("#dupUidCheckButton");
dupUidCheckButton.onclick = () => {
	dupUidCheck();
}

let uid = document.querySelector("#uid");
uid.onblur = () => {
	dupUidCheck();
}

let insertButton = document.querySelector("#insertButton");
insertButton.onclick = () => {
	if(joinform_check())
		jsInsert();
}


async function dupUidCheck() {

	let response = await fetch('/project/join/dupUidCheck?uid=' + uid.value);
	
	let jsonResult = await response.json();
	if (jsonResult.status == false) {
		alert("[" + uid.value + "]" + jsonResult.message);
	}
	let uid_valid_msg = document.querySelector("#uid_valid_msg");
	uid_valid_msg.innerHTML =  "[" + uid.value + "]" + jsonResult.message;
	
}

function jsInsert () {
	//아이디 중복 확인
	fetch('/project/join/dupUidCheck?uid=' + uid.value)
	.then(response => response.json())
	.then(jsonResult => {
		let uid_valid_msg = document.querySelector("#uid_valid_msg");
		uid_valid_msg.innerHTML =  "[" + uid.value + "]" + jsonResult.message;
		if (jsonResult.status == false) {
			alert("[" + uid.value + "]" + jsonResult.message);
		} else {
			
			//uid=user10&pwd=123&name=홍길동
		
			let param = {
				"uid" : uid.value,
				"pwd" : pwd.value,
				"name1" : name1.value,
				"phone1" : phone1.value,
				"phone2" : phone2.value,
				"phone3" : phone3.value,
				"email1" : email1.value,
				"email2" : email2.value
			};
			
			fetch('/project/join/insert', {
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
			/* 	alert(jsonResult.message);
				alert(jsonResult.status);
				alert(jsonResult.url); */
				if (jsonResult.status == true) {
					//성공시 이동할 페이지로 이동한다  
					location.href = jsonResult.url;
				}
			});
		}
		
	});
}

</script>
</body>
</html>