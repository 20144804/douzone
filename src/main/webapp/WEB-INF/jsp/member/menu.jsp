<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//로그인시 저장한 sessionId 를 불러온다. 이것의 값이 없으면 비회원으로 처리
	String sessionId = (String)session.getAttribute("sessionId");
	int admin=(Integer)session.getAttribute("admin");
%>


	<nav class="navbar navbar-expand  navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/main.jsp"/>">Home</a>
			</div>
			<div>
				<ul class="navbar-nav mr-auto">
					<c:choose>
						<c:when test="${empty sessionId}">	
							<%-- <li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/addMember.jsp"/>">회원가입</a>
							</li> --%>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/joinForm.do"/>">회원가입</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/findIdForm.do"/>">아이디찾기</a>
							</li>	
							
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/findPwdForm.do"/>">비밀번호찾기</a>
							</li>
							
											
						</c:when>
						<c:when test="${not empty sessionId && admin==1}">
							<li style="padding-top: 7px; color: white;">
								[<%=sessionId %>님]
							</li>
							<li class="nav-item">	
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/list.do"/>">회원관리모드</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/board/boardShow.do?searchContent="/>">게시판</a>
							</li>			
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/logout.do"/>">로그아웃</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/roomList"/>">채팅</a>
							</li>	
						</c:when>
						<c:otherwise>
							<li style="padding-top: 7px; color: white;">
								[<%=sessionId %>님]
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/logout.do"/>">로그아웃</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/show.do"/>">상세정보</a>
							</li>								
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/member/updateMemberForm.do"/>">회원수정</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/board/boardShow.do?searchContent="/>">게시판</a>
							</li>			
							<li class="nav-item">
								<a class="nav-link" href="<c:url value="/roomList"/>">채팅</a>
							</li>
							<li class="nav-item">
								 <a class="nav-link" href="<c:url value="/member/withDraw.do"/>">회원탈퇴</a>
							</li>	
															
						</c:otherwise>					
					</c:choose>
				
				
				
				</ul>
			</div>
		</div>
	</nav>