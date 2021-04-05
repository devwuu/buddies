<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet" href="/css_file/main_home_css.css">
<link rel="stylesheet" href="/css_file/board_seoul_css.css">
<link rel="stylesheet" href="/css_file/contentView_css.css">

<script src="/js_file/main_home_js.js"></script>

<!-- 폰트 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">

<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<style>
	
	a#boardSeoul{
		color: #ffffff !important;
	}
	
	div#area_drop{
		display: block !important;
	}

</style>

<title>동물병원 통합 아카이빙 시스템 : 서울 - OO 동물병원</title>
</head>

<!-- 온로드 로그인상태 체크 함수 -->
<body>
	
<!-- 로그인 및 회원가입창 -->
<%@include file="../member/login_join_modal.jsp" %>				


<!-- 헤더영역 -->
<%@include file="../header_pj.jsp" %>	


<!-- 미니메뉴 영역 -->
<p id="welcom">
<c:if test="${sess_nickname != null}">
<b>${sess_nickname }</b>님 환영합니다.
</c:if>
</p>
<%@include file="../navbar_mini.jsp" %>

<!-- 메인내용(왼쪽 오른쪽 컬럼)		 -->
	 <div id="main_content">
		

	<!-- 			왼쪽 메뉴 영역	 -->
		<%@include file="../navbar_left.jsp" %>
	
	<!-- 메인 오른쪽 컬럼		 -->
		<div id="main_right_column">
			<c:set value="${review }" var="r"/>
			<form method="POST" enctype="multipart/form-data" action="/board/boardModProc">
				<input type="hidden" value="${r.reviewNo }" name="reviewNo">
				<input type="hidden" value="${pageNum }" name="pageNum">
				<p id="title_cont"><b>Title</b> : <input id="title_cont" type="text" name="title" value="${r.title }" maxlength="13" required></p>
				<p id="date_cont"><b>Date</b> : ${r.wdate }</p>
				<p id="email_cont"><b>Email</b> : ${r.writer }</p>
				<textarea id="cont_input" name="content" required>${r.content }</textarea><br>
				
				<div id="file_area">
					<c:if test="${file.fileName != null }">
						<b>첨부파일명:</b> ${file.fileName }<br>
						<b>사이즈:</b> ${file.fileSize }<br>
						<c:if test="${fn:contains(file.fileType, 'image') }">
							<img src="/upload/${file.fileNameSave }">	
						</c:if>
						<br>
						<input id="file_del" type="button" value="첨부파일 삭제" onclick="fileDelReq(${r.reviewNo })"><br>
					</c:if>
					<c:if test="${file.fileName == null }">
						<input type="file" name="fileAttach"><br>
					</c:if>
				</div>
				<input id="modi" type="submit" value="수정">
			</form>
		</div>
		
	 </div>	
	
<%@include file="../footer_pj.jsp" %>

	
</body>
</html>