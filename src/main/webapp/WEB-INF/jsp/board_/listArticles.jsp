<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  request.setCharacterEncoding("UTF-8");
%>  

<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<body>
<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="lightgreen">
     <td >글번호</td>
     <td >카테고리</td>
     <td >제목</td>              
     <td >내용</td>
     <td >작성자</td>
     <td >작성일</td>
  </tr>
<c:choose>
  <c:when test="${empty articlesList }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.33</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
 <c:when test="${!empty articlesList}" >
    <c:forEach  var="article" items="${articlesList}" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${articleNum.count}</td>
	<td width="10%">${article.category}</td>
	<td  width="10%">${article.title}</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>

	   <a class='cls1' href="${contextPath}/board/detail.do?num=${article.num}">${article.content}</a>
	  
	  </td>
	  <td  width="10%">${article.id}</td>
	  <td  width="10%"><fmt:formatDate value="${article.writeDate}" /></td> 
	
	</tr>
    </c:forEach>
     </c:when> 
    </c:choose>
</table>
<a  class="cls1"  href="/project/editor.jsp"><p class="cls2">글쓰기</p></a>
<button id="firstPage">처음</button>
<button id="prev">&lt;</button>
<div id="pageButtons" style="display: inline-block">
	<%-- <c:forEach var="page" items="${articlesList}">
		<button data-page="${page}">"${page}"</button>
	</c:forEach> --%>
	<c:forEach var="pageNo" begin="${startPageNo}" end="${endPageNo}">
		<c:choose>
			<c:when test="${currentPageNo == pageNo}">
				<span style="font-size:1.3rem;">${pageNo}</span>
			</c:when>
			<c:otherwise>
				<a href="javascript:movePage(${pageNo})">${pageNo}</a>
			</c:otherwise>
		</c:choose>
		&nbsp;
	</c:forEach>
	
	<c:if test="${currentPageNo != totalPageNo}">
		<a href="javascript:movePage(${currentPageNo+1})"> &gt; </a>
		&nbsp;
		<a href="javascript:movePage(${totalPageNo})"> &gt;&gt; </a>
	</c:if>
</div>
<button id="next">&gt;</button>
<button id="lastPage">마지막</button>
<form id ="content" name="content" action ="searchBoard">
<input type="hidden" name="pageNum" id="pageNo" value="${currentPageNo}"/>
<select name='searchSelect' id="searchSelect">
		<option disabled="disabled">선택</option>
		<option value='카테고리'>카테고리</option>
		<option value='작성자' >작성자</option>
		<option value='내용' >내용</option> 
		<option value='제목' >제목</option> 
	</select>
<input type="text" id ="searchContent" name="searchContent">
<input type="submit" id ="searchButton" value="검색">
</form>


<script type="text/javascript">
function movePage(pageNum) {
	document.querySelector("#pageNo").value = pageNum; 
	location.href = "/project/board/boardShow.do?page=" + pageNum;
}


function jsSearch() {
	document.querySelector("#pageNo").value = 1; 
	searchForm.submit();
}



/*폼이름 */
let content = document.querySelector("#content");
/*버튼이름  */
let searchButton = document.querySelector("#searchButton");

searchButton.addEventListener("click", e => {
   e.preventDefault();
   content.action = "searchBoard";
   content.submit();
   });
pageButtons.querySelectorAll("button").forEach(node => node.addEventListener("click", e => {
	location.href = "/project/board/boardShow.do?page=" + e.target.dataset.page;
}));
</script>
</body>
</html>
