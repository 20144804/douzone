<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <script type="text/javascript">
     function backToList(){
		 location.replace("/project/boardShow");
	  }

   </script>
</head>
<body>
  <form name="frmBoard" method="post" action="<c:url value='/updateBoardForm'/>"  enctype="utf-8">
  <table  border="0" align="center" >
  <tr>
   <td width="150" align="center" bgcolor="#FF9933">
      글번호
   </td>
   <td >
    <input type="text"  value="${board.num}"  disabled />
    <input type="hidden" name="num" value="${board.num}"  />
   </td>
  </tr>
  <tr>
   <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td >
    <input type="text" value="${board.id }" name="id"  disabled />
   </td>
  </tr>
  <tr>
   <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type="text" value="${board.title }"  name="title"  id="i_title" disabled />
   </td>   
  </tr>
  <tr>
   <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    ${board.content}
   </td>  
  </tr>
 

  <tr>
	   <td width="20%" align="center" bgcolor="#FF9933">
	      등록일자
	   </td>
	   <td>
	    <input type=text value="<fmt:formatDate value='${board.writeDate}' />" disabled />
	   </td>   
  </tr>
 
    
  <tr  id="tr_btn"    >
   <td colspan=2 align="center">
   
	  <button type="submit" >수정하기</button>
	  <input type="submit" value="삭제" formaction="/project/deleteBoard">
	  <input type=button value="리스트로 돌아가기"  onClick="backToList()">
   </td>
  </tr>
 </table>
 </form>
 
</body>
</html>
