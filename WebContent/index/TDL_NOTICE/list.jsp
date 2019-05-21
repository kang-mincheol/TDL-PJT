<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String TM_id=(String)session.getAttribute("TM_id");
%>
<jsp:include page="../main/head.jsp"/>


<div class="container">
<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>공지사항</h3>
	</div>
</section>
</div>
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
						<li class="col-md-6 col-xs-6"><a href="/TDL/index/TDL_NOTICE/content.do?TN_num=${article.TN_num}&pageNum=${pgList.currentPage}&TM_id=${article.TN_id}">${article.TN_title}</a></li>
						<li class="col-md-2 col-xs-2">${article.TN_id }</li>
						<li class="col-md-2 col-xs-2">${article.TN_date.substring(0,10)}</li>	
						<li class="col-md-1 col-xs-1">${article.TN_readcount }</li>
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
					<c:if test="${pgList.startPage > pgList.blockSize}"><li class="page-item"><a class="page-link" href="/TDL/index/TDL_NOTICE/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">&laquo;</a></li></c:if>
					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					 	<li class="page-item"><a class="page-link" href="/TDL/index/TDL_NOTICE/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
							<c:if test="${pgList.currentPage==i}">${i}</c:if>
						 	<c:if test="${pgList.currentPage!=i}">${i}</c:if>
						</a></li>
					</c:forEach>
					<c:if test="${pgList.endPage >= pgList.pageCount }"><li class="page-item"><a class="page-link" href="#">&raquo;</a></li></c:if>
					<c:if test="${pgList.endPage < pgList.pageCount }"><li class="page-item"><a class="page-link" href="/TDL/index/TDL_NOTICE/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">&raquo;</a></li></c:if>
				</ul>		
			</div>
		</div>
	</div>
</section>			

<!-- 자유게시판 list page search -->
<section id="content" class="board-list-search-wrap">
	<div class="container">
		<div class="row">
			<div class="col-12 col-md-2 nopadding form-row">
				<div class="col-4 col-md-12">
					<c:if test="${TM_id	!=null }">
						<a href="/TDL/index/TDL_NOTICE/writeForm.do">
							<button class="btn btn-success write-btn btn-block">글쓰기</button>
						</a>
					</c:if>
				</div>	
			</div>
			<div class="col-12 col-md-10  nopadding">
				<form>
					<div class="form-row">
						<div class="form-group  col-4 col-md-3 offset-md-4">
							<div class="white-section">
								<select id="inputState" name="search" class="form-control selectpicker">					
									<option value="TN_title">제목</option>
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
		</div>
	</div>
</section>
<script>
function log(){
		alert("글쓰기는 로그인이 필요합니다.")
		location.href="../TDL_MEMBER/login.jsp"
	}
</script>

<!-- External JavaScripts
============================================= -->
<script src="js/jquery.js"></script>
<script src="js/plugins.js"></script>

<!-- Footer Scripts start -->
<script src="js/functions.js"></script>
<!-- Footer Scripts end -->

<!-- Bootstrap Select Plugin -->
<script src="js/components/bs-select.js"></script>

<!-- Select Splitter Plugin -->
<script src="js/components/selectsplitter.js"></script>

<!-- SLIDER REVOLUTION 5.x SCRIPTS  -->
	<script src="../include/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
	<script src="../include/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

	<script src="../include/rs-plugin/js/extensions/revolution.extension.video.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.slideanims.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.actions.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.layeranimation.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.kenburn.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.navigation.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.migration.min.js"></script>
	<script src="../include/rs-plugin/js/extensions/revolution.extension.parallax.min.js"></script>

	<script>

		var tpj=jQuery;
		var revapi31;
		tpj(document).ready(function() {
			if(tpj("#rev_slider_679_1").revolution == undefined){
				revslider_showDoubleJqueryError("#rev_slider_679_1");
			}else{
				revapi31 = tpj("#rev_slider_679_1").show().revolution({
					sliderType:"standard",
					jsFileLocation:"../include/rs-plugin/js/",
					sliderLayout:"fullwidth",
					dottedOverlay:"none",
					hideThumbs:200,
					thumbWidth:100,
					thumbHeight:50,
					thumbAmount:5,
					navigation: {
	                    keyboardNavigation: "on",
	                    keyboard_direction: "horizontal",
	                    mouseScrollNavigation: "off",
	                    onHoverStop: "off",
	                    touch: {
	                        touchenabled: "on",
	                        swipe_threshold: 75,
	                        swipe_min_touches: 1,
	                        swipe_direction: "horizontal",
	                        drag_block_vertical: false
	                    }
	                },
					responsiveLevels:[1140,1024,778,480],
					visibilityLevels:[1140,1024,778,480],
					gridwidth:[1140,1024,778,480],
					gridheight:[700,768,960,720],
					lazyType:"none",
					shadow:0,
					spinner:"off",
					stopLoop:"off",
					stopAfterLoops:-1,
					stopAtSlide:-1,
					shuffle:"off",
					autoHeight:"off",
					fullScreenAutoWidth:"off",
					fullScreenAlignForce:"off",
					fullScreenOffsetContainer: "",
					fullScreenOffset: "0px",
					hideThumbsOnMobile:"off",
					hideSliderAtLimit:0,
					hideCaptionAtLimit:0,
					hideAllCaptionAtLilmit:0,
					debugMode:false,
					fallbacks: {
						simplifyAll:"off",
						nextSlideOnWindowFocus:"off",
						disableFocusListener:false,
					}
				});
			}

           
			revapi31.bind("revolution.slide.onchange",function (e,data) {
				SEMICOLON.slider.revolutionSliderMenu();
			});
		});	/*ready*/
	</script>

<script>
	$('.selectsplitter').selectsplitter();
</script>
</body>
</html>