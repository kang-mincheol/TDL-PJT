package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//추가
import tdl_notice.*;


public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================공지사항 글쓰기 프로 actionNotice - WriteProAction클래스 시작");
		//한글처리	
		request.setCharacterEncoding("utf-8");
		
		//DTO,DAO 객체 필요
		TDLNoticeDTO article=new TDLNoticeDTO();
		article.setTN_num(Integer.parseInt(request.getParameter("TN_num")));
		article.setTN_title(request.getParameter("TN_title"));
		article.setTN_content(request.getParameter("TN_content"));
		//==========================================================
		System.out.println("글쓰기액션 넘버값"+Integer.parseInt(request.getParameter("TN_num")));

		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		dbPro.insertArticle(article);
		System.out.println("================공지사항 글쓰기 프로 actionNotice - WriteProAction클래스 시작");
		//response.sendRedirect("http://localhost:8090/JspBoard2/list.do");
		return "/index/TDL_NOTICE/writePro.jsp"; // "/index.jsp"
	}

}
