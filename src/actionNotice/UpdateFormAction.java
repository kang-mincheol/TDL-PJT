package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//추가
import tdl_notice.*;
;public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================공지사항 업데이트 폼 actionPost - UpdateFormAction클래스 시작");
		//content.jsp -> 글수정 -> /TDL/updateForm.do?num=?&pageNum=1
		int TN_num=Integer.parseInt(request.getParameter("TN_num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("UpdateFormAction TN_num 값 =>"+TN_num);
		System.out.println("UpdateFormAction  pageNum -> "+pageNum);
		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		TDLNoticeDTO article=dbPro.updateGetArticle(TN_num);
	
		request.setAttribute("pageNum",pageNum);//${pageNum}
		request.setAttribute("article",article);
		System.out.println("================공지사항 업데이트 폼 actionPost - UpdateFormAction클래스 끝 ");
		return "/index/TDL_NOTICE/updateForm.jsp";
	}

}
