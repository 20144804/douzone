<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> --%>

<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
</style>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
	<form id = "searchForm" name="searchForm">
		<input type="text" id="searchUser" name="searchUser"> <input type="submit"
			id = "searchButton" name ="searchButton" value="회원검색"><br>
	</form>
	<table border='1'>
		<thead>
			<tr>
			<th>순번</th>
				<th>아이디</th>
				<th>비번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>삭제</th>
				<th>정지</th>
				<th>활성화</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list_" items="${list}" varStatus="listNum">
				<tr align="center">
					<td width="5%">${listNum.count}</td>
					<td width="10%">${list_.uid}</td>
					<td width="10%">${list_.pwd}</td>
					<td width="10%">${list_.name}</td>
					<td width="10%">${list_.phone}</td>
					<td width="10%">${list_.email}</td>
					<td><a
						href='/project/member/deleteAdmin.do?userid=${list_.uid}'>삭제</a></td>
					<td><a
						href='/project/member/stop.do?userid=${list_.uid}'>정지</a></td>
					<td><a
						href='/project/member/act.do?userid=${list_.uid}'>활성화</a></td>

				</tr>
			</c:forEach>


<script type="text/javascript">
/*폼이름 */
let content = document.querySelector("#searchForm");
/*버튼이름  */
let searchButton = document.querySelector("#searchButton");

searchButton.addEventListener("click", e => {
   e.preventDefault();
   content.action = "/project/member/adminSearch.do";
   content.submit();
   });

</script>
</body>
</html>
