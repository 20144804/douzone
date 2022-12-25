<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>


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
<title>유저 관리창</title>
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
				<th>사용/미사용</th>
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
					<td>
						<a class="adminDelete" data-uid="${list_.uid}" href='#'>삭제</a>
					</td>
				    <td>
				    	<a href="#" class="allow" data-uid="${list_.uid}" data-useyn="${list_.allow}" >
				    		<span id="allow_${list_.uid}">${list_.allow == 'y' ? '사용' : '미사용'}</span>
				    	</a>
			    	</td>
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



$(".adminDelete").on("click", e=>{
	let aLink=e.target;
	e.preventDefault();
	let uid = aLink.getAttribute("data-uid");
    
    let param = {
    		uid : uid
    };
	
	if (!confirm("정말 삭제 하시겠습니까?"))
		return;
		
	$.ajax({
		type:"post",
		async:true,
		 url :"<c:url value='/member/deleteAdmin.do'/>", 
	
		data: JSON.stringify(param),
		dataType:"JSON",
		contentType: "application/json;charset=utf-8",
		success : (jsonResult, textStatus) => {
			alert(jsonResult.message);
			if (jsonResult.status == true) {
				searchForm.submit();    	
			}
		}
	});
	
});




$(".allow").on("click", e=>{
	let aLink=e.target.parentNode;
	e.preventDefault();
	console.log("hi");
	let uid = aLink.getAttribute("data-uid");
    let useYn = aLink.getAttribute("data-useyn");
    
    let param = {
    		uid : uid,
    	    useYn : useYn
    };
	
	if (!confirm((useYn == 'y' ? '미사용' : '사용') +  "으로 변경하시겠습니까?"))
		return;
		
	$.ajax({
		type:"post",
		async:true,
		 url :"<c:url value='/member/adminShow.do'/>", 
	
		data: JSON.stringify(param),
		dataType:"JSON",
		contentType: "application/json;charset=utf-8",
		success : (jsonResult, textStatus) => {
			alert(jsonResult.message);
			if (jsonResult.status == true) {
				searchForm.submit();    			
			}
		}
	});
	
});


	

</script>
</body>
</html>
