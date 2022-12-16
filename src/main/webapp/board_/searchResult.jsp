<%@page import="board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="	${pageContext.request.contextPath}"  />
<%-- <%@ page import= board.BoardVO %>   --%>

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
  <c:when test="${empty voList}" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">찾는 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${!empty voList}" >
    <c:forEach  var="vo" items="${voList}" varStatus="voNum" >
     <tr align="center">
	<td width="5%">${voNum.count}</td>
	<td width="10%">${vo.category}</td>
	<td  width="10%">${vo.title}</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>

	   <a class='cls1' href="${contextPath}/detail?num=${vo.num}">${vo.content}</a>
	  
	  </td>
	  <td  width="10%">${vo.id}</td>
	  <td  width="10%"><fmt:formatDate value="${vo.writeDate}" /></td> 
	
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<a  class="cls1"  href="/project/editor.jsp"><p class="cls2">글쓰기</p></a>

<form action="/project/searchBoard">
<select name='searchSelect' id="searchSelect">
		<option disabled="disabled">선택</option>
		<option value='카테고리'>카테고리</option>
		<option value='작성자' >작성자</option>
		<option value='내용' >내용</option> 
	</select>
<input type="text" id ="searchContent" name="searchContent">
<input type="submit" id ="searchButton" value="검색">
</form>
</body>
</html>
