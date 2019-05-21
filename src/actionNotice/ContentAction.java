package actionNotice;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
import tdl_Comment.TDLCommentDAO;
//추가
import tdl_notice.*;

//content.jsp에서 처리한 자바코드부분을 대신 처리해주는 액션클래스
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("================공지사항 글보기 actionPost - ContentAction클래스 시작");
		int TN_num=Integer.parseInt(request.getParameter("TN_num"));
		System.out.println("TN_num 의 값 -> "+TN_num);
		String pageNum=request.getParameter("pageNum");
		System.out.println("pageNum의 값 -> "+pageNum);
		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		TDLNoticeDTO article=dbPro.getArticle(TN_num);
	
		System.out.println("content.do의 매개변수");
		
		//ModelAndView mav=new ModelAndView(); 
		//mav.addObject("TN_num",TN_num);
		request.setAttribute("TN_num", TN_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
	
	
			System.out.println("================공지사항 글보기 actionPost - ContentAction클래스 끝");	
		return "/index/TDL_NOTICE/content.jsp";
	}

}
