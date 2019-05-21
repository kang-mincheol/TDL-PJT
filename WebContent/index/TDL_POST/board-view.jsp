<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../main/head.jsp"></jsp:include>

<!-- 자유게시판 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>자유게시판</h3>
	</div>
</section>

<section id="content">
	<div class="container board-view-con">
		<div class="board-view-tit">
			제목
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-info">
			<span><i class="icon-user-edit"></i> 홍길동</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> 2019-04-18</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-line-eye"></i> 8</span>
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con">
		<div class="board-view-sub">
			글내용
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-sub board-view-utilbtn">
			<button class="button">글수정</button>
			<button class="button">글삭제</button>
			<button class="button">글목록</button>
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con">
		<div class="board-view-comments-tit">
			<i class="icon-comments"></i> 댓글
		</div>
		<div class="board-view-comments-form">
			<form action="" method="">
				<div class="form-group">
					<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
					<button class="button board-view-comments-submit" type="submit">댓글쓰기</button>
				</div>
			</form>
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con board-view-con1">
		<div class="board-view-comments-tit">
			<i class="icon-comments"></i> 댓글
		</div>
	</div>
</section>

<section id="content">
	<div class="container board-view-con">
		<div class="row">
			<div class="board-view-comments col-9">
				<div>
					<span><i class="icon-user-edit"></i> 홍길동</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> 2019-04-18</span>
				</div>
				<div class="board-view-comments-con">
					댓글내용
				</div>
				<div class="board-view-comments-delbtn">
					<form action="" method="">
						<button class="button">댓글삭제</button>
					</form>
				</div>
			</div>
			<div class="board-view-comments col-3">
				<p class="comment-like text-center">8</p>
				<p class="comment-like-btn text-center"><button class=""><i class="icon-thumbs-up"></i></button></p>
			</div>
		</div>
	</div>
	<div class="container board-view-con">
		<div class="row">
			<div class="board-view-comments col-9">
				<div>
					<span><i class="icon-user-edit"></i> 홍길동</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> 2019-04-18</span>
				</div>
				<div class="board-view-comments-con">
					댓글내용
				</div>
			</div>
			<div class="board-view-comments col-3">
				<p class="comment-like text-center">8</p>
				<p class="comment-like-btn text-center"><button class=""><i class="icon-thumbs-up"></i></button></p>
			</div>
		</div>
	</div>
	<div class="container board-view-con">
		<div class="row">
			<div class="board-view-comments col-9">
				<div>
					<span><i class="icon-user-edit"></i> 홍길동</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><i class="icon-clock"></i> 2019-04-18</span>
				</div>
				<div class="board-view-comments-con">
					댓글내용
				</div>
			</div>
			<div class="board-view-comments col-3">
				<p class="comment-like text-center">8</p>
				<p class="comment-like-btn text-center"><button class=""><i class="icon-thumbs-up"></i></button></p>
			</div>
		</div>
	</div>
</section>





<script src="js/jquery.js"></script>
<script src="js/plugins.js"></script>

<!-- Footer Scripts start -->
<script src="js/functions.js"></script>
<!-- Footer Scripts end -->
</body>
</html>