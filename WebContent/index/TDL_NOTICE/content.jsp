<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
 <%
	String TM_id=(String)session.getAttribute("TM_id");
%>

<jsp:include page="../main/head.jsp"/>
<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>자유게시판</h3>
	</div>
</section>

<section id="content">
	<div class="container board-view-con">
		<div id="TN_id"class="board-view-tit">
			${article.TN_title }
		</div>
	</div>
</section>	

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-info">
			<span><i class="icon-user-edit"></i>${article.TN_id }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> ${article.TN_date }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-line-eye"></i> 8</span>
		</div>
	</div>
</section>
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-sub">
			<pre id="TN_content">${article.TN_content }</pre>
		</div>
	</div>
</section>	
	
<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-sub board-view-utilbtn">
			<c:if test="${TM_id!=null}"> 
				<a href="/TDL/index/TDL_NOTICE/updateForm.do?TN_num=${article.TN_num}&pageNum=${pageNum}"><button class="button">글수정</button></a>
				<a href="/TDL/index/TDL_NOTICE/deleteAction.do?TN_num=${article.TN_num}&pageNum=${pageNum}"><button class="button">글삭제</button></a>
			</c:if>
				<a href="/TDL/index/TDL_NOTICE/list.do?pageNum=${pageNum}"><button class="button">글목록</button></a>
		</div>
	</div>
</section>				
	

<script src="../js/commentCheck.js"></script>
<script src="../js/jquery.js"></script>
<script src="../js/plugins.js"></script>
<script src="../js/functions.js"></script>
</body>
</html>