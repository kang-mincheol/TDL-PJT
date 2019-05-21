<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
     //한글처리
     request.setCharacterEncoding("utf-8");
     //BoardDTO->Setter Method(5)+hidden (1)
     //BoardDAO 객체 필요
%>

<meta http-equiv="Refresh"  content="0;url=/TDL/index/TDL_NOTICE/list.do?pageNum=${pageNum }">


