<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
 <%
	String TU_id=(String)session.getAttribute("TU_id");
	String TM_id=(String)session.getAttribute("TM_id");
	String articleLike=(String)request.getAttribute("articleLike");
	String x=null;
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
		<div id="TP_id"class="board-view-tit">
			${article.TP_title }
		</div>
	</div>
</section>	

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-info">
			<span><i class="icon-user-edit"></i>${article.TP_id }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> ${article.TP_date }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-line-eye"></i> 8</span>
		</div>
	</div>
</section>
	
<section id="content">
	<div class="container board-view-con">
		<div class="board-view-sub">
			<pre id="TP_content">${article.TP_content }</pre>
		</div>
	</div>
</section>	
	
		
<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-sub board-view-utilbtn">
			<c:if test="${TM_id!=null || article.TP_id==TU_id}">
				<a href="/TDL/index/TDL_POST/updateForm.do?TP_num=${article.TP_num}&pageNum=${pageNum}"><button class="button">글수정</button></a>
				<a href="/TDL/index/TDL_POST/deleteAction.do?TP_num=${article.TP_num}&pageNum=${pageNum}"><button class="button">글삭제</button></a>
			</c:if>
				<a href="/TDL/index/TDL_POST/list.do?pageNum=${pageNum}"><button class="button">글목록</button></a>
		</div>
	</div>
</section>				
	


<!-- 댓글시작 -->

<section id="content">
	<div class="container board-view-con">
		<div class="board-view-comments-tit">
			<i class="icon-comments"></i> 댓글
		</div>
		<div class="board-view-comments-form">
			<form action="/TDL/index/TDL_COMMENT/writePro.do" name="writeform" method="post" onsubmit="return writeSave()">
				<input type="hidden" name="TP_num"  value="${article.TP_num}">
				<input type="hidden" name="TU_id" value="${TU_id}">
				<input type="hidden" name="TP_id" value="${article.TP_id }">
				<div class="form-group">
					<textarea class="form-control" id="exampleFormControlTextarea1" name="TPC_content" rows="3" maxlength="2048"></textarea>
					<c:if test="${TU_id!=null || TM_id!=null}">
						<button class="button board-view-comments-submit" type="submit">댓글쓰기</button>
					</c:if>
					<c:if test="${TU_id==null && TM_id==null}">
						<input type="button" class="button board-view-comments-submit" onclick="comment()" value="댓글쓰기">
					</c:if>	
				</div>	
			</form>
		</div>
	</div>
</section>
	
<!-- 댓글 출력 -->

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-comments-tit">
			<i class="icon-comments"></i> 댓글
		</div>
	</div>
</section>

<!-- forEach를 사용해 댓글 출력 -->
<c:if test="${pgListC.count > 0 }">
	<c:set var="countC" value="1"/>
	<c:set var="number" value="${pgListC.number}" />
	<c:forEach var="articleC" items="${articleListC}" >
		<c:if test="${article.TP_num==articleC.TPC_num }">					
			<section id="content">
				<div class="container board-view-con">
					<div class="row">
						<div class="board-view-comments col-9">
							<div>
								<span><i class="icon-user-edit"></i> ${articleC.TPC_id }</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>
								<i class="icon-clock"></i> ${articleC.TPC_date.substring(0,4)}년
														${articleC.TPC_date.substring(5,7)}월
														${articleC.TPC_date.substring(8,10) }일 
														${articleC.TPC_date.substring(11,19) } 작성 </span>
							</div> 
							<div class="board-view-comments-con">
								${articleC.TPC_content }
							</div>
							<div class="board-view-comments-delbtn">
								<form action="/TDL/index/TDL_COMMENT/Delete.do" method="post">
									<c:if test="${TM_id != null || article.TP_id == TU_id || articleC.TPC_id == TU_id }">
										<button class="button">댓글삭제</button>
									</c:if>		 	
									<input type="hidden" name="TPC_num" value="${articleC.TPC_num}">
									<input type="hidden" name="countC" value="${countC}">
									<input id="likeAddr" type="hidden" name="likeAddr" value="${articleC.TPC_addr}">
									<input id="sumCount" type="hidden" name="sumCount" value="${sumCount}">	
									<input type="hidden" name="TP_num"  value="${article.TP_num}">
									<input type="hidden" name="TU_id" value="${TU_id}">
									<input type="hidden" name="TP_id" value="${article.TP_id }">	
									<c:set var="like" value="${articleC.TPC_addr }"/>
									<%x=(String)pageContext.getAttribute("like");%>
								</form>
								<c:set var="countC" value="${countC+1}" />		
							</div>
						</div>
						<div class="board-view-comments col-3">
						<!-- 좋아요 조건 수정 -->	
							<p class="comment-like text-center">${articleC.TPC_good}</p>							
							
								<p class="comment-like-btn text-center"><button class="" 
									onclick="location.href='/TDL/index/TDL_COMMENT/like.do?TU_id=${TU_id}&addr=${articleC.TPC_addr}&TP_num=${article.TP_num}'">
									<%if(articleLike.indexOf(x)==-1){ %>
										<font id="" class="icon-thumbs-up" color="gray" style="font-size:22"></font>
									<%}
										if(articleLike.indexOf(x)!=-1){%>
										<font id="" class="icon-thumbs-up" color="blue" style="font-size:22"></font>					
									<%} %>
										</button></p>
									
						</div>
					</div>
				</div>					
			</section>	
		</c:if> 
		
	</c:forEach>							
</c:if>
 

<script src="../js/commentCheck.js"></script>
<script src="../js/jquery.js"></script>
<script src="../js/plugins.js"></script>
<script src="../js/functions.js"></script>
</body>
</html>