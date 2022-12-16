<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

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
    
     <td >방이름</td>              

  </tr>
<c:choose>
  <c:when test="${empty roomList }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${!empty roomList}" >
    <c:forEach  var="roomList" items="${roomList}"  >
     <tr align="center">
	
	<td align='left'  width="35%">
	  	<span style="padding-right:30px"></span>
	   <a class='cls1' href="${contextPath}/enterRoom?roomName=${roomList}">${roomList}</a>
	  </td>

	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>

<form id ="content" name="content" action ="makeRoom">

<input type="text" id ="createRoom" name="createRoom" placeholder="방이름">
<input type="submit" id ="createRoomButton" value="방생성">
</form>

</body>
</html>
