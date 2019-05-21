<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../main/head.jsp" />
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header">
			<i class="icon-clipboard-list" style="margin-right: 10px;"></i>자유게시판
		</h3>
	</div>
</section>

<!-- 자유게시판 write wrap -->
<section id="content" class="board-write-con-wrap">
	
	<div class="container">
		
		<div class="form-widget">
			
			<div class="form-result"></div>
			
			<form class="nobottommargin" id="template-contactform"
				name="writeform"
				action="/TDL/index/TDL_POST/updatePro.do?pageNum=${pageNum}"
				method="post" >
				
				<input type="hidden" name="TP_num" value="${article.TP_num }">
				
				<div class="form-process"></div>
				
				<div class="col_full">
					<label for="template-contactform-subject">제목 <small>*</small><font id="titletResult" color="red">제목을 입력하세요</font></label>
					<input type="text" name="TP_title" value="${article.TP_title }"
						class="required sm-form-control" />
				</div>

				<div class="clear"></div>

				<div class="col_full">
					<label for="template-contactform-message">내용 <small>*</small><font id="contentResult" color="red">내용을 입력하세요</font></label>
					<textarea class="required sm-form-control" name="TP_content"
						rows="6" cols="30">${article.TP_content }</textarea>
				</div>

				<div class="col_full hidden">
					<input type="text" value="" class="sm-form-control" />
				</div>

				<div class="row">
					<div class="col-6 col-sm-6">
						<a href='/TDL/TDL_POST/list.do'>
							<button class="btn btn-success" type="submit" value="submit">목록으로</button>
						</a>
					</div>

					<div class="col-6 col-sm-6 text-right">
						<button class="btn btn-success" type="submit" value="submit" onclick="textCheck()">글쓰기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<script>
jQuery(document).ready(function($) {
	$("#titletResult").hide();
	$("#contentResult").hide();
})
</script>
<script src="../js/commentCheck.js"></script>	
<script src="../js/jquery.js"></script>
<script src="../js/plugins.js"></script>

<!-- Footer Scripts start -->
<script src="../js/functions.js"></script>
<!-- Footer Scripts end -->

</body>
</html>
	