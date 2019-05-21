<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String TU_id=(String)session.getAttribute("TU_id");
 	String TM_id=(String)session.getAttribute("TM_id");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="author" content="SemiColonWeb" />

	<!-- 템플릿 내용 start -->
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,400i,700|Raleway:300,400,500,600,700|Crete+Round:400i" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="../main/style.css" type="text/css" />
	<link rel="stylesheet" href="../css/dark.css" type="text/css" />
	<link rel="stylesheet" href="../css/font-icons.css" type="text/css" />
	<link rel="stylesheet" href="../css/animate.css" type="text/css" />
	<link rel="stylesheet" href="../css/magnific-popup.css" type="text/css" />
  
	<link rel="stylesheet" href="../css/responsive.css" type="text/css" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <!-- 템플릿 내용 end -->
    
    <!-- SLIDER REVOLUTION 5.x CSS SETTINGS -->
	<link rel="stylesheet" type="text/css" href="../include/rs-plugin/css/settings.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="../include/rs-plugin/css/layers.css">
	<link rel="stylesheet" type="text/css" href="../include/rs-plugin/css/navigation.css">
    <!-- SLIDER REVOLUTION 5.x CSS SETTINGS end -->
    
    <!-- tdl file start -->
    <link rel="stylesheet" href="../tdl/css/tdl.css" type="text/css" />
    <!-- tdl file end -->
    
    <!-- favicon -->
    <link rel="../icon" href="../tdl/img/favicon-logo.png" type="../image/vnd.microsoft.icon">
    
    <style>

		.revo-slider-emphasis-text {
			font-size: 64px;
			font-weight: 700;
			letter-spacing: -1px;
			font-family: 'Noto Sans KR', sans-serif;
			padding: 15px 20px;
			border-top: 2px solid #FFF;
			border-bottom: 2px solid #FFF;
		}

		.revo-slider-desc-text {
			font-size: 20px;
			font-family: 'Noto Sans KR', sans-serif;
			width: 650px;
			text-align: center;
			line-height: 1.5;
		}

		.revo-slider-caps-text {
			font-size: 16px;
			font-weight: 400;
			letter-spacing: 3px;
			font-family: 'Noto Sans KR', sans-serif;
		}
		.tp-video-play-button { display: none !important; }

		.tp-caption { white-space: nowrap; }

	</style>

    
    <meta charset="UTF-8">
    <title>The DDALeung</title>
</head>
<body>

<!-- Header
============================================= -->
<header id="header" class="split-menu">
    <div id="header-wrap">
        <div class="container clearfix">
            <div id="primary-menu-trigger"><i class="icon-reorder"></i></div>
            <!-- Logo
            ============================================= -->
            <div id="logo">
                <a href="../in/index.jsp" class="standard-logo" data-dark-logo="../tdl/img/logo.png"><img src="../tdl/img/logo.png" alt="TDL Logo"></a>
            </div><!-- #logo end -->
            <!-- Primary Navigation
            ============================================= -->
            <nav id="primary-menu" class="with-arrows clearfix style-5">
                <ul>
                    <li><a href="../main/map.jsp"><div><i class="icon-map-marker1"></i>대여소정보</div></a></li>
                    <li><a href="/TDL/index/TDL_NOTICE/list.do"><div><i class="icon-pushpin"></i>공지사항</div></a></li>
                    <li><a href="/TDL/index/TDL_POST/list.do"><div><i class="icon-clipboard-list"></i>자유게시판</div></a></li>
                    <li><a href="../main/safety.jsp"><div><i class="icon-heart3"></i>안전수칙</div></a></li>
                </ul>
                <ul>
                <%if(TU_id == null && TM_id==null){ %>
                	<li><a href="../TDL_MEMBER/login.jsp"><div><i class="icon-power-off"></i>로그인</div></a></li>
                	<li><a href="../TDL_MEMBER/Register.jsp"><div><i class="icon-line-circle-plus"></i>회원가입</div></a></li>
                	<li><a href="#"><div><i class="icon-line-cog"></i>마이페이지</div></a></li>                	
                <%}else{ %>                	
                	<li>
                		<a class="btn dropdown-toggle head-my-btn" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                			<div><i class="icon-user4"></i>내정보</div>
                		</a>
                		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						    <a class="dropdown-item" href="#">ID : <label id="like_id">${TU_id}<%if(TU_id==null){%>관리자<%} %></label></a>
						    <!-- <a class="dropdown-item" href="#">이름 : </a>
						    <a class="dropdown-item" href="#">성별 : </a> -->
						  </div>
                	</li>
                	<li><a href="/TDL/index/TDL_MEMBER/Logout.do"><div><i class="icon-power-off"></i>로그아웃</div></a></li>   
                	<li><a href="../TDL_Member/Mypage.do"><div><i class="icon-line-cog"></i>마이페이지</div></a></li>             	
                <%} %>                	
                	 
                </ul>
            </nav><!-- #primary-menu end -->
        </div>
    </div>
</header><!-- #header end -->

