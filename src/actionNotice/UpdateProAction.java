package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//추가
import tdl_notice.*;

// /updatePro.do?num=?&pageNum=1
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================공지사항 업데이트 프로 actionPost - UpdateProAction클래스 시작");
		//한글처리	
		request.setCharacterEncoding("utf-8");
		String pageNum=request.getParameter("pageNum");
		
		TDLNoticeDTO article=new TDLNoticeDTO();
		System.out.println("UpdateProAction 여기까진 나옴");
		article.setTN_num(Integer.parseInt(request.getParameter("TN_num")));
		article.setTN_title(request.getParameter("TN_title"));
		article.setTN_content(request.getParameter("TN_content"));

		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		int check=dbPro.updateArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		System.out.println("================공지사항 업데이트 프로 actionPost - UpdateProAction클래스 끝");
		return "/index/TDL_NOTICE/updatePro.jsp"; // "/index.jsp"
	}

}
