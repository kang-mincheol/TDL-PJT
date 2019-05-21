<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String TU_id=(String)session.getAttribute("TU_id");
	String TN_id=(String)session.getAttribute("TN_id");
%>
<jsp:include page="../main/head.jsp"/>


<div class="container">
<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>자유게시판</h3>
	</div>
</section>
<!-- 출력할 글 데이터가 하나도 없을 때. --> 
<c:if test="${pgList.count==0}">
	<section id="content">
		<div class="container">
			<div class="board-list-wrap board-list-header">
				<ul class="row">	
					<li>게시판에 저장된 글이 없습니다.</li>
				</ul>
			</div>
		</div>
	</section>
</c:if>
<!-- 출력할 글 데이터가 있을 때. -->
<c:if test="${pgList.count != 0 }">
	<section id="content">
		<div class="container">
			<div class="board-list-wrap board-list-header">
				<ul class="row">	
					<li class="col-xs-1 col-md-1">번호</li>
					<li class="col-xs-6 col-md-6">제목</li>
					<li class="col-xs-2 col-md-2">작성자</li>
					<li class="col-xs-2 col-md-2">작성일</li>
					<li class="col-xs-1 col-md-1">조회수</li>
				</ul>
			</div>
			<div class="board-list-wrap borard-list-con">
				<c:set var="number" value="${pgList.number}" />
				<c:forEach var="article" items="${articleList}">
					<ul class="row">
						<li class="col-md-1 col-xs-1"><c:out value="${number}" /> <c:set var="number" value="${number-1}" /></li>
						<li class="col-md-6 col-xs-6"><a href="/TDL/index/TDL_POST/content.do?TP_num=${article.TP_num}&pageNum=${pgList.currentPage}&TP_id=${article.TP_id}">${article.TP_title}</a></li>
						<li class="col-md-2 col-xs-2">${article.TP_id }</li>
						<li class="col-md-2 col-xs-2">${article.TP_date.substring(0,10)}</li>	
						<li class="col-md-1 col-xs-1">${article.TP_readcount }</li>
					</ul>
				</c:forEach>
			</div>
		</div>
	</section>			
</c:if>

	
<!-- 자유게시판 list page paging -->
<section id="content" class="board-list-paging">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul class="pagination">
					<c:if test="${pgList.startPage <= pgList.blockSize}"> <li class="page-item"><a class="page-link" href="#">&laquo;</a></li></c:if>
					<c:if test="${pgList.startPage > pgList.blockSize}"><li class="page-item"><a class="page-link" href="/TDL/index/TDL_POST/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">&laquo;</a></li></c:if>
					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					 	<li class="page-item"><a class="page-link" href="/TDL/index/TDL_POST/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
							<c:if test="${pgList.currentPage==i}">${i}</c:if>
						 	<c:if test="${pgList.currentPage!=i}">${i}</c:if>
						</a></li>
					</c:forEach>
					<c:if test="${pgList.endPage >= pgList.pageCount }"><li class="page-item"><a class="page-link" href="#">&raquo;</a></li></c:if>
					<c:if test="${pgList.endPage < pgList.pageCount }"><li class="page-item"><a class="page-link" href="/TDL/index/TDL_POST/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">&raquo;</a></li></c:if>
				</ul>		
			</div>
		</div>
	</div>
</section>			

<section id="content" class="board-list-search-wrap">
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<form name="test" action="/TDL/index/TDL_POST/list.do"> 
					<div class="form-row">
						<div class="form-group col-md-2">
							<div class="white-section">
								<select id="inputState" name="search" class="form-control selectpicker">					
									<option value="TP_title">제목</option>
									<option value="subject_content">제목+본문</option>
									<option value="subject_writer">작성자</option>
								</select> 
							</div>
						</div>
						<div class="form-group col-md-4">
							<input type="text" name="searchtext" class="form-control">
						</div>
						<div class="form-group col-md-1">
							<button type="submit" class="btn btn-success form-control"><i class="icon-line-search"></i></button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-2">
				<div class="text-right">
				<c:if test="${TU_id	!=null }">
					<a href="/TDL/index/TDL_POST/writeForm.do">
						<button class="btn btn-success">글쓰기</button>
					</a> 
				 </c:if> 
				 <c:if test="${TU_id==null }">
					<button class="btn btn-success" onclick="log()">글쓰기</button>		 
				 </c:if> 
				</div>	
			</div>
		</div>
	</div>
</section>
<script>
function log(){
		alert("글쓰기는 로그인이 필요합니다.")
		location.href="../TDL_MEMBER/login.jsp"
	}
</script>
<!--  여기까지 게시판 목록리스트 ======================================================================================== -->
<jsp:include page="bottom.jsp"/>
